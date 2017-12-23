package me.winter.newx.adventure.gui.command;

import me.winter.newx.adventure.gui.Chat;

import java.util.List;

/**
 *
 * Created by Alexander Winter on 2016-02-19.
 */
public interface Command
{
	String getName();
	List<String> getAliases();
	String getDescription();
	String getUsage();
	void execute(Chat chat, String label, String[] arguments);
}
