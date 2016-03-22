package me.winter.newx.adventure.world.object.elements;

import java.util.ArrayList;
import java.util.List;

import me.winter.newx.adventure.Drawer;
import me.winter.newx.adventure.physics.Direction;
import me.winter.newx.adventure.physics.Location;
import me.winter.newx.adventure.physics.Vector;
import me.winter.newx.adventure.physics.collision.*;
import me.winter.newx.adventure.world.object.WorldObject;
import me.winter.newx.adventure.world.object.proprieties.Furniture;
import me.winter.newx.adventure.world.object.proprieties.Solid;
import me.winter.newx.adventure.world.object.proprieties.Visible;
import me.winter.newx.adventure.world.World;

public class Bath extends WorldObject implements Furniture
{
	private Location location;
	private Vector velocity;
	private double width, height;

	public Bath(World world, double x, double y, double w, double h)
	{
		super(world);
		this.location = new Location(x, y);
		this.width = w;
		this.height = h;

		this.velocity = new Vector(0, 0);
	}

	@Override
	public CollisionBox getCollisionBox()
	{
		return new CollisionBox(this)
		{
			@Override
			public List<Limit> getLimits()
			{
				double x = Bath.this.getLocation().getX();
				double y = Bath.this.getLocation().getY();
				double width = Bath.this.getWidth();
				double height = Bath.this.getHeight();

				List<Limit> list = new ArrayList<>();
				list.add(new TopLimit(y + 0.5 * height, x + 0.1 * width, x + 0.85 * width));

				list.add(new TopLimit(y + 0.9 * height, x, x + 0.1 * width));
				list.add(new TopLimit(y + height, x + 0.85 * width, x + width));

				list.add(new BottomLimit(y, x + 0.2 * width, x + 0.26 * width));
				list.add(new BottomLimit(y, x + 0.66 * width, x + 0.72 * width));

				list.add(new RightLimit(x + 0.1 * width,
						y + 0.5 * height,
						y + 0.9 * height));
				list.add(new LeftLimit(x,
						y + 0.8 * height,
						y + 0.9 * height));

				list.add(new RightLimit(x + width,
						y + 0.9 * height,
						y + height));
				list.add(new LeftLimit(x + 0.85 * width,
						y + 0.5 * height,
						y + height));

				return list;
			}
		};
	}

	@Override
	public void render(Drawer d)
	{
		d.drawImage(getResourceManager().getImage("object_bath"), getLocation().getX(), getLocation().getY(), getWidth(), getHeight());
	}

	@Override
	public void onTouch(Solid solid, Limit limit, double power)
	{
		Furniture.super.onTouch(solid, limit, power / 4);
	}

	@Override
	public int getPriority()
	{
		return Visible.HIGH_PRIORITY;
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

	public BathFront getBathFront()
	{
		return new BathFront(this);
	}

	@Override
	public boolean isOnGround()
	{
		return getWorld().isBlocked(getCollisionBox(), Direction.DOWN);
	}

	@Override
	public Location getLocation()
	{
		return location;
	}

	@Override
	public void setLocation(Location loc)
	{
		this.location = loc;
	}

	@Override
	public Vector getVelocity()
	{
		return velocity;
	}

	@Override
	public void setVelocity(Vector vec)
	{
		this.velocity = vec;
	}
}
