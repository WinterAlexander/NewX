package me.winter.newx.adventure.world.object.elements;

import me.winter.newx.adventure.Drawer;
import me.winter.newx.adventure.physics.collision.CollisionBox;
import me.winter.newx.adventure.world.object.WorldObject;
import me.winter.newx.adventure.world.object.proprieties.Visible;
import me.winter.newx.adventure.physics.collision.Limit;
import me.winter.newx.adventure.world.World;
import me.winter.newx.adventure.world.object.proprieties.Solid;
import me.winter.newx.adventure.physics.collision.TopLimit;

import java.util.ArrayList;
import java.util.List;

public class Ladder extends WorldObject implements Visible, Solid
{
	private double x, y, width, height;
	private int rungs;
	
	public Ladder(World world, double x, double y, double width, double height, int rungs)
	{
		super(world);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.rungs = rungs;
	}

	@Override
	public CollisionBox getCollisionBox()
	{
		return new CollisionBox(this)
		{
			@Override
			public List<Limit> getLimits()
			{
				List<Limit> list = new ArrayList<>();
				for(double i = height / rungs / 2; i <= height - height / rungs / 2; i+= height / rungs / 4)
				{
					list.add(new TopLimit(getY() + i, getX() + width / 4, getX() + width * 3 / 4, true));
				}
				return list;
			}
		};
	}

	@Override
	public void render(Drawer d)
	{
		for(double i = this.height / this.rungs / 2; i <= this.height - this.height / this.rungs / 2; i+= this.height / this.rungs)
		{
			d.drawImage(getResourceManager().getImage("object_ladder1"), this.getX() - width / 10, this.getY() + i, this.width * 6 / 5, this.height / this.rungs / 4);
		}
		
		d.drawImage(getResourceManager().getImage("object_ladder2"), this.getX(), this.getY(), this.width / 5, height);
		d.drawImage(getResourceManager().getImage("object_ladder2"), this.getX() + this.width * 4 / 5, this.getY(), this.width / 5, height);
	}

	@Override
	public int getPriority()
	{
		return Visible.HIGH_PRIORITY;
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

	@Override
	public double getWidth()
	{
		return width;
	}

	public void setWidth(double width)
	{
		this.width = width;
	}

	@Override
	public double getHeight()
	{
		return height;
	}

	public void setHeight(double height)
	{
		this.height = height;
	}

	public int getRungs()
	{
		return rungs;
	}

	public void setRungs(int rungs)
	{
		this.rungs = rungs;
	}
}
