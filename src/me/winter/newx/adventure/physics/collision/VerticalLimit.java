package me.winter.newx.adventure.physics.collision;

import me.winter.newx.adventure.physics.Location;
import me.winter.newx.adventure.physics.Vector;
import me.winter.newx.util.MathUtil;

public abstract class VerticalLimit implements Limit
{
	private double x;
	private double y1, y2;
	
	public VerticalLimit(double x, double y1, double y2)
	{
		this.x = x;
		this.y1 = y1;
		this.y2 = y2;
	}

	public boolean contains(VerticalLimit limit, double deltaY)
	{
		deltaY = MathUtil.round(deltaY, 2);
		limit.round(2);
		this.round(2);

		if(limit.getY2() + deltaY <= this.getY1())
			return false;

		return limit.getY1() + deltaY < this.getY2();

	}


	@Override
	public double getPriority(Vector vector, Limit paramLimit)
	{
		if(!(paramLimit instanceof VerticalLimit))
			return -1;

		VerticalLimit limit = (VerticalLimit)paramLimit;

		Vector smallVector = new Vector(this.getX() - limit.getX(), vector.getYFromX(this.getX() - limit.getX()));

		return Math.abs(smallVector.getLength() / vector.getLength());
	}
	
	
	public double getX()
	{
		return MathUtil.round(this.x, 2);
	}
	
	public double getY1()
	{
		return MathUtil.round(this.y1, 2);
	}
	
	public double getY2()
	{
		return MathUtil.round(this.y2, 2);
	}

	@Override
	public void round(int decimals)
	{
		this.x = MathUtil.round(this.x, decimals);
		this.y1 = MathUtil.round(this.y1, decimals);
		this.y2 = MathUtil.round(this.y2, decimals);
	}

	@Override
	public Location getStart()
	{
		Location loc = new Location(this.x, this.y1, this.getBlockDirection());
		loc.round(2);
		return loc;
	}

	@Override
	public Location getEnd()
	{
		Location loc = new Location(this.x, this.y2, this.getBlockDirection());
		loc.round(2);
		return loc;
	}

	@Override
	public boolean equals(Object object)
	{
		if(object.getClass() != getClass())
			return false;

		if(((VerticalLimit)object).getY1() != getY1())
			return false;

		if(((VerticalLimit)object).getY2() != getY2())
			return false;

		if(((VerticalLimit)object).getX() != getX())
			return false;

		return ((VerticalLimit)object).getBlockDirection() == getBlockDirection();
	}
}
