package me.winter.newx.adventure.world.object.proprieties;

import me.winter.newx.adventure.physics.collision.Limit;

/**
 *
 * Created by winter on 17/02/16.
 */
public interface Pushable extends Touchable, Movable
{
	@Override
	default void onTouch(Solid solid, Limit limit, double power)
	{
		if(power < 0)
			return;

		power /= 2;

		switch(limit.getBlockDirection())
		{
			case RIGHT:
				getVelocity().add(power, 0);
				break;

			case LEFT:
				getVelocity().add(-power, 0);
				break;

			case UP:
				getVelocity().add(0, power);
				break;

			case DOWN:
				getVelocity().add(0, -power);
				break;
		}
	}
}
