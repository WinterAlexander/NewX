package me.winter.newx.config.input;

import org.newdawn.slick.Input;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by 1541869 on 2016-02-05.
 */
public class InputLayout
{
	private Input input;
	private HashMap<InputAction, InputEvent> inputMap;

	public InputLayout()
	{
		this.input = null;
		this.inputMap = new HashMap<>();

		this.inputMap.put(InputAction.SPRINT, new InputKeyEvent(Input.KEY_LSHIFT));
		this.inputMap.put(InputAction.JUMP, new InputKeyEvent(Input.KEY_SPACE));
		this.inputMap.put(InputAction.CROUCH, new InputKeyEvent(Input.KEY_S));
		this.inputMap.put(InputAction.HIT, new InputMouseClickEvent(Input.MOUSE_LEFT_BUTTON));
		this.inputMap.put(InputAction.LEFT, new InputKeyEvent(Input.KEY_A));
		this.inputMap.put(InputAction.RIGHT, new InputKeyEvent(Input.KEY_D));
	}

	public InputAction getAction(InputEvent event)
	{
		for(Map.Entry<InputAction, InputEvent> entry : inputMap.entrySet())
			if(entry.getValue().equals(event))
				return entry.getKey();

		return null;
	}

	public boolean isPressed(InputAction action)
	{
		if(input == null)
			return false;

		return inputMap.get(action).isPressed(input);
	}

	public String getInputName(InputAction action)
	{
		return inputMap.get(action).getName();
	}

	public HashMap<InputAction, InputEvent> getInputMap()
	{
		return inputMap;
	}

	public void setInputMap(HashMap<InputAction, InputEvent> inputMap)
	{
		this.inputMap = inputMap;
	}

	public Input getInput()
	{
		return input;
	}

	public void setInput(Input input)
	{
		this.input = input;
	}

	public void load(File file)
	{
		if(file == null || !file.exists() || !file.isFile())
			return;

		try
		{
			ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));

			this.inputMap = (HashMap)inputStream.readObject();


			inputStream.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return;
		}
	}

	public void save(File file)
	{
		try
		{
			if(!file.isFile())
				file.delete();

			if(!file.exists())
				file.createNewFile();

			ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));

			outputStream.writeObject(this.inputMap);

			outputStream.flush();
			outputStream.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return;
		}
	}
}
