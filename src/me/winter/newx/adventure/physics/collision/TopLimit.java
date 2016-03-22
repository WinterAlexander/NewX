package me.winter.newx.adventure.physics.collision;

import me.winter.newx.adventure.physics.Direction;
import me.winter.newx.adventure.physics.Vector;

/**
 * The limit of the top of a box
 * Blocks movements from up to down
 * Represented by a red color
 *
 * Created by Alexander Winter on 2016-01-19.
 */
public class TopLimit extends HorizontalLimit
{
	private boolean penetrable;
	private float stability;

	public TopLimit(double y, double x1, double x2)
	{
		this(y, x1, x2, false);
	}

	public TopLimit(double y, double x1, double x2, boolean penetrable)
	{
		this(y, x1, x2, penetrable, 1f);
	}

	public TopLimit(double y, double x1, double x2, boolean penetrable, float stability)
	{
		super(y, x1, x2);
		this.penetrable = penetrable;
		this.stability = stability;
	}

	@Override
	public Direction getBlockDirection()
	{
		return Direction.DOWN;
	}

	@Override
	public boolean collides(Vector vector, Limit paramLimit, boolean penetration)
	{
		if(!(paramLimit instanceof BottomLimit))
			return false;

		BottomLimit limit = (BottomLimit)paramLimit;

		if(this.isPenetrable() && penetration)
			return false;

		vector.round(2);
		limit.round(2);

		//IM HUERE

		return limit.getY() + vector.getY() <= this.getY()
				&& limit.getY() >= this.getY()
				&& this.contains(limit, vector.getXFromY(this.getY() - limit.getY()));
	}


	@Override
	public Vector replace(Vector vector, Limit paramLimit)
	{
		if(!(paramLimit instanceof BottomLimit))
			return vector;

		BottomLimit limit = (BottomLimit)paramLimit;

		vector.round(2);
		limit.round(2);

		if(vector.getY() < 0) //rouge
			return new Vector(vector.getX(), this.getY() - limit.getY());


		return vector;
	}

	@Override
	public boolean isTouching(Limit paramLimit)
	{
		if(!(paramLimit instanceof BottomLimit))
			return false;

		BottomLimit limit = (BottomLimit)paramLimit;

		limit.round(2);
		this.round(2);

		return limit.getY() == this.getY() && this.contains(limit, 0);
	}



	@Override
	public boolean canSubstitute(Limit limit)
	{
		if(!(limit instanceof TopLimit))
			return false;

		return ((TopLimit)limit).getY() <= this.getY();
	}

	public float getStability()
	{
		return stability;
	}

	public boolean isPenetrable()
	{
		return this.penetrable;
	}
}
