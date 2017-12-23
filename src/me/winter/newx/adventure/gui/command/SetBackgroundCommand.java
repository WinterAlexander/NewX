package me.winter.newx.adventure.gui.command;

import me.winter.newx.adventure.gui.Chat;
import me.winter.newx.adventure.world.object.Background;
import me.winter.newx.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * Created by Alexander Winter on 2016-02-22.
 */
public class SetBackgroundCommand implements Command
{
	@Override
	public String getName()
	{
		return "setbackground";
	}

	@Override
	public List<String> getAliases()
	{
		return Arrays.asList("setback", "setbg", "background", "setbground");
	}

	@Override
	public String getDescription()
	{
		return "Sets the specified background image as the current world's background.\nTo get the list, type the command with no arguments.";
	}

	@Override
	public String getUsage()
	{
		return "/setbackground [background-image]";
	}

	@Override
	public void execute(Chat chat, String label, String[] arguments)
	{
		List<String> backgrounds = new ArrayList<>();

		for(String image : chat.getState().getResourceManager().getImages())
			if(image.startsWith("bg_"))
				backgrounds.add(StringUtil.getLastPart(image, "_").toLowerCase());

		if(arguments.length == 0)
		{

			chat.sendMessage(backgrounds.size() + " backgrounds are available:");

			for(String image : backgrounds)
				chat.sendMessage(StringUtil.capitalize(image));

			return;
		}

		String image = null;

		for(String background : backgrounds)
			if(background.equalsIgnoreCase(arguments[0]))
			{
				image = "bg_" + background;
				break;
			}


		if(image == null)
		{
			chat.sendMessage("Couldn't find that image.");
			return;
		}

		chat.getGui().getAdventure().getWorld().setBackground(new Background(chat.getGui().getAdventure().getWorld(), image));
		chat.sendMessage("The background has been successfully changed.");
	}
}
