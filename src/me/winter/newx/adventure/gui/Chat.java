package me.winter.newx.adventure.gui;

import me.winter.newx.adventure.AdventureState;
import me.winter.newx.adventure.Drawer;
import me.winter.newx.adventure.gui.command.*;
import me.winter.newx.util.StringUtil;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.Log;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Chat
{
	private Gui gui;
	
	private boolean open;
	private String content;
	private int cursor, cmdHistoryIndex;
	private List<String> chatHistory;
	private List<String> commandHistory;
	private List<Command> commands;

	
	public Chat(Gui gui)
	{
		this.gui = gui;
		
		this.open = false;
		this.content = "";
		this.cursor = 0;
		this.cmdHistoryIndex = 0;

		this.chatHistory = new ArrayList<>();
		this.commandHistory = new ArrayList<>();

		this.commands = new ArrayList<>();
		this.commands.add(new HelpCommand());
		this.commands.add(new LevelCommand());
		this.commands.add(new MusicCommand());
		this.commands.add(new TeleportCommand());
		this.commands.add(new ClearCommand());
		this.commands.add(new SpawnCommand());
		this.commands.add(new RemoveCommand());
		this.commands.add(new RemoveAllCommand());
		this.commands.add(new SetBackgroundCommand());
		this.commands.add(new SetSpawnCommand());

	}

	public void render(Drawer drawer, Graphics graphics)
	{
		graphics.setFont(getGui().getChatFont());

		int width = getState().getGame().getContainer().getWidth();
		int height = getState().getGame().getContainer().getHeight();

		String entry = ">" + this.content;
		int displayCursor = cursor;

		while(getGui().getChatFont().getWidth(entry) > width / 2)
		{
			if(displayCursor >= entry.length())
			{
				entry = entry.substring(1);
				displayCursor--;
				continue;
			}

			entry = entry.substring(0, entry.length() - 1);
		}

		int y = height - getGui().getChatFont().getLineHeight();

		if(this.isOpen())
		{
			graphics.setColor(new Color(48, 48, 48, 128));
			graphics.fillRect(0, y, width / 2, height);

			graphics.setColor(Color.white);
			graphics.drawString(entry, 0, y);

			if(getState().getScheduler().getGameTimeMillis() % 1000 < 500)
			{
				int cursorX = getGui().getChatFont().getWidth(entry.substring(0, Math.min(displayCursor + 1, entry.length())));

				graphics.drawLine(cursorX, height, cursorX, y);
			}

			graphics.setColor(new Color(64, 64, 64, 128));
			graphics.fillRect(0, y - getGui().getChatFont().getLineHeight() * chatHistory.size(), width / 2, getGui().getChatFont().getLineHeight() * chatHistory.size());

		}

		graphics.setColor(Color.white);
		for(int index = chatHistory.size(); index > 0; index--)
		{
			String line = chatHistory.get(index - 1);

			y -= getGui().getChatFont().getLineHeight();

			graphics.drawString(line, 0, y);
		}
	}

	public void sendMessage(String message)
	{
		if(message.contains("\n"))
			for(String part : message.split("\n"))
				sendMessage(part);

		if(message.length() == 0)
			return;

		chatHistory.add(message);
		System.out.println("CHAT: " + message);
	}
	
	
	public void input(int key, char c)
	{
		if(getState().getGame().getContainer().getInput().isKeyDown(Input.KEY_LCONTROL)
		|| getState().getGame().getContainer().getInput().isKeyDown(Input.KEY_RCONTROL))
		{
			switch(key)
			{
				case Input.KEY_V:
					String input = StringUtil.getClipboardContent();
					content = StringUtil.insert(content, cursor, input);
					cursor += input.length();
					break;
				case Input.KEY_C:
					StringUtil.setClipboardContent(content);
					break;

				case Input.KEY_X:
					StringUtil.setClipboardContent(content);
					content = "";
					break;
			}
			cursorAutoReplace();
			return;
		}

		switch(key)
		{
			case Input.KEY_ENTER:
				execute();
				close();
				break;

			case Input.KEY_ESCAPE:
				close();
				break;

			case Input.KEY_BACK:
				content = StringUtil.backspace(content, cursor);
				cursor--;
				break;

			case Input.KEY_DELETE:
				content = StringUtil.backspace(content, cursor + 1);
				break;

			case Input.KEY_LEFT:
				cursor--;
				break;

			case Input.KEY_RIGHT:
				cursor++;
				break;

			case Input.KEY_UP:
				if(cmdHistoryIndex >= commandHistory.size())
					break;

				cmdHistoryIndex++;
				this.content = commandHistory.get(commandHistory.size() - cmdHistoryIndex);
				cursor = content.length();
				break;

			case Input.KEY_DOWN:
				if(cmdHistoryIndex <= 0)
					break;

				cmdHistoryIndex--;

				if(cmdHistoryIndex == 0)
					this.content = "";
				else
					this.content = commandHistory.get(commandHistory.size() - cmdHistoryIndex);
				cursor = content.length();
				break;

			case Input.KEY_F1:
			case Input.KEY_F2:
			case Input.KEY_F3:
			case Input.KEY_F4:
			case Input.KEY_F5:
			case Input.KEY_F6:
			case Input.KEY_F7:
			case Input.KEY_F8:
			case Input.KEY_F9:
			case Input.KEY_F10:
			case Input.KEY_F11:
			case Input.KEY_F12:
			case Input.KEY_F13:
			case Input.KEY_F14:
			case Input.KEY_F15:
			case Input.KEY_LALT:
			case Input.KEY_RALT:
			case Input.KEY_LCONTROL:
			case Input.KEY_RCONTROL:
			case Input.KEY_LSHIFT:
			case Input.KEY_RSHIFT:
			case Input.KEY_LWIN:
			case Input.KEY_RWIN:
			case Input.KEY_HOME:
			case Input.KEY_INSERT:
			case Input.KEY_TAB:
			case Input.KEY_STOP:
			case Input.KEY_NUMLOCK:
			case Input.KEY_CAPITAL:
				break;

			default:
				content = StringUtil.insert(content, cursor, c);
				cursor++;
		}

		cursorAutoReplace();
	}

	public void close()
	{
		this.content = "";
		this.open = false;
		this.cursor = 0;
		this.cmdHistoryIndex = 0;
	}

	private void cursorAutoReplace()
	{
		while(cursor < 0)
			cursor++;

		while(cursor > content.length())
			cursor--;
	}

	public void execute()
	{
		if(this.content.length() == 0)
			return;

		this.commandHistory.add(this.content);
		this.cmdHistoryIndex = 0;

		try
		{
			String label = null;
			List<String> argsList = new ArrayList<>();

			while(Character.isWhitespace(this.content.charAt(0)))
			{
				this.content = this.content.substring(1);
				if(this.content.length() == 0)
					return;
			}

			if(!this.content.startsWith("/"))
			{
				sendMessage(this.content);
				return;
			}

			this.content = this.content.substring(1);

			for(String part : this.content.split(" "))
			{
				if(part.length() == 0)
					continue;

				if(label == null)
					label = part;
				else
					argsList.add(part);
			}

			Command command = findCommand(label);

			if(command == null)
			{
				sendMessage("Command \"" + label + "\" not found.");
				return;
			}

			command.execute(this, label, argsList.toArray(new String[argsList.size()]));

		}
		catch(Exception e)
		{
			sendMessage("An unexpected error occurred while executing this command");
			Log.error("Error while executing command", e);
		}

		close();
	}


	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public boolean isOpen()
	{
		return open;
	}

	public void setOpen(boolean open)
	{
		this.open = open;
	}

	public List<String> getChatHistory()
	{
		return chatHistory;
	}

	public AdventureState getState()
	{
		return gui.getAdventure();
	}

	public Gui getGui()
	{
		return gui;
	}

	public List<Command> getCommands()
	{
		return commands;
	}

	public Command findCommand(String label)
	{
		for(Command current : getCommands())
		{
			if(current.getName().equalsIgnoreCase(label))
			{
				return current;
			}
		}

		for(Command current : getCommands())
		{
			for(String alias : current.getAliases())
			{
				if(alias.equalsIgnoreCase(label))
				{
					return current;
				}
			}
		}

		return null;
	}
}
