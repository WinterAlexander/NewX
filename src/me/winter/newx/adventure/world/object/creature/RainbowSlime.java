package me.winter.newx.adventure.world.object.creature;

import me.winter.newx.adventure.physics.Location;
import me.winter.newx.adventure.world.World;
import org.newdawn.slick.Color;

import java.util.Random;

/**
 *
 * Created by Alexander Winter on 2016-02-12.
 */
public class RainbowSlime extends Slime
{
	private int sync;

	public RainbowSlime(World world)
	{
		super(world);
	}

	public RainbowSlime(World world, Location loc, double width, double height, int health, double speed)
	{
		super(world, loc, null, width, height, health, speed);

		this.sync = new Random().nextInt((int)getCycle());
	}

	public Color getColor()
	{
		long time = (getScheduler().getGameTimeMillis() + sync) % getCycle();
		int part = (int)(6 * time / getCycle());

		float x = (float)time % (getCycle() / 6f) / (getCycle() / 6f);

		switch(part)
		{
			case 0:
				return new Color(1, x, 0);
			case 1:
				return new Color(1 - x, 1, 0);
			case 2:
				return new Color(0, 1, x);
			case 3:
				return new Color(0, 1 - x, 1);
			case 4:
				return new Color(x, 0, 1);
			case 5:
				return new Color(1, 0, 1 - x);


			default:
				return new Color(0.5f, 0.5f, 0.5f);
		}

	}

	public long getCycle()
	{
		return 5000;
	}
}
