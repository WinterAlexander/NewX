package me.winter.newx.adventure.world.object.elements;

import me.winter.newx.adventure.world.World;
import me.winter.newx.adventure.world.object.creature.Creature;
import me.winter.newx.adventure.world.object.properties.Solid;
import me.winter.newx.adventure.Drawer;
import me.winter.newx.adventure.physics.collision.Limit;
import me.winter.newx.adventure.world.object.properties.Touchable;

public class LifePlatform extends Platform implements Touchable
{
	
	private transient boolean taken;

	public LifePlatform(World world, double x, double y, double width)
	{
		super(world, x, y, width);
		this.taken = false;
	}

	@Override
	public void render(Drawer d)
	{
		super.render(d);
		if(!this.taken)
			d.drawImage(getResourceManager().getImage("object_taco"), this.getLocation().getX() + this.getWidth() / 2 - 0.5, this.getLocation().getY() + Math.sin(getWorld().getAdventure().getScheduler().getGameTimeMillis() / (Math.PI * 50)) / 4 + 0.25, 1, 1);
	}
	
	@Override
	public void onTouch(Touchable entity, Limit limit)
	{
		if(taken)
			return;

		if(entity instanceof Creature)
		{
			((Creature)entity).setHealth(((Creature)entity).getMaxHealth());
			getResourceManager().getAudio("pickup").playAsSoundEffect(1f, 1f, false);
			taken = true;
		}
	}
}
