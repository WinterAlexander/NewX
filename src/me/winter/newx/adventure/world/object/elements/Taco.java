package me.winter.newx.adventure.world.object.elements;

import me.winter.newx.adventure.Drawer;
import me.winter.newx.adventure.physics.Direction;
import me.winter.newx.adventure.physics.Location;
import me.winter.newx.adventure.physics.Vector;
import me.winter.newx.adventure.world.World;
import me.winter.newx.adventure.world.object.properties.Furniture;
import me.winter.newx.adventure.world.object.properties.Visible;

/**
 *
 * Created by winter on 17/02/16.
 */
public class Taco extends Rectangle implements Furniture
{
	private Vector vector;

	public Taco(World world, Location loc)
	{
		super(world, loc, 0.3, 0.15, 0.5f);
		this.vector = new Vector(0, 0);
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
	public void render(Drawer d)
	{
		d.drawImage(getResourceManager().getImage("object_taco"), getLocation().getX(), getLocation().getY(), getWidth(), getHeight());
	}

	@Override
	public int getPriority()
	{
		return Visible.MEDIUM_PRIORITY;
	}
}
