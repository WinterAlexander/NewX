package me.winter.newx.adventure.world.object.elements;

import me.winter.newx.adventure.world.World;
import me.winter.newx.adventure.Drawer;
import me.winter.newx.adventure.world.object.properties.Solid;
import me.winter.newx.adventure.world.object.properties.Visible;
import me.winter.newx.adventure.world.object.properties.Movable;
import me.winter.newx.adventure.physics.Direction;
import me.winter.newx.adventure.physics.collision.Limit;
import me.winter.newx.adventure.physics.Vector;
import me.winter.newx.adventure.world.object.properties.Touchable;

public class Spoing extends Square implements Touchable
{
	private long lastWalk;
	
	public Spoing(World world, double x, double y, double w)
	{
		super(world, x, y, w);
		this.lastWalk = 0;
	}

	@Override
	public void render(Drawer d)
	{
		if(this.isActive())
			d.drawImage(getResourceManager().getImage("object_spoing_active"), this.getX(), this.getY(), this.getWidth(), this.getHeight());
		else
			d.drawImage(getResourceManager().getImage("object_spoing"), this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}

	@Override
	public int getPriority()
	{
		return Visible.LOW_PRIORITY;
	}

	@Override
	public void onTouch(Touchable entity, Limit limit)
	{
		if(limit.getBlockDirection() == Direction.DOWN)
		{
			if(entity instanceof Movable)
			{
				((Movable)entity).setVelocity(new Vector(0, 0.5));
				this.active();
			}
		}
		else if(limit.getBlockDirection() == Direction.UP)
		{
			if(entity instanceof Movable)
			{
				((Movable)entity).setVelocity(new Vector(0, 0.5));
				this.active();
			}
		}
		else if(limit.getBlockDirection() == Direction.LEFT)
		{
			if(entity instanceof Movable)
			{
				((Movable)entity).setVelocity(new Vector(0.5, 0));
				this.active();
			}
		}
		else if(limit.getBlockDirection() == Direction.RIGHT)
		{
			if(entity instanceof Movable)
			{
				((Movable)entity).setVelocity(new Vector(0.5, 0));
				this.active();
			}
		}
	}
	
	public boolean isActive()
	{
		return this.lastWalk + 1000 > getWorld().getAdventure().getScheduler().getGameTimeMillis();
				
	}
	
	public void active()
	{
		this.lastWalk = getWorld().getAdventure().getScheduler().getGameTimeMillis();
	}

}
