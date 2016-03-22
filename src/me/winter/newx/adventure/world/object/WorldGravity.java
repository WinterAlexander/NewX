package me.winter.newx.adventure.world.object;

import me.winter.newx.adventure.world.World;

import java.io.Serializable;


public class WorldGravity extends WorldObject implements Serializable
{
	public static final double GRAVITY_EARTH = 9.80665;

	private double gravity;

	public WorldGravity(World world)
	{
		this(world, GRAVITY_EARTH);
	}

	public WorldGravity(World world, double gravity)
	{
		super(world);
		this.gravity = gravity;
	}

	/**
	 * Return the Y movement a gravity affected should add to it's velocity every tick
	 *
	 * @return
	 */
	public double getDeltaY()
	{
		double deltaY = -0.075 * (gravity / GRAVITY_EARTH);

		if(deltaY > -0.01)
			return -0.01;

		return deltaY;
	}
}

