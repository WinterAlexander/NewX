package me.winter.newx.adventure.world.object.properties;

import me.winter.newx.adventure.physics.Location;
import me.winter.newx.adventure.physics.Vector;

public interface Movable extends Tickable, Localizable
{
	default void tick()
	{
		move();
	}

	void move();

	void setLocation(Location loc);

	Vector getVelocity();
	void setVelocity(Vector vec);
}