package me.winter.newx.adventure.gui.command;

import me.winter.newx.adventure.gui.Chat;
import me.winter.newx.adventure.physics.collision.RectangleCollisionBox;
import me.winter.newx.adventure.world.object.WorldObject;
import me.winter.newx.adventure.world.object.proprieties.Localizable;
import me.winter.newx.adventure.world.object.proprieties.Movable;
import me.winter.newx.adventure.world.object.proprieties.Solid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Alexander Winter on 2016-02-22.
 */
public class RemoveAllCommand implements Command
{
	@Override
	public String getName()
	{
		return "removeall";
	}

	@Override
	public List<String> getAliases()
	{
		return Arrays.asList("deleteall", "delobjects");
	}

	@Override
	public String getDescription()
	{
		return "Deletes all WorldObjects selected with a the specified method" +
				"\ncollision method only applies to solids, looking if the pin is inside the object collision box" +
				"\ndistance method apply to any movable object with a location" +
				"\n\n See also: /remove";
	}

	@Override
	public String getUsage()
	{
		return "/removeall [mode] [args ...]";
	}

	@Override
	public void execute(Chat chat, String label, String[] arguments)
	{
		if(arguments.length == 0)
			arguments = new String[]{"collision"};

		int count = 0;

		if(arguments[0].equalsIgnoreCase("collision"))
		{
			for(WorldObject element: new ArrayList<>(chat.getState().getWorld().getWorldObjects()))
				if(element instanceof Solid)
					if(((Solid)element).getCollisionBox().collides(new RectangleCollisionBox(null, chat.getGui().getPin().getX() - 0.005, chat.getGui().getPin().getY() - 0.005, 0.01, 0.01)))
					{
						if(element == chat.getState().getPlayer())
							continue;

						chat.getState().getWorld().getWorldObjects().remove(element);
						count++;
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

			for(WorldObject element: new ArrayList<>(chat.getState().getWorld().getWorldObjects()))
				if(element instanceof Localizable)
					if(((Localizable)element).getLocation().distance(chat.getGui().getPin()) <= distance)
					{
						if(element == chat.getState().getPlayer())
							continue;

						chat.getState().getWorld().getWorldObjects().remove(element);
						count++;
					}
		}

		chat.sendMessage(count + " objects removed.");
	}
}