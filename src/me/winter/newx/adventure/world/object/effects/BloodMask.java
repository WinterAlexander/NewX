package me.winter.newx.adventure.world.object.effects;

import me.winter.newx.adventure.Drawer;
import me.winter.newx.adventure.world.World;
import me.winter.newx.adventure.world.object.WorldObject;
import me.winter.newx.adventure.world.object.proprieties.Named;
import me.winter.newx.adventure.world.object.proprieties.Visible;

/**
 *
 * Created by 1541869 on 2016-03-24.
 */
public class BloodMask extends WorldObject implements Visible, Named
{
	public BloodMask(World world)
	{
		super(world);
	}

	@Override
	public void render(Drawer d)
	{
		if(getScheduler().getGameTimeMillis() / 125 % 8 != 0)
			getResourceManager().getImage("mask_blood").draw(0, 0, d.getCamera().getScreenWidth(), d.getCamera().getScreenHeight());
	}

	@Override
	public int getPriority()
	{
		return Visible.LOW_PRIORITY;
	}

	@Override
	public String getName()
	{
		return "BloodMask";
	}

	@Override
	public boolean doSave()
	{
		return false;
	}
}
