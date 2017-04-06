package me.winter.newx.adventure.world.object.elements;

import me.winter.newx.adventure.world.World;
import org.newdawn.slick.Image;
import me.winter.newx.adventure.Drawer;
import me.winter.newx.adventure.world.object.properties.Visible;

public class Dirt extends Rectangle
{
	public double tileWidth;
	protected transient Image[] textures;
	protected transient boolean texturesLoaded = false;

	public Dirt(World world, double x, double y, double width, double height, double tileWidth)
	{
		super(world, x, y, width, height);
		this.tileWidth = tileWidth;
	}

	@Override
	public void render(Drawer d)
	{
		if(!this.texturesLoaded)
			this.loadTexture();
		d.drawBorderedMosaic(this.textures, this.getX(), this.getY(), this.getWidth(), this.getHeight(), tileWidth, tileWidth);
	}
	
	public void loadTexture()
	{
		this.textures = new Image[9];
		this.textures[0] = getResourceManager().getImage("object_dirt_topleft");
		this.textures[6] = getResourceManager().getImage("object_dirt_top");
		this.textures[3] = getResourceManager().getImage("object_dirt_topright");
		this.textures[2] = getResourceManager().getImage("object_dirt_left");
		this.textures[8] = getResourceManager().getImage("object_dirt_center");
		this.textures[5] = getResourceManager().getImage("object_dirt_right");
		this.textures[1] = getResourceManager().getImage("object_dirt_bottomleft");
		this.textures[7] = getResourceManager().getImage("object_dirt_bottom");
		this.textures[4] = getResourceManager().getImage("object_dirt_bottomright");
		this.texturesLoaded = true;
		
	}

	@Override
	public int getPriority()
	{
		return Visible.LOW_PRIORITY;
	}

}
