package me.winter.newx.config.input;

import org.newdawn.slick.Input;

/**
 *
 * Created by Alexander Winter on 2016-02-05.
 */
public class InputMouseClickEvent implements InputEvent
{
	private int button;

	public InputMouseClickEvent(int button)
	{
		this.button = button;
	}

	@Override
	public String getName()
	{
		return "Mouse Button #" + button;
	}

	@Override
	public boolean isPressed(Input input)
	{
		return input.isMouseButtonDown(this.button);
	}

	public int getButton()
	{
		return button;
	}

	public void setButton(int button)
	{
		this.button = button;
	}
}
