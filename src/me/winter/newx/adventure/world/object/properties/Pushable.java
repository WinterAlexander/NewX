package me.winter.newx.adventure.world.object.properties;

import me.winter.newx.adventure.physics.collision.Limit;

/**
 *
 * Created by winter on 17/02/16.
 */
public interface Pushable extends Touchable, Movable
{
	@Override
	default void onTouch(Touchable body, Limit limit)
	{
		if(!(body instanceof Body))
			return;

		//getVelocity().add(((Body)body).getVelocity().clone().multiply(0.5));
	}
}
