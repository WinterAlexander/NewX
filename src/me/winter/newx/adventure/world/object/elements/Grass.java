package me.winter.newx.adventure.world.object.elements;

import me.winter.newx.adventure.physics.Location;
import me.winter.newx.adventure.world.World;
import me.winter.newx.adventure.Drawer;
import me.winter.newx.adventure.world.object.WorldObject;
import me.winter.newx.adventure.world.object.proprieties.Localizable;
import me.winter.newx.adventure.world.object.proprieties.Visible;

public class Grass extends WorldObject implements Visible, Localizable
{
	private Location loc;
	private double width, height;

	public Grass(World world, Location loc, double width, double height)
	{
		super(world);
		this.loc = loc;
		this.width = width;
		this.height = height;
	}

	@Override
	public void render(Drawer d)
	{
		d.drawImage(getResourceManager().getImage("object_grass"), getLocation().getX(), getLocation().getY(), getWidth(), getHeight());
	}

	@Override
	public int getPriority()
	{
		return Visible.LOW_PRIORITY;
	}

	@Override
	public Location getLocation()
	{
		return loc;
	}

	public double getWidth()
	{
		return width;
	}

	public void setWidth(double width)
	{
		this.width = width;
	}

	public double getHeight()
	{
		return height;
	}

	public void setHeight(double height)
	{
		this.height = height;
	}
}
