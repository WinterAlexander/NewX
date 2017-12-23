package me.winter.newx.config.input;

import me.winter.newx.util.StringUtil;
import org.newdawn.slick.Input;

/**
 *
 * Created by Alexander Winter on 2016-02-05.
 */
public class InputKeyEvent implements InputEvent
{
	private int keyCode;

	public InputKeyEvent(int keyCode)
	{
		this.keyCode = keyCode;
	}

	@Override
	public String getName()
	{
		return StringUtil.capitalize(Input.getKeyName(keyCode));
	}

	@Override
	public boolean equals(Object obj)
	{
		return obj instanceof InputKeyEvent && ((InputKeyEvent)obj).getKeyCode() == getKeyCode();
	}

	@Override
	public boolean isPressed(Input input)
	{
		return input.isKeyDown(keyCode);
	}

	public int getKeyCode()
	{
		return keyCode;
	}

	public void setKeyCode(int keyCode)
	{
		this.keyCode = keyCode;
	}
}
