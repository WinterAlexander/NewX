package me.winter.newx.adventure.world.object.elements;

import me.winter.newx.adventure.Drawer;
import me.winter.newx.adventure.physics.Direction;
import me.winter.newx.adventure.physics.Location;
import me.winter.newx.adventure.physics.Vector;
import me.winter.newx.adventure.physics.collision.Limit;
import me.winter.newx.adventure.world.World;
import me.winter.newx.adventure.world.object.proprieties.Furniture;
import me.winter.newx.adventure.world.object.proprieties.Solid;
import me.winter.newx.adventure.world.object.proprieties.Visible;

/**
 *
 * Created by winter on 17/02/16.
 */
public class Television extends Rectangle implements Furniture
{
	private boolean broken;
	private Vector velocity;

	public Television(World world, double x, double y, double width, double height)
	{
		this(world, new Location(x, y), width, height);
	}

	public Television(World world, Location loc, double width, double height)
	{
		super(world, loc, width, height, 1f);
		this.velocity = new Vector(0, 0);
		this.broken = false;
	}

	@Override
	public boolean isOnGround()
	{
		return getWorld().isBlocked(getCollisionBox(), Direction.DOWN);
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

	@Override
	public void render(Drawer d)
	{
		d.drawImage(this.broken ? getResourceManager().getImage("object_oldtv_broken") : getResourceManager().getImage("object_oldtv"), this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}

	@Override
	public int getPriority()
	{
		return Visible.MEDIUM_PRIORITY;
	}

	@Override
	public void onTouch(Solid solid, Limit limit, double power)
	{
		if(Math.abs(power) > 0.5)
			this.broken = true;

		Furniture.super.onTouch(solid, limit, power);
	}
}
