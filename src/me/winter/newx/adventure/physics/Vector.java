package me.winter.newx.adventure.physics;

import me.winter.newx.util.MathUtil;

import java.io.Serializable;
import java.util.Collection;

/**
 * A mutable Vector class
 *
 */
public class Vector implements Serializable
{
	private double x;
	private double y;
	
	public Vector(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	public Vector(Location loc1, Location loc2)
	{
		this.x = loc2.getX() - loc1.getX();
		this.y = loc2.getY() - loc1.getY();
	}

	public Vector(Vector vector)
	{
		this.x = vector.getX();
		this.y = vector.getY();
	}

	public double getX()
	{
		return x;
	}

	public void setX(double x)
	{
		this.x = x;
	}

	public double getY()
	{
		return y;
	}

	public void setY(double y)
	{
		this.y = y;
	}
	
	public double getLength()
	{
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	public Vector add(Vector vector)
	{
		this.x += vector.x;
		this.y += vector.y;
		return this;
	}

	public Vector add(double x, double y)
	{
		this.x += x;
		this.y += y;
		return this;
	}

	public Vector remove(Vector vector)
	{
		this.x -= vector.x;
		this.y -= vector.y;
		return this;
	}

	public Vector remove(double x, double y)
	{
		this.x -= x;
		this.y -= y;
		return this;
	}

	public Vector multiply(double scalar)
	{
		this.x *= scalar;
		this.y *= scalar;
		return this;
	}

	public Vector normalize()
	{
		double length = getLength();

		this.x /= length;
		this.y /= length;
		return this;
	}

	@Override
	public Vector clone()
	{
		return new Vector(this);
	}

	public boolean isNull()
	{
		return this.x == 0 && this.y == 0;
	}
	
	public double getXFromY(double y)
	{
		return this.x * y / this.y;
	}
	
	public double getYFromX(double x)
	{
		return this.y * x / this.x;
	}
	
	public String toString()
	{
		return "Vector(" + this.x + ", " + this.y + ")";
	}

	public void round(int decimals)
	{
		this.x = MathUtil.round(this.x, decimals);
		this.y = MathUtil.round(this.y, decimals);
	}
	
	public static Vector getSum(Collection<Vector> collection)
	{
		Vector result = new Vector(0, 0);
		for(Vector vec : collection)
			result.add(vec);
		return result;
	}
}
