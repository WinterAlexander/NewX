package me.winter.newx.adventure.world.object.elements;


import me.winter.newx.adventure.Drawer;
import me.winter.newx.adventure.physics.Direction;
import me.winter.newx.adventure.physics.Location;
import me.winter.newx.adventure.physics.Vector;
import me.winter.newx.adventure.world.World;
import me.winter.newx.adventure.world.object.properties.Furniture;
import me.winter.newx.adventure.world.object.properties.Visible;

public class Box extends Square implements Furniture
{
	private Vector vector;

	public Box(World world, double x, double y, double width)
	{
		super(world, x, y, width);

		this.vector = new Vector(0, 0);
	}

	public Box(World world, Location loc, double width)
	{
		super(world, loc, width);

		this.vector = new Vector(0, 0);
	}

	@Override
	public void render(Drawer d)
	{
		d.drawImage(getResourceManager().getImage("object_box"), this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}

	@Override
	public boolean isOnGround()
	{
		return getWorld().isBlocked(getCollisionBox(), Direction.DOWN);
	}

	@Override
	public Vector getVelocity()
	{
		return vector;
	}

	@Override
	public void setVelocity(Vector vec)
	{
		this.vector = vec;
	}

	@Override
	public int getPriority()
	{
		return Visible.HIGH_PRIORITY;
	}
}
