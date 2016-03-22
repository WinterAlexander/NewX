package me.winter.newx.adventure.physics;

import java.io.Serializable;

import me.winter.newx.util.MathUtil;


public class Location implements Serializable
{
	private double x;
	private double y;
	private Direction direction;

	public Location(double x, double y)
	{
		this.x = x;
		this.y = y;
		this.direction = null;
	}

	public Location(double x, double y, Direction direction)
	{
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	public Location(Location location)
	{
		this.x = location.getX();
		this.y = location.getY();
		this.direction = location.getDirection();
	}

	public Location(Location location, Direction direction)
	{
		this.x = location.getX();
		this.y = location.getY();
		this.direction = direction;
	}

	public Location round(int decimals)
	{
		this.x = MathUtil.round(this.x, decimals);
		this.y = MathUtil.round(this.y, decimals);
		return this;
	}
	
	public String toString()
	{
		return this.x + "," + this.y + ":" + this.direction.toChar();
	}
	
	public Location add(Vector vector)
	{
		this.x += vector.getX();
		this.y += vector.getY();
		return this;
	}

	public Location add(double x, double y)
	{
		this.x += x;
		this.y += y;
		return this;
	}
	
	public Location invert()
	{
		this.direction = Direction.reverseOf(this.direction);
		return this;
		
	}

	public Location clone()
	{
		return new Location(this);
	}

	public double distance(Location location)
	{
		return Math.sqrt(Math.pow(this.x - location.x, 2) + Math.pow(this.y - location.y, 2));
	}

	public Vector difference(Location location)
	{
		return new Vector(location.getX() - getX(), location.getY() - getY());
	}

	public double getX()
	{
		return x;
	}

	public void setX(double x)
	{
		this.x = x;
	}

	public double getY()
	{
		return y;
	}

	public void setY(double y)
	{
		this.y = y;
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
