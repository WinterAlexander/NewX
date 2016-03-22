package me.winter.newx.adventure.physics;

public class Straight
{
	private double a;
	private double b;
	
	public Straight(double a, double b)
	{
		this.a = a;
		this.b = b;
	}
	
	public Straight(double x, double y, double x2, double y2)
	{
		this.a = (y2 - y) / (x2 - x);
		this.b = y - this.a * x;
	}
	
	public static final int POINT_OVER_STRAIGHT = 1;
	public static final int POINT_ON_STRAIGHT = 0;
	public static final int POINT_UNDER_STRAIGHT = -1; 
	
	public int getPointRelativePosition(double x, double y)
	{
		if(y < this.a * x + this.b)
			return -1;
		if(y > this.a * x + this.b)
			return 1;
		return 0;
	}
	
	public double f(double x)
	{
		return this.a * x + this.b;
	}
}
