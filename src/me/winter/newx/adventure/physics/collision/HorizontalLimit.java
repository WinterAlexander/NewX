package me.winter.newx.adventure.physics.collision;

import me.winter.newx.adventure.physics.Location;
import me.winter.newx.adventure.physics.Vector;
import me.winter.newx.util.MathUtil;

public abstract class HorizontalLimit implements Limit
{
	private double y;
	private double x1, x2;
	
	public HorizontalLimit(double y, double x1, double x2)
	{
		this.y = y;
		this.x1 = x1;
		this.x2 = x2;
	}

	public boolean contains(HorizontalLimit limit, double deltaX)
	{
		deltaX = MathUtil.round(deltaX, 2);
		limit.round(2);
		this.round(2);

		if(limit.getX2() + deltaX <= this.getX1())
			return false;

		return limit.getX1() + deltaX < this.getX2();

	}

	@Override
	public double getPriority(Vector vector, Limit paramLimit)
	{
		if(!(paramLimit instanceof HorizontalLimit))
			return -1;

		HorizontalLimit limit = (HorizontalLimit)paramLimit;

		Vector smallVector = new Vector(vector.getXFromY(this.getY() - limit.getY()), this.getY() - limit.getY());

		return Math.abs(smallVector.getLength() / vector.getLength());
	}
	
	public double getY()
	{
		return MathUtil.round(this.y, 2);
	}
	
	public double getX1()
	{
		return MathUtil.round(this.x1, 2);
	}
	
	public double getX2()
	{
		return MathUtil.round(this.x2, 2);
	}

	@Override
	public void round(int decimals)
	{
		this.y = MathUtil.round(this.y, decimals);
		this.x1 = MathUtil.round(this.x1, decimals);
		this.x2 = MathUtil.round(this.x2, decimals);
	}

	@Override
	public Location getStart()
	{
		Location loc = new Location(this.x1, this.y, this.getBlockDirection());
		loc.round(2);
		return loc;
	}

	@Override
	public Location getEnd()
	{
		Location loc = new Location(this.x2, this.y, this.getBlockDirection());
		loc.round(2);
		return loc;
	}

	@Override
	public boolean equals(Object object)
	{
		if(object.getClass() != getClass())
			return false;

		if(((HorizontalLimit)object).getX1() != getX1())
			return false;

		if(((HorizontalLimit)object).getX2() != getX2())
			return false;

		if(((HorizontalLimit)object).getY() != getY())
			return false;

		return ((HorizontalLimit)object).getBlockDirection() == getBlockDirection();
	}
}
