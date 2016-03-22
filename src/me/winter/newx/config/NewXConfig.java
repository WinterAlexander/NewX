package me.winter.newx.config;

import me.winter.newx.util.FileUtil;
import org.json.JSONObject;
import org.newdawn.slick.util.Log;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * Created by 1541869 on 2016-02-29.
 */
public class NewXConfig
{
	private float musicVolume;
	private float soundVolume;

	private boolean vSync;
	private boolean fullscreen;

	private int windowsWidth;
	private int windowsHeight;

	private String langFile;

	public NewXConfig()
	{
		this.musicVolume = 1f;
		this.soundVolume = 1f;
		this.vSync = true;
		this.fullscreen = false;

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		this.windowsWidth = (int)screenSize.getWidth() * 2 / 3;
		this.windowsHeight = (int)screenSize.getHeight() * 2 / 3;

		this.langFile = "french.lang";
	}

	public void load(File file)
	{
		if(!file.exists() || !file.isFile())
			return;

		String jsonFile = "";

		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(file));

			while(true)
			{
				String line = reader.readLine();

				if(line == null)
					break;

				jsonFile += line;
			}

			reader.close();
		}
		catch(IOException exception)
		{
			Log.error("Couldn't load settings in file " + file.getName(), exception);
			return;
		}

		JSONObject jsonObject = new JSONObject(jsonFile);

		for(String key : jsonObject.keySet()) try
		{
			Field field = getClass().getDeclaredField(key);

			if(!field.isAccessible())
				field.setAccessible(true);

			if(Modifier.isTransient(field.getModifiers()))
				continue;

			field.set(this, jsonObject.get(key));
		}
		catch(NoSuchFieldException | SecurityException | IllegalAccessException | IllegalArgumentException exception)
		{
			Log.error("Invalid field in " + file.getName() + " settings file, ignoring it", exception);
		}
	}

	public void save(File file)
	{
		try
		{
			FileUtil.createFile(file);
		}
		catch(IOException exception)
		{
			Log.error("Couldn't create file " + file.getName() + " to save settings", exception);
			return;
		}

		JSONObject jsonObject = new JSONObject();

		List<Field> fields = new ArrayList<>(Arrays.asList(getClass().getDeclaredFields()));

		fields.sort((x, y) -> x.getName().compareToIgnoreCase(y.getName()));

		for(Field field : fields) try
		{
			if(!field.isAccessible())
				field.setAccessible(true);

			if(Modifier.isTransient(field.getModifiers()))
				continue;

			jsonObject.put(field.getName(), field.get(this));
		}
		catch(IllegalAccessException exception)
		{
			Log.error("Couldn't save field " + field.getName() + " in settings", exception);
		}

		try
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));

			writer.write(jsonObject.toString(4));

			writer.flush();
			writer.close();
		}
		catch(IOException exception)
		{
			Log.error("Couldn't save settings in file " + file.getName(), exception);
		}
	}

	public float getMusicVolume()
	{
		return musicVolume;
	}

	public void setMusicVolume(float musicVolume)
	{
		this.musicVolume = musicVolume;
	}

	public float getSoundVolume()
	{
		return soundVolume;
	}

	public void setSoundVolume(float soundVolume)
	{
		this.soundVolume = soundVolume;
	}

	public boolean isvSync()
	{
		return vSync;
	}

	public void setvSync(boolean vSync)
	{
		this.vSync = vSync;
	}

	public boolean isFullscreen()
	{
		return fullscreen;
	}

	public void setFullscreen(boolean fullscreen)
	{
		this.fullscreen = fullscreen;
	}

	public int getWindowsWidth()
	{
		return windowsWidth;
	}

	public void setWindowsWidth(int windowsWidth)
	{
		this.windowsWidth = windowsWidth;
	}

	public int getWindowsHeight()
	{
		return windowsHeight;
	}

	public void setWindowsHeight(int windowsHeight)
	{
		this.windowsHeight = windowsHeight;
	}

	public String getLangFile()
	{
		return langFile;
	}

	public void setLangFile(String langFile)
	{
		this.langFile = langFile;
	}

}
