package me.winter.newx.adventure.world.object.elements;

import me.winter.newx.adventure.physics.Location;
import me.winter.newx.adventure.world.World;
import me.winter.newx.adventure.world.object.WorldObject;
import me.winter.newx.adventure.world.object.proprieties.Localizable;
import me.winter.newx.adventure.world.object.proprieties.Solid;
import me.winter.newx.adventure.world.object.proprieties.Visible;
import me.winter.newx.adventure.physics.collision.CollisionBox;
import me.winter.newx.adventure.physics.collision.RectangleCollisionBox;

public abstract class Rectangle extends WorldObject implements Solid, Visible, Localizable
{
	private float stability;
	private Location location;
	private double width, height;

	public Rectangle(World world, double x, double y, double width, double height)
	{
		this(world, x, y, width, height, 1f);
	}

	public Rectangle(World world, double x, double y, double width, double height, float stability)
	{
		this(world, new Location(x, y), width, height, stability);
	}

	public Rectangle(World world, Location loc, double width, double height)
	{
		this(world, loc, width, height, 1f);
	}

	public Rectangle(World world, Location loc, double width, double height, float stability)
	{
		super(world);
		this.location = loc;
		this.width = width;
		this.height = height;
		this.stability = stability;
	}

	@Override
	public CollisionBox getCollisionBox()
	{
		return new RectangleCollisionBox(this, this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.stability);
	}


	public double getX()
	{
		return getLocation().getX();
	}

	public void setX(double x)
	{
		this.getLocation().setX(x);
	}

	public double getY()
	{
		return getLocation().getY();
	}

	public void setY(double y)
	{
		this.getLocation().setY(y);
	}

	public Location getLocation()
	{
		return location;
	}

	public void setLocation(Location location)
	{
		this.location = location;
	}

	public double getHeight()
	{
		return height;
	}

	public void setHeight(double height)
	{
		this.height = height;
	}

	public double getWidth()
	{
		return width;
	}

	public void setWidth(double width)
	{
		this.width = width;
	}

	public float getStability()
	{
		return stability;
	}

	public void setStability(float stability)
	{
		this.stability = stability;
	}
}
