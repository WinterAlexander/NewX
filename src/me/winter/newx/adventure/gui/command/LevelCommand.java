package me.winter.newx.adventure.gui.command;

import me.winter.newx.adventure.gui.Chat;
import me.winter.newx.adventure.level.ExampleLevel;
import me.winter.newx.adventure.level.FileLevel;
import me.winter.newx.adventure.level.Level;
import org.newdawn.slick.util.Log;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 *
 * Created by 1541869 on 2016-02-19.
 */
public class LevelCommand implements Command
{
	@Override
	public String getName()
	{
		return "level";
	}

	@Override
	public List<String> getAliases()
	{
		return Arrays.asList("lvl", "world", "map");
	}

	@Override
	public String getDescription()
	{
		return "Lets you manage the current level";
	}

	@Override
	public String getUsage()
	{
		return "/level <save/load/template> <level-name>";
	}

	@Override
	public void execute(Chat chat, String label, String[] args)
	{
		if(args.length != 2)
		{
			chat.sendMessage("Invalid command usage");
			chat.sendMessage(getUsage());
			return;
		}

		if(args[0].equalsIgnoreCase("template") || args[0].equalsIgnoreCase("new"))
		{
			Level level;

			switch(args[1].toLowerCase())
			{
				case "example":
					level = new ExampleLevel();
					break;

				case "empty":
					level = new ExampleLevel();
					break;

				default:
					chat.sendMessage("Template \"" +  args[1] + "\" can not be found");
					return;
			}

			chat.getState().load(level);
			chat.sendMessage("The level has been loaded");
			return;
		}

		if(args[0].equalsIgnoreCase("save"))
		{
			File file = new File(chat.getState().getGame().getDataFolder(), args[1] + ".level");

			if(file.exists())
				chat.sendMessage("Warning, file already exists, overwriting it");

			try
			{
				FileLevel level = new FileLevel(file, args[1], chat.getState().getLevel().getMusic(), chat.getState().getLevel().getPlayerSpawn(), chat.getState().getWorld());
				level.save();
				chat.sendMessage("The level has been saved");
			}
			catch(Exception e)
			{
				chat.sendMessage("An error occurred while saving this level: " + e.getMessage());
				Log.error(e);
			}
			return;
		}

		if(args[0].equalsIgnoreCase("load"))
		{
			File file = new File(chat.getState().getGame().getDataFolder(), args[1] + ".level");

			if(!file.exists())
			{
				chat.sendMessage("That level can not be found");
				return;
			}

			try
			{
				FileLevel level = new FileLevel(file);
				level.load();
				chat.getState().load(level);
				chat.sendMessage("The level has been loaded");
			}
			catch(Exception e)
			{
				chat.sendMessage("An error occurred while loading this level: " + e.getMessage());
				Log.error(e);
			}
			return;
		}

		chat.sendMessage("Invalid command usage");
		chat.sendMessage(getUsage());
	}
}
