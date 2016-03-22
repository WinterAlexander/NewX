package me.winter.newx.adventure.physics;

import java.io.Serializable;

public enum Direction implements Serializable
{
	LEFT(),
	RIGHT(),
	UP(),
	DOWN();

	public static Direction reverseOf(Direction direction)
	{
		if(direction == LEFT)
			return RIGHT;
		if(direction == RIGHT)
			return LEFT;
		if(direction == UP)
			return DOWN;
		if(direction == DOWN)
			return UP;
		return null;
	}

	public char toChar()
	{
		if(this == LEFT)
			return 'L';
		if(this == RIGHT)
			return 'R';
		if(this == UP)
			return 'U';
		if(this == DOWN)
			return 'D';
		return '?';
	}

	public static Direction fromString(String name)
	{
		switch(name.toLowerCase())
		{
			case "up":
			case "^":
			case "top":
			case "north":
				return Direction.UP;

			case "down":
			case "v":
			case "bottom":
			case "south":
				return Direction.DOWN;

			case "left":
			case "<":
			case "west":
				return Direction.LEFT;

			case "right:":
			case ">":
			case "east":
				return Direction.RIGHT;

			default:
				return null;
		}
	}
}
