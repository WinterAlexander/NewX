package me.winter.newx.adventure.gui.command;

import me.winter.newx.adventure.gui.Chat;
import me.winter.newx.util.StringUtil;

import java.util.Arrays;
import java.util.List;

/**
 *
 * Created by 1541869 on 2016-02-19.
 */
public class HelpCommand implements Command
{
	@Override
	public String getName()
	{
		return "help";
	}

	@Override
	public List<String> getAliases()
	{
		return Arrays.asList("aide", "helpme", "sos", "?", "lscmd", "listcommands");
	}

	@Override
	public String getDescription()
	{
		return "Gets the description and usage of the given command";
	}

	@Override
	public String getUsage()
	{
		return "/help [command]";
	}

	@Override
	public void execute(Chat chat, String label, String[] arguments)
	{
		if(arguments.length == 0)
			arguments = new String[]{"help"};

		Command command = chat.findCommand(arguments[0]);

		if(command == null)
		{
			chat.sendMessage("Command \"" + arguments[0] + "\" not found.");
			return;
		}


		chat.sendMessage(command.getName() + ":");
		chat.sendMessage(command.getDescription());
		chat.sendMessage("Usage: " + command.getUsage());
		chat.sendMessage("Aliases: " + StringUtil.join(command.getAliases()));
	}
}
