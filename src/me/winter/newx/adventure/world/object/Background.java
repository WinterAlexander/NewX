package me.winter.newx.adventure.world.object;

import me.winter.newx.adventure.Drawer;
import me.winter.newx.adventure.world.World;
import me.winter.newx.adventure.world.object.properties.Visible;

import java.io.Serializable;

public class Background extends WorldObject implements Serializable, Visible
{
	private String imageName;

	public Background(World world)
	{
		super(world);
		this.imageName = null;
	}

	public Background(World world, String imageName)
	{
		super(world);
		this.imageName = imageName;
	}

	@Override
	public void render(Drawer d)
	{
		getResourceManager().getImage(imageName).draw(0, 0, d.getCamera().getScreenWidth(), d.getCamera().getScreenHeight());
	}


	@Override
	public int getPriority()
	{
		return 0;
	}
}
