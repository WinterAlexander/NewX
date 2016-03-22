package me.winter.newx.adventure.world.object.proprieties;


import me.winter.newx.adventure.Drawer;

public interface Visible
{
	int HIGH_PRIORITY = 0;
	int MEDIUM_PRIORITY = 50;
	int LOW_PRIORITY = 100;

	void render(Drawer d);

	/**
	 * Smaller is before, bigger is after
	 *
	 * @return the priority your Visible object should be rendered with
	 */
	int getPriority();
}
