package me.winter.newx.adventure.world.object.elements;

import me.winter.newx.adventure.world.World;
import me.winter.newx.adventure.physics.collision.Limit;
import me.winter.newx.adventure.world.object.creature.Player;
import me.winter.newx.adventure.world.object.properties.Solid;
import me.winter.newx.adventure.world.object.properties.Touchable;

public class FinishPlatform extends Platform implements Touchable
{

	public FinishPlatform(World world, double x, double y, double width)
	{
		super(world, x, y, width);
	}

	@Override
	public void onTouch(Touchable solid, Limit limit)
	{
		if(solid instanceof Player);
			//getWorld().getAdventure().win();
	}

}
