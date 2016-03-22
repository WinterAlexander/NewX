package me.winter.newx.adventure.physics.collision;

import me.winter.newx.adventure.physics.Direction;
import me.winter.newx.adventure.physics.Vector;

/**
 * * A limits that blocks movements from right to left
 *
 * Created by Alexander Winter on 2016-01-19.
 */
public class RightLimit extends VerticalLimit
{
	public RightLimit(double x, double y1, double y2)
	{
		super(x, y1, y2);
	}

	@Override
	public boolean collides(Vector vector, Limit paramLimit, boolean penetration)
	{
		if(!(paramLimit instanceof LeftLimit))
			return false;

		LeftLimit limit = (LeftLimit)paramLimit;

		vector.round(2);
		limit.round(2);

		return limit.getX() + vector.getX() <= this.getX() &&
				limit.getX() >= this.getX() &&
				this.contains(limit, vector.getYFromX(this.getX() - limit.getX()));
	}

	@Override
	public Vector replace(Vector vector, Limit paramLimit)
	{
		if(!(paramLimit instanceof LeftLimit))
			return vector;

		LeftLimit limit = (LeftLimit)paramLimit;

		vector.round(2);
		limit.round(2);

		if(vector.getX() < 0)//si c'est un bleu
			return new Vector(this.getX() - limit.getX(), vector.getY());

		return vector;
	}


	@Override
	public Direction getBlockDirection()
	{
		return Direction.LEFT;
	}

	@Override
	public boolean isTouching(Limit paramLimit)
	{
		if(!(paramLimit instanceof LeftLimit))
			return false;

		LeftLimit limit = (LeftLimit)paramLimit;

		limit.round(2);
		this.round(2);

		return limit.getX() == this.getX() && this.contains(limit, 0);
	}

	@Override
	public boolean canSubstitute(Limit limit)
	{
		if(!(limit instanceof RightLimit))
			return false;

		return ((RightLimit)limit).getX() <= this.getX();
	}

}
