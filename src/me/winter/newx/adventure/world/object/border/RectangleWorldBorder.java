package me.winter.newx.adventure.world.object.border;

import me.winter.newx.adventure.physics.collision.CollisionBox;
import me.winter.newx.adventure.physics.collision.RectangleCollisionBox;
import me.winter.newx.adventure.world.World;
import me.winter.newx.adventure.Drawer;
import org.newdawn.slick.Color;

public class RectangleWorldBorder extends WorldBorder
{
	private double x, y, width, height;

	public RectangleWorldBorder(World world)
	{
		this(world, 0, 0, 0, 0);
	}

	public RectangleWorldBorder(World world, double x, double y, double width, double height)
	{
		super(world);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void render(Drawer d)
	{
		if(!getWorld().getAdventure().isDebugMode())
			return;

		d.setColor(Color.red);
		d.drawLine(x, y, x + width, y);
		d.drawLine(x, y, x, y + height);
		d.drawLine(x, y + height, x + width, y + height);
		d.drawLine(x + width, y, x + width, y + height);
	}

	@Override
	public CollisionBox getBounds()
	{
		return new RectangleCollisionBox(this, x, y, width, height);
	}
}
