package me.winter.newx.adventure.world;

import me.winter.newx.Task;
import me.winter.newx.adventure.world.object.WorldObject;
import me.winter.newx.adventure.world.object.proprieties.Tickable;

import java.util.ArrayList;

/**
 *
 * Created by Alexander Winter on 2016-02-13.
 */
public class WorldTickTask extends Task
{
	private World world;

	public WorldTickTask(World world)
	{
		super(50, true);
		this.world = world;
	}

	@Override
	public void run()
	{
		for(WorldObject object : new ArrayList<>(this.world.getWorldObjects()))
			if(object instanceof Tickable)
				((Tickable)object).tick();
	}
}
