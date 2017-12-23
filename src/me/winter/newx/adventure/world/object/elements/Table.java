package me.winter.newx.adventure.world.object.elements;

import me.winter.newx.adventure.Drawer;
import me.winter.newx.adventure.physics.Direction;
import me.winter.newx.adventure.physics.Location;
import me.winter.newx.adventure.physics.Vector;
import me.winter.newx.adventure.physics.collision.*;
import me.winter.newx.adventure.world.World;
import me.winter.newx.adventure.world.object.WorldObject;
import me.winter.newx.adventure.world.object.proprieties.Furniture;
import me.winter.newx.adventure.world.object.proprieties.Visible;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Alexander Winter on 2016-02-18.
 */
public class Table extends WorldObject implements Furniture
{
	private Location location;
	private Vector velocity;
	private double width, height;

	public Table(World world, double x, double y, double w, double h)
	{
		super(world);
		this.location = new Location(x, y);
		this.width = w;
		this.height = h;

		this.velocity = new Vector(0, 0);
	}

	@Override
	public void render(Drawer d)
	{
		d.drawImage(getResourceManager().getImage("object_ladder_vertical"), getLocation().getX() + getWidth() / 5, getLocation().getY(), 0.1, getHeight());
		d.drawImage(getResourceManager().getImage("object_ladder_vertical"), getLocation().getX() + getWidth() * 4 / 5 - 0.1, getLocation().getY(), 0.1, getHeight());

		d.drawImage(getResourceManager().getImage("object_ladder_horizontal"), getLocation().getX(), getLocation().getY() + getHeight() - 0.1, getWidth(), 0.1);


	}

	@Override
	public CollisionBox getCollisionBox()
	{
		return new CollisionBox(this)
		{
			@Override
			public List<Limit> getLimits()
			{
				double x = Table.this.getLocation().getX();
				double y = Table.this.getLocation().getY();
				double width = Table.this.getWidth();
				double height = Table.this.getHeight();

				List<Limit> list = new ArrayList<>();
				list.add(new TopLimit(y + height, x, x + width));
				list.add(new BottomLimit(y + height - 0.1, x, x + width));

				list.add(new LeftLimit(x, y + height, y + height - 0.1));
				list.add(new RightLimit(x + width, y + height, y + height - 0.1));


				list.add(new LeftLimit(x + width / 5, y, y + height - 0.1));
				list.add(new RightLimit(x + width / 5 + 0.1 , y, y + height - 0.1));
				list.add(new BottomLimit(y, x + width / 5, x + width / 5 + 0.1));

				list.add(new LeftLimit(x + width * 4 / 5 - 0.1, y, y + height - 0.1));
				list.add(new RightLimit(x + width * 4 / 5, y, y + height - 0.1));
				list.add(new BottomLimit(y, x + width * 4 / 5 - 0.1, x + width * 4 / 5));

				return list;
			}
		};
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
