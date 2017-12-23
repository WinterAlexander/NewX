package me.winter.newx.adventure.world.object;

import me.winter.newx.Scheduler;
import me.winter.newx.adventure.AdventureState;
import me.winter.newx.adventure.world.World;
import me.winter.newx.resource.ResourceManager;

import java.io.Serializable;

/**
 *
 * Created by Alexander Winter on 2016-01-25.
 */
public class WorldObject implements Serializable
{
	private transient World world;

	public WorldObject(World world)
	{
		this.world = world;
	}

	public World getWorld()
	{
		return this.world;
	}

	public void setWorld(World world)
	{
		this.world = world;
	}

	public AdventureState getAdventure()
	{
		if(getWorld() == null)
			return null;

		return this.getWorld().getAdventure();
	}

	public Scheduler getScheduler()
	{
		if(getAdventure() == null)
			return null;

		return getAdventure().getScheduler();
	}

	public ResourceManager getResourceManager()
	{
		return getAdventure().getResourceManager();
	}

	public boolean doSave()
	{
		return true;
	}

	public void remove()
	{
		world.getWorldObjects().remove(this);
	}

	public boolean isValid()
	{
		return world != null && world.getWorldObjects().contains(this);
	}
}
