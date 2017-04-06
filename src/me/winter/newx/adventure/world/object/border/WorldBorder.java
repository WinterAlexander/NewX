package me.winter.newx.adventure.world.object.border;

import me.winter.newx.adventure.physics.collision.CollisionBox;
import me.winter.newx.adventure.world.World;
import me.winter.newx.adventure.world.object.WorldObject;
import me.winter.newx.adventure.world.object.properties.Visible;

public abstract class WorldBorder extends WorldObject implements Visible
{
	public WorldBorder(World world)
	{
		super(world);
	}

	public boolean contains(CollisionBox box)
	{
		return getBounds().collides(box);
	}

	public abstract CollisionBox getBounds();

	@Override
	public int getPriority()
	{
		return 0;
	}

	@Override
	public boolean doSave()
	{
		return true;
	}
}
