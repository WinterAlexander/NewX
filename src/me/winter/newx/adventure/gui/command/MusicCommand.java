package me.winter.newx.adventure.gui.command;

import me.winter.newx.adventure.gui.Chat;
import me.winter.newx.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * Created by Alexander Winter on 2016-02-19.
 */
public class MusicCommand implements Command
{
	@Override
	public String getName()
	{
		return "music";
	}

	@Override
	public List<String> getAliases()
	{
		return Arrays.asList("song", "audio", "setmusic");
	}

	@Override
	public String getDescription()
	{
		return "Changes the music of the current level";
	}

	@Override
	public String getUsage()
	{
		return "/music <set/get/list> [name]";
	}

	@Override
	public void execute(Chat chat, String label, String[] args)
	{
		if(args.length == 0)
			args = new String[]{"list"};

		if(args[0].equalsIgnoreCase("get"))
		{
			if(chat.getState().getLevel().getMusic() == null)
				chat.sendMessage("There's no music currently playing");
			else
				chat.sendMessage("The music currently playing is " + StringUtil.capitalize(StringUtil.getLastPart(chat.getState().getLevel().getMusic().toLowerCase(), "_")));
			return;
		}

		List<String> musics = new ArrayList<>();

		for(String sound : chat.getState().getResourceManager().getAudios())
			if(sound.startsWith("music_"))
				musics.add(StringUtil.getLastPart(sound, "_"));

		if(args[0].equalsIgnoreCase("list"))
		{
			chat.sendMessage("List of available music: (" + musics.size() + ")");

			for(String music : musics)
				chat.sendMessage(StringUtil.capitalize(music.toLowerCase()));
			chat.sendMessage(getUsage());
			return;
		}

		if(args[0].equalsIgnoreCase("set"))
		{
			if(args.length < 2)
			{
				chat.sendMessage("You do need to specify the music name");
				return;
			}

			String music = null;

			for(String currentMusic : musics)
				if(currentMusic.equalsIgnoreCase(args[1]))
					music = currentMusic;

			if(music == null)
			{
				chat.sendMessage("Music not found. To see list use /music list");
				return;
			}

			if(chat.getState().getMusic() != null)
				chat.getState().getMusic().stop();
			chat.getState().setMusic(chat.getState().getResourceManager().getAudio(music));
			chat.getState().getMusic().playAsMusic(1f, 1f, true);
			chat.getState().getLevel().setMusic(music);
			chat.sendMessage(StringUtil.capitalize(music) + " is now playing.");
			return;
		}

		chat.sendMessage("Invalid command usage");
		chat.sendMessage(getUsage());
	}
}
