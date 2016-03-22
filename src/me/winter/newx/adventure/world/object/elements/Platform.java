package me.winter.newx.adventure.world.object.elements;

import java.util.ArrayList;
import java.util.List;

import me.winter.newx.adventure.Drawer;
import me.winter.newx.adventure.physics.Location;
import me.winter.newx.adventure.physics.collision.CollisionBox;
import me.winter.newx.adventure.world.object.WorldObject;
import me.winter.newx.adventure.world.object.proprieties.Localizable;
import me.winter.newx.adventure.world.object.proprieties.Visible;
import me.winter.newx.adventure.physics.collision.Limit;
import me.winter.newx.adventure.world.World;
import me.winter.newx.adventure.world.object.proprieties.Solid;
import me.winter.newx.adventure.physics.collision.TopLimit;

public class Platform extends WorldObject implements Visible, Solid, Localizable
{
	private Location location;
	private double width, height;
	private String imageName;
	
	public Platform(World world, double x, double y, double width)
	{
		this(world, "object_ladder_horizontal", x, y, width);
	}
	
	protected Platform(World world, String imageName, double x, double y, double width)
	{
		super(world);
		this.location = new Location(x, y);
		this.width = width;
		this.height = 0.1;
		this.imageName = imageName;
	}

	@Override
	public CollisionBox getCollisionBox()
	{
		return new CollisionBox(this)
		{
			@Override
			public List<Limit> getLimits()
			{
				List<Limit> limits = new ArrayList<>();

				limits.add(new TopLimit(Platform.this.getLocation().getY(), Platform.this.getLocation().getX(), Platform.this.getLocation().getX() + Platform.this.getWidth(), true));

				return limits;
			}
		};
	}


	@Override
	public void render(Drawer d)
	{
		if(this.imageName != null)
			d.drawImage(getResourceManager().getImage(imageName), this.getLocation().getX(), this.getLocation().getY() - getHeight(), getWidth(), getHeight());
	}

	@Override
	public int getPriority()
	{
		return Visible.MEDIUM_PRIORITY;
	}

	@Override
	public Location getLocation()
	{
		return location;
	}

	public void setLocation(Location location)
	{
		this.location = location;
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

	public String getImageName()
	{
		return imageName;
	}

	public void setImageName(String imageName)
	{
		this.imageName = imageName;
	}
}
