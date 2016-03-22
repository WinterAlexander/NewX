package me.winter.newx.adventure.world.object.proprieties;


import me.winter.newx.adventure.physics.collision.CollisionBox;

public interface Solid
{
	CollisionBox getCollisionBox();

	default double getWidth()
	{
		return getCollisionBox().getWidth();
	}
	default double getHeight()
	{
		return getCollisionBox().getHeight();
	}
}
