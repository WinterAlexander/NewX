package me.winter.newx.adventure.world.object.elements;

import me.winter.newx.adventure.world.World;
import me.winter.newx.adventure.Drawer;
import me.winter.newx.adventure.world.object.proprieties.Visible;

public class Ice extends Rectangle
{

	public Ice(World world, double x, double y, double width, double height)
	{
		super(world, x, y, width, height, 0.2f);
	}

	@Override
	public void render(Drawer d)
	{
		d.drawImage(getResourceManager().getImage("object_ice"), this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}

	@Override
	public int getPriority()
	{
		return Visible.HIGH_PRIORITY;
	}
}
