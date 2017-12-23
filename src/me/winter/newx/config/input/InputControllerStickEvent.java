package me.winter.newx.config.input;

import me.winter.newx.adventure.physics.Direction;
import me.winter.newx.util.StringUtil;
import org.newdawn.slick.Input;

/**
 *
 * Created by Alexander Winter on 2016-02-08.
 */
public class InputControllerStickEvent extends InputControllerEvent
{
	private Direction direction;

	public InputControllerStickEvent(int controller, Direction direction)
	{
		super(controller);
		this.direction = direction;
	}

	@Override
	public String getName()
	{
		return super.getName() + " " + StringUtil.capitalize(direction.name().toLowerCase());
	}

	@Override
	public boolean equals(Object obj)
	{
		return obj instanceof InputControllerStickEvent
				&& ((InputControllerStickEvent)obj).getController() == getController()
				&& ((InputControllerStickEvent)obj).getDirection() == getDirection();
	}

	@Override
	public boolean isPressed(Input input)
	{
		switch(direction)
		{
			case UP:
				return input.isControllerUp(getController());

			case DOWN:
				return input.isControllerDown(getController());

			case LEFT:
				return input.isControllerLeft(getController());

			case RIGHT:
				return input.isControllerRight(getController());

			default:
				return false;
		}
	}

	public Direction getDirection()
	{
		return direction;
	}

	public void setDirection(Direction direction)
	{
		this.direction = direction;
	}
}
