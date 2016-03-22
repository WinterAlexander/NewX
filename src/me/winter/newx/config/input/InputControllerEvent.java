package me.winter.newx.config.input;


/**
 *
 * Created by 1541869 on 2016-02-08.
 */
public abstract class InputControllerEvent implements InputEvent
{
	private int controller;

	public InputControllerEvent(int controller)
	{
		this.controller = controller;
	}

	public String getName()
	{
		return "Joy #" + controller;
	}

	public int getController()
	{
		return controller;
	}

	public void setController(int controller)
	{
		this.controller = controller;
	}
}
