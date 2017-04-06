package me.winter.newx.adventure.world.object.properties;

import me.winter.newx.adventure.world.World;

/**
 *
 * Created by winter on 17/02/16.
 */
public interface Furniture extends GravityAffected, Pushable, Visible, Body
{
	World getWorld();

	@Override
	default void move()
	{
		getVelocity().add(0, getWorld().getGravity().getDeltaY());

		setVelocity(getWorld().getNoCollisionVector(getVelocity(), this, false));

		setLocation(getLocation().add(getVelocity()));

		if(getWorld().getBorder() != null && !getWorld().getBorder().contains(getCollisionBox()))
		{
			getWorld().getWorldObjects().remove(this);
			return;
		}

		if(!isOnGround())
			return;

		float stability = getWorld().getStability(getCollisionBox());

		if(stability == 0)
			stability = 1f;

		getVelocity().setX(getVelocity().getX() * (1f - stability));
		getVelocity().setY(0);

	}

}
