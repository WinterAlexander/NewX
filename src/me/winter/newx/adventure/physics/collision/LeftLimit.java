package me.winter.newx.adventure.physics.collision;

import me.winter.newx.adventure.physics.Direction;
import me.winter.newx.adventure.physics.Vector;

/**
 * A limits that blocks movements from left to right
 *
 * Created by Alexander Winter on 2016-01-19.
 */
public class LeftLimit extends VerticalLimit
{
	public LeftLimit(double x, double y1, double y2)
	{
		super(x, y1, y2);
	}

	@Override
	public boolean collides(Vector vector, Limit paramLimit, boolean penetration)
	{
		if(!(paramLimit instanceof RightLimit))
			return false;

		RightLimit limit = (RightLimit)paramLimit;

		vector.round(2);
		limit.round(2);

		return limit.getX() + vector.getX() >= this.getX() &&
				limit.getX() <= this.getX() &&
				this.contains(limit, vector.getYFromX(this.getX() - limit.getX()));
	}

	@Override
	public Vector replace(Vector vector, Limit paramLimit)
	{
		if(!(paramLimit instanceof RightLimit))
			return vector;

		RightLimit limit = (RightLimit)paramLimit;

		vector.round(2);
		limit.round(2);

		if(vector.getX() > 0)//si c'est un vert
			return new Vector(this.getX() - limit.getX(), vector.getY());

		return vector;
	}

	@Override
	public Direction getBlockDirection()
	{
		return Direction.RIGHT;
	}

	@Override
	public boolean isTouching(Limit paramLimit)
	{
		if(!(paramLimit instanceof RightLimit))
			return false;

		RightLimit limit = (RightLimit)paramLimit;

		limit.round(2);
		this.round(2);

		return limit.getX() == this.getX() && this.contains(limit, 0);
	}

	@Override
	public boolean canSubstitute(Limit limit)
	{
		if(!(limit instanceof LeftLimit))
			return false;

		return ((LeftLimit)limit).getX() >= this.getX();
	}

}
