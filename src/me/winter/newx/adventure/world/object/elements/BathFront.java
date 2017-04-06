package me.winter.newx.adventure.world.object.elements;

import me.winter.newx.adventure.Drawer;
import me.winter.newx.adventure.world.object.WorldObject;
import me.winter.newx.adventure.world.object.properties.Tickable;
import me.winter.newx.adventure.world.object.properties.Visible;

public class BathFront extends WorldObject implements Visible, Tickable
{
	private Bath bath;

	public BathFront(Bath bath)
	{
		super(bath.getWorld());
		this.bath = bath;
	}

	@Override
	public void render(Drawer d)
	{
		d.drawImage(getResourceManager().getImage("object_bath_front"), bath.getLocation().getX(), bath.getLocation().getY(), bath.getWidth(), bath.getHeight());
	}

	@Override
	public int getPriority()
	{
		return Visible.LOW_PRIORITY;
	}

	@Override
	public void tick()
	{
		if(bath == null || !bath.isValid())
			remove();
	}
}
