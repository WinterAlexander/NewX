package me.winter.newx.adventure.physics.collision;

import me.winter.newx.adventure.physics.Direction;
import me.winter.newx.adventure.physics.Vector;

/**
 *
 * Created by Alexander Winter on 2016-01-19.
 */
public class BottomLimit extends HorizontalLimit
{
	public BottomLimit(double y, double x1, double x2)
	{
		super(y, x1, x2);
	}

	@Override
	public Direction getBlockDirection()
	{
		return Direction.UP;
	}

	@Override
	public boolean collides(Vector vector, Limit paramLimit, boolean penetration)
	{
		if(!(paramLimit instanceof TopLimit))
			return false;

		TopLimit limit = (TopLimit)paramLimit;

		vector.round(2);
		limit.round(2);

		return limit.getY() + vector.getY() >= this.getY() &&
				limit.getY() <= this.getY() &&
				this.contains(limit, vector.getXFromY(this.getY() - limit.getY()));

	}

	/**
	 * TODO vector mutable so resulting vector is the param one
	 * Return a vector for the remaining movement
	 * In short instead of fixing the vector for it to not collide
	 * we cut it in 2 to do the real way
	 */
	@Override
	public Vector replace(Vector vector, Limit paramLimit)
	{
		if(!(paramLimit instanceof TopLimit))
			return vector;

		TopLimit limit = (TopLimit)paramLimit;

		limit.round(2);
		vector.round(2);

		if(vector.getY() > 0)
			return new Vector(vector.getX(), this.getY() - limit.getY());

		return vector;
	}

	@Override
	public boolean isTouching(Limit paramLimit)
	{
		if(!(paramLimit instanceof TopLimit))
			return false;

		TopLimit limit = (TopLimit)paramLimit;

		limit.round(2);
		this.round(2);

		return limit.getY() == this.getY() && this.contains(limit, 0);
	}

	@Override
	public boolean canSubstitute(Limit limit)
	{
		if(!(limit instanceof BottomLimit))
			return false;

		return ((BottomLimit)limit).getY() >= this.getY();
	}
}
