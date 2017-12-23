package me.winter.newx.adventure.gui.command;

import me.winter.newx.adventure.gui.Chat;

import java.util.Arrays;
import java.util.List;

/**
 *
 * Created by Alexander Winter on 2016-02-19.
 */
public class ClearCommand implements Command
{
	@Override
	public String getName()
	{
		return "clear";
	}

	@Override
	public List<String> getAliases()
	{
		return Arrays.asList("clearchat", "cc");
	}

	@Override
	public String getDescription()
	{
		return "Clears the client chat history.";
	}

	@Override
	public String getUsage()
	{
		return "/clear";
	}

	@Override
	public void execute(Chat chat, String label, String[] arguments)
	{
		chat.getChatHistory().clear();
	}
}
