package me.winter.newx.adventure.gui.command;

import me.winter.newx.adventure.gui.Chat;
import me.winter.newx.adventure.physics.Direction;
import me.winter.newx.adventure.physics.Location;

import java.util.Arrays;
import java.util.List;

/**
 *
 * Created by Alexander Winter on 2016-02-22.
 */
public class SetSpawnCommand implements Command
{
	@Override
	public String getName()
	{
		return "setspawn";
	}

	@Override
	public List<String> getAliases()
	{
		return Arrays.asList("setspawnpoint", "setplayerspawn");
	}

	@Override
	public String getDescription()
	{
		return "Sets the players spawn of the world at the specified location";
	}

	@Override
	public String getUsage()
	{
		return "/setspawn [x] [y] [direction]";
	}

	@Override
	public void execute(Chat chat, String label, String[] arguments)
	{
		Location location = new Location(chat.getGui().getPin(), Direction.RIGHT);

		if(arguments.length > 0) try
		{
			location.setX(Double.parseDouble(arguments[0]));
			if(arguments.length > 1)
				location.setY(Double.parseDouble(arguments[1]));

			if(arguments.length > 2)
				location.setDirection(Direction.fromString(arguments[2]));

			if(location.getDirection() == null)
				throw new NullPointerException("Spawn direction CANNOT be null");
		}
		catch(ArithmeticException exception)
		{
			chat.sendMessage("Invalid arguments. x and y should be double values (numbers)");
			return;
		}
		catch(NullPointerException exception)
		{
			chat.sendMessage("Invalid direction (left/right/up/down)");
			return;
		}

		chat.getGui().getAdventure().getLevel().setPlayerSpawn(location);
		chat.sendMessage("The new player spawn has been set.");
	}
}
