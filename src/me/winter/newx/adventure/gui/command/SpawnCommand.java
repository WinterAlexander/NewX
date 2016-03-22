package me.winter.newx.adventure.gui.command;

import me.winter.newx.adventure.gui.Chat;
import me.winter.newx.adventure.physics.Direction;
import me.winter.newx.adventure.physics.Location;
import me.winter.newx.adventure.world.object.WorldObject;
import me.winter.newx.util.StringUtil;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * Created by 1541869 on 2016-02-19.
 */
public class SpawnCommand implements Command
{
	@Override
	public String getName()
	{
		return "spawn";
	}

	@Override
	public List<String> getAliases()
	{
		return Arrays.asList("add", "addobject");
	}

	@Override
	public String getDescription()
	{
		return "Spawns an object with the specified arguments";
	}

	@Override
	public String getUsage()
	{
		return "/spawn <object> [args ...]";
	}

	@Override
	public void execute(Chat chat, String label, String[] args)
	{
		if(args.length == 0)
		{


			return;
		}

		int constructorId = 0;
		String[] classPath = args[0].toLowerCase().split(Pattern.quote("."));

		classPath[classPath.length - 1] = StringUtil.capitalize(classPath[classPath.length - 1]);

		String className = StringUtil.join(classPath, ".");

		if(className.contains("~") && className.split("~").length == 2)
		{
			constructorId = Integer.parseInt(className.split("~")[1]);
			className = className.split("~")[0];
		}

		Class<?> objectClass = null;

		String[] paths = new String[]{
				"me.winter.newx.adventure.world.object." + className,
				"me.winter.newx.adventure.world.object.elements" + className,
				"me.winter.newx.adventure.world.object.creature" + className, };

		for(String path : paths) try
		{
			objectClass = Class.forName(path);
			break;
		}
		catch(ClassNotFoundException e) {}

		if(objectClass == null)
		{
			chat.sendMessage(className + " object could not be recognized");
			return;
		}

		if(constructorId >= objectClass.getConstructors().length || constructorId < 0)
		{
			chat.sendMessage("Constructor ID must be between 0 and " + objectClass.getConstructors().length + " for " + objectClass.getSimpleName() + " object");
			return;
		}

		Constructor<?> constructor = objectClass.getConstructors()[constructorId];

		Type[] constructArgs = constructor.getGenericParameterTypes();

		System.arraycopy(constructArgs, 1, constructArgs, 0, constructArgs.length - 1);

		if(args.length != constructor.getParameterCount())
		{
			chat.sendMessage("Missing arguments: " + StringUtil.join(constructArgs));
			return;
		}

		Object[] params = new Object[args.length];

		params[0] = chat.getState().getWorld();

		for(int index = 1; index < args.length; index++)
		{
			if(args[index].equalsIgnoreCase("x"))
				params[index] = chat.getGui().getPin().getX();

			else if(args[index].equalsIgnoreCase("y"))
				params[index] = chat.getGui().getPin().getY();

			else if(args[index].equalsIgnoreCase("loc"))
				params[index] = new Location(chat.getGui().getPin(), Direction.RIGHT);

			else if(args[index].startsWith("color(")) try
			{
				args[index] = args[index].replace("color(", "");
				String[] parts = args[index].substring(0, args[index].length() - 1).split(",");

				if(parts.length == 3)
					params[index] = new Color(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));

				else if(parts.length == 4)
					params[index] = new Color(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));

				else
					throw new IndexOutOfBoundsException("Colors requires 3 or 4 colors");
			}
			catch(Exception e)
			{
				chat.sendMessage("Argument " + index + " is invalid. colors should be given as \"color(r,b,g)\" or \"color(r,b,g,a)\"");
				return;
			}


			else if(StringUtil.isInt(args[index]))
				params[index] = Integer.parseInt(args[index]);

			else if(StringUtil.isDouble(args[index]))
				params[index] = Double.parseDouble(args[index]);

			else if(StringUtil.isFloat(args[index]))
				params[index] = Float.parseFloat(args[index]);

			else if(StringUtil.isLong(args[index]))
				params[index] = Long.parseLong(args[index]);

			else
				params[index] = args[index];
		}
		try
		{
			Object object = constructor.newInstance(params);
			chat.getState().getWorld().getWorldObjects().add((WorldObject)object);
			chat.sendMessage("The object has been spawned");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}


	}
}
