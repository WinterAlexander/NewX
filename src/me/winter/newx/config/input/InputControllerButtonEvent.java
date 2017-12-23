package me.winter.newx.config.input;

import org.newdawn.slick.Input;

/**
 *
 * Created by Alexander Winter on 2016-02-08.
 */
public class InputControllerButtonEvent extends InputControllerEvent
{
	private int button;

	public InputControllerButtonEvent(int controller, int button)
	{
		super(controller);
		this.button = button;
	}

	@Override
	public String getName()
	{
		return super.getName() + " Button #" + button;
	}

	@Override
	public boolean equals(Object obj)
	{
		return obj instanceof InputControllerButtonEvent
				&& ((InputControllerButtonEvent)obj).getController() == getController()
				&& ((InputControllerButtonEvent)obj).getButton() == getButton();
	}

	@Override
	public boolean isPressed(Input input)
	{
		return input.isButtonPressed(getController(), getButton());
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
