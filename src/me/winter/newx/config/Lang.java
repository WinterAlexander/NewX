package me.winter.newx.config;

import org.json.JSONObject;
import org.newdawn.slick.util.Log;
import org.newdawn.slick.util.ResourceLoader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 *
 * Created by 1541869 on 2016-02-29.
 */
public class Lang
{
	private HashMap<String, String> quotes;

	public Lang()
	{
		quotes = new HashMap<>();
	}

	public void load(String name)
	{
		try
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(ResourceLoader.getResourceAsStream("lang/" + name)));

			String jsonFile = "", line;

			while((line = reader.readLine()) != null)
				jsonFile += line;

			JSONObject jsonObject = new JSONObject(jsonFile);

			for(String key : jsonObject.keySet())
				quotes.put(key, jsonObject.getString(key));
		}
		catch(Exception exception)
		{
			Log.error("Couldn't load lang file " + name, exception);
		}
	}

	public String get(String key)
	{
		if(quotes.containsKey(key))
			return quotes.get(key);

		return "ERROR: " + key;
	}
}
