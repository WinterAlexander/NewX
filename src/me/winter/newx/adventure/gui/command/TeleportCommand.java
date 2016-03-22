package me.winter.newx.adventure.gui.command;

import me.winter.newx.adventure.gui.Chat;
import me.winter.newx.adventure.physics.Direction;
import me.winter.newx.adventure.physics.Location;
import me.winter.newx.util.StringUtil;

import java.util.Arrays;
import java.util.List;

/**
 *
 * Created by 1541869 on 2016-02-19.
 */
public class TeleportCommand implements Command
{
	@Override
	public String getName()
	{
		return "teleport";
	}

	@Override
	public List<String> getAliases()
	{
		return Arrays.asList("tp", "goto");
	}

	@Override
	public String getDescription()
	{
		return "Teleports the player to a specified destination";
	}

	@Override
	public String getUsage()
	{
		return "/teleport [x=pin] [y=pin] [direction=right]";
	}

	@Override
	public void execute(Chat chat, String label, String[] args)
	{
		Location loc = new Location(chat.getGui().getPin(), Direction.RIGHT);

		if(args.length == 1 && args[0].equalsIgnoreCase("spawn"))
		{
			loc = new Location(chat.getGui().getAdventure().getLevel().getPlayerSpawn());
		}
		else if(args.length == 1 || args.length > 3)
		{
			chat.sendMessage("Invalid command usage");
			chat.sendMessage(getUsage());
			return;
		}

		if(args.length >= 2)
		{
			Direction direction = Direction.RIGHT;

			if(!StringUtil.isDouble(args[0]) || !StringUtil.isDouble(args[1]))
			{
				chat.sendMessage("X and Y coordinates should be valid decimal values");
				return;
			}

			if(args.length == 3)
			{
				direction = Direction.fromString(args[2]);

				if(direction == null)
				{
					chat.sendMessage("Invalid direction (up/down/left/right)");
					return;
				}
			}

			loc = new Location(Double.parseDouble(args[0]), Double.parseDouble(args[1]), direction);
		}

		chat.getState().getPlayer().setLocation(loc);
		chat.sendMessage("Player teleported to " + loc.toString());
	}
}
