package me.winter.newx.resource;

import org.json.JSONException;
import org.json.JSONObject;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.openal.*;
import org.newdawn.slick.openal.NullAudio;
import org.newdawn.slick.util.Log;
import org.newdawn.slick.util.ResourceLoader;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Created by 1541869 on 2016-02-25.
 */
public class SimpleResourceManager implements ResourceManager
{
	private HashMap<String, Object> resources;
	private int remaining;

	public SimpleResourceManager()
	{
		resources = new HashMap<>();
		remaining = -1;
	}

	@Override
	public void load()
	{
		JSONObject fonts, images, sounds;

		try
		{
			InputStream jsonStream = ResourceLoader.getResourceAsStream("paths.json");

			//if(jsonStream == null)
			//	throw new FileNotFoundException("Can't open stream paths.json");

			BufferedReader reader = new BufferedReader(new InputStreamReader(jsonStream));

			String jsonFile = "";

			while(true)
			{
				String line = reader.readLine();

				if(line == null)
					break;

				jsonFile += line;
			}


			JSONObject main = new JSONObject(jsonFile);

			fonts = main.getJSONObject("fonts");
			images = main.getJSONObject("images");
			sounds = main.getJSONObject("sounds");

		}
		catch(FileNotFoundException e)
		{
			Log.error("Couldn't find paths.json file, please redownload the game.", e);
			return;
		}
		catch(IOException e)
		{
			Log.error("Couldn't read paths.json file. Unable to get resources paths, please redownload the game.", e);
			return;
		}
		catch(JSONException e)
		{
			Log.error("paths.json file content is corrupted, please redownload the game.", e);
			return;
		}

		remaining = fonts.keySet().size() + images.keySet().size() + sounds.keySet().size();

		for(String fontName : fonts.keySet()) try
		{
			resources.put(fontName, Font.createFont(Font.TRUETYPE_FONT, ResourceLoader.getResourceAsStream("fonts/" + fonts.getString(fontName))));
			Log.info("Font \"" + fontName + "\" loaded (" + resources.size() + "/" + (resources.size() + remaining - 1) + ")");
		}
		catch(FontFormatException | IOException exception)
		{
			Log.error("Could not load font " + fontName, exception);
		}
		finally
		{
			remaining--;
		}

		for(String imageName : images.keySet()) try
		{
			resources.put(imageName, new Image("gfx/" + images.getString(imageName)));
			Log.info("Image \"" + imageName + "\" loaded (" + resources.size() + "/" + (resources.size() + remaining - 1) + ")");
		}
		catch(SlickException exception)
		{
			Log.error("Could not load image " + imageName, exception);
		}
		finally
		{
			remaining--;
		}

		for(String soundName : sounds.keySet()) try
		{
			resources.put(soundName, AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("sfx/" + sounds.getString(soundName))));
			Log.info("Sound \"" + soundName + "\" loaded (" + resources.size() + "/" + (resources.size() + remaining - 1) + ")");
		}
		catch(IOException exception)
		{
			Log.error("Could not load sound " + soundName, exception);
		}
		finally
		{
			remaining--;
		}
	}

	@Override
	public void dispose()
	{
		for(String imageName : getImages()) try
		{
			getImage(imageName).destroy();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		for(String soundName : getAudios()) try
		{
			getAudio(soundName).stop();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		resources.clear();
	}

	@Override
	public float getLoadingProgression()
	{
		if(remaining <= 0)
			return 0;

		return (float)resources.size() / (float)(remaining + resources.size());
	}

	@Override
	public Image getImage(String path)
	{
		if(!resources.containsKey(path) || !(resources.get(path) instanceof Image))
			try
			{
				return new Image(100, 100);
			}
			catch(SlickException exception)
			{
				Log.error("Failed to create void image for non-existant images", exception);
				return null;
			}


		return (Image)resources.get(path);
	}

	@Override
	public Font getFont(String path)
	{
		if(!resources.containsKey(path) || !(resources.get(path) instanceof Font))
			return new JLabel().getFont();

		return (Font)resources.get(path);
	}

	@Override
	public Audio getAudio(String path)
	{
		if(!resources.containsKey(path) || !(resources.get(path) instanceof Audio))
			return new NullAudio();

		return (Audio)resources.get(path);
	}

	@Override
	public String[] getImages()
	{
		List<String> images = new ArrayList<>();

		for(Map.Entry<String, Object> entry : resources.entrySet())
			if(entry.getValue() instanceof Image)
				images.add(entry.getKey());

		return images.toArray(new String[images.size()]);
	}

	@Override
	public String[] getFonts()
	{
		List<String> fonts = new ArrayList<>();

		for(Map.Entry<String, Object> entry : resources.entrySet())
			if(entry.getValue() instanceof Font)
				fonts.add(entry.getKey());

		return fonts.toArray(new String[fonts.size()]);
	}

	@Override
	public String[] getAudios()
	{
		List<String> sounds = new ArrayList<>();

		for(Map.Entry<String, Object> entry : resources.entrySet())
			if(entry.getValue() instanceof Audio)
				sounds.add(entry.getKey());

		return sounds.toArray(new String[sounds.size()]);
	}
}
