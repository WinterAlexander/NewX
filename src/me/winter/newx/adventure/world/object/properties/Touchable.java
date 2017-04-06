package me.winter.newx.adventure.world.object.properties;

import me.winter.newx.adventure.physics.collision.Limit;

public interface Touchable extends Solid
{
	void onTouch(Touchable touchable, Limit limit);
}
