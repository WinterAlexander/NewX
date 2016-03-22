package me.winter.newx.adventure.physics.collision;

import me.winter.newx.adventure.physics.Direction;
import me.winter.newx.adventure.physics.Location;
import me.winter.newx.adventure.physics.Vector;

public interface Limit
{
	boolean collides(Vector vector, Limit limit, boolean penetration);
	double getPriority(Vector vector, Limit limit);
	Vector replace(Vector vector, Limit limit);

	Direction getBlockDirection();
	boolean isTouching(Limit limit);

	default boolean isTouching(CollisionBox box)
	{
		if(box.hasLimit(this))
			return false;

		for(Limit limit : box.getLimits())
			if(isTouching(limit))
				return true;
		return false;
	}

	boolean canSubstitute(Limit limit);

	void round(int decimals);

	Location getStart();
	Location getEnd();
}
