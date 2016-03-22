package me.winter.newx.adventure.world.object.proprieties;

import me.winter.newx.adventure.physics.collision.Limit;

public interface Touchable extends Solid
{
	void onTouch(Solid solid, Limit limit, double power);
}
