package me.winter.newx.adventure.world.object.elements;

import me.winter.newx.adventure.Drawer;
import me.winter.newx.adventure.world.object.WorldObject;
import me.winter.newx.adventure.world.object.properties.Visible;
import me.winter.newx.adventure.world.World;

public class AppleTree extends WorldObject implements Visible
{
	private double x, y, width, height;

	public AppleTree(World world, double x, double y, double width, double height)
	{
		super(world);

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public void render(Drawer d)
	{
		d.drawImage(getResourceManager().getImage("object_appletree"), x, y, width, height);
	}

	@Override
	public int getPriority()
	{
		return Visible.MEDIUM_PRIORITY;
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
