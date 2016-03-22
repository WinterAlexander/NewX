package me.winter.newx.adventure.physics.collision;

import me.winter.newx.adventure.world.object.WorldObject;
import me.winter.newx.util.MathUtil;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by 1541869 on 2016-02-01.
 */
public class RectangleCollisionBox extends CollisionBox
{
	private double x, y, width, height;
	private float stability;

	public RectangleCollisionBox(WorldObject object, double x, double y, double width, double height)
	{
		this(object, x, y, width, height, 1f);
	}

	public RectangleCollisionBox(WorldObject object, double x, double y, double width, double height, float stability)
	{
		super(object);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		this.stability = stability;
	}

	@Override
	public void round(int decimals)
	{
		this.x = MathUtil.round(this.x, decimals);
		this.y = MathUtil.round(this.y, decimals);
		this.width = MathUtil.round(this.width, decimals);
		this.height = MathUtil.round(this.height, decimals);
	}

	@Override
	public List<Limit> getLimits()
	{
		List<Limit> limits = new ArrayList<>();

		limits.add(new BottomLimit(this.getY(), this.getX(), this.getX() + this.getWidth()));
		limits.add(new TopLimit(this.getY() + this.getHeight(), this.getX(), this.getX() + this.getWidth(), false, this.stability));
		limits.add(new LeftLimit(this.getX(), this.getY(), this.getY() + this.getHeight()));
		limits.add(new RightLimit(this.getX() + this.getWidth(), this.getY(), this.getY() + this.getHeight()));

		return limits;
	}

	public double getX()
	{
		return this.x;
	}

	public double getY()
	{
		return this.y;
	}

	public double getWidth()
	{
		return this.width;
	}

	public double getHeight()
	{
		return this.height;
	}

	public double getX2()
	{
		return this.x + this.width;
	}

	public double getY2()
	{
		return this.y;
	}

	public double getX3()
	{
		return this.x;
	}

	public double getY3()
	{
		return this.y + this.height;
	}

	public double getX4()
	{
		return this.x + this.width;
	}

	public double getY4()
	{
		return this.y + this.height;
	}

	public double getCenterX()
	{
		return this.x + this.width / 2;
	}

	public double getCenterY()
	{
		return this.y + this.height / 2;
	}

	public float getStability()
	{
		return stability;
	}

	public void setStability(float stability)
	{
		this.stability = stability;
	}
}
