package me.winter.newx.adventure.physics;

public class Segment extends Straight
{
	private double x1, y1, x2, y2; //limites

	public Segment(double x1, double y1, double x2, double y2)
	{
		super(x1, y1, x2, y2);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public boolean contains(double x)
	{
		if(x > this.x1 && this.x1 > this.x2)
			return false;

		return !(x < this.x1 && x < this.x2);

	}
	
	public boolean contains(double x, double y)
	{
		if(!this.contains(x))
		{
			return false;
		}
		
		return this.f(x) == y;
	}
	
	public boolean intersects(Segment seg)
	{
		return false;
	}

	public double getX1() {
		return x1;
	}

	public void setX1(double x1) {
		this.x1 = x1;
	}

	public double getY1() {
		return y1;
	}

	public void setY1(double y1) {
		this.y1 = y1;
	}

	public double getX2() {
		return x2;
	}

	public void setX2(double x2) {
		this.x2 = x2;
	}

	public double getY2() {
		return y2;
	}

	public void setY2(double y2) {
		this.y2 = y2;
	}
}
