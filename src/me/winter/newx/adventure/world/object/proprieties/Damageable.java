package me.winter.newx.adventure.world.object.proprieties;

import me.winter.newx.adventure.physics.Vector;

public interface Damageable
{
	boolean isDeath();
	void kill();

	void hit(double damage, Vector velocity);
	void setHealth(double health);
	double getHealth();
	double getMaxHealth();
}