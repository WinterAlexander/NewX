package me.winter.newx.adventure.world.object.elements;

import me.winter.newx.adventure.physics.Location;
import me.winter.newx.adventure.world.World;

/**
 *
 * Created by Alexander Winter on 2016-02-12.
 */
public abstract class Square extends Rectangle
{
	public Square(World world, double x, double y, double width)
	{
		super(world, x, y, width, width);
	}

	public Square(World world, double x, double y, double width, float stability)
	{
		super(world, x, y, width, width, stability);
	}

	public Square(World world, Location location, double width)
	{
		super(world, location, width, width);
	}

	public Square(World world, Location location, double width, float stability)
	{
		super(world, location, width, width, stability);
	}
}
