package me.winter.newx.adventure.gui.command;

import me.winter.newx.adventure.gui.Chat;
import me.winter.newx.adventure.physics.collision.RectangleCollisionBox;
import me.winter.newx.adventure.world.object.WorldObject;
import me.winter.newx.adventure.world.object.properties.Localizable;
import me.winter.newx.adventure.world.object.properties.Solid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * Created by 1541869 on 2016-02-19.
 */
public class RemoveCommand implements Command
{
	@Override
	public String getName()
	{
		return "remove";
	}

	@Override
	public List<String> getAliases()
	{
		return Arrays.asList("delete", "delobject");
	}

	@Override
	public String getDescription()
	{
		return "Deletes a single WorldObject selected with a the specified method" +
				"\ncollision method only applies to solids, looking if the pin is inside the object collision box" +
				"\ndistance method apply to any movable object with a location" +
				"\n\n See also: /removeall";
	}

	@Override
	public String getUsage()
	{
		return "/remove [mode] [args ...]";
	}

	@Override
	public void execute(Chat chat, String label, String[] arguments)
	{
		if(arguments.length == 0)
			arguments = new String[]{"collision"};

		if(arguments[0].equalsIgnoreCase("collision"))
		{
			for(WorldObject element: new ArrayList<>(chat.getState().getWorld().getWorldObjects()))
				if(element instanceof Solid)
					if(((Solid)element).getCollisionBox().collides(new RectangleCollisionBox(null, chat.getGui().getPin().getX() - 0.005, chat.getGui().getPin().getY() - 0.005, 0.01, 0.01)))
					{
						if(element == chat.getState().getPlayer())
							continue;

						chat.getState().getWorld().getWorldObjects().remove(element);
						chat.sendMessage("Object removed.");
						return;
					}


		}

		if(arguments[0].equalsIgnoreCase("distance"))
		{
			double distance;
			try
			{
				distance = Double.parseDouble(arguments[1]);
			}
			catch(Exception e)
			{
				chat.sendMessage("Invalid distance value. /remove distance <value>");
				return;
			}


			Localizable toRemove = null;

			for(WorldObject element : chat.getState().getWorld().getWorldObjects())
				if(element instanceof Localizable)
				{
					double currentDistance = ((Localizable)element).getLocation().distance(chat.getGui().getPin());

					if(currentDistance <= distance)
					{
						if(element == chat.getState().getPlayer())
							continue;

						if(toRemove != null && toRemove.getLocation().distance(chat.getGui().getPin()) < currentDistance)
							continue;

						toRemove = (Localizable)element;
					}
				}

			if(toRemove == null)
			{
				chat.sendMessage("No object found.");
				return;
			}

			chat.getState().getWorld().getWorldObjects().remove(toRemove);
			chat.sendMessage("Object removed.");
			return;
		}
	}
}
