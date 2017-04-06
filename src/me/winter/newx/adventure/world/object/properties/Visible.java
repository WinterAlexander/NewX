package me.winter.newx.adventure.world.object.properties;


import me.winter.newx.adventure.Drawer;

public interface Visible
{
	int HIGH_PRIORITY = 25;
	int MEDIUM_PRIORITY = 50;
	int LOW_PRIORITY = 100;


	void render(Drawer d);

	/**
	 * Higher priority means sooner
	 * Lower priority means after
	 *
	 * @return the priority your Visible object should be rendered with
	 */
	int getPriority();
}
