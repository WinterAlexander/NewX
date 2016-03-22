package me.winter.newx.adventure.world.object.elements;

import me.winter.newx.adventure.world.World;

import org.newdawn.slick.Image;

public class Snow extends Dirt
{

	public Snow(World world, double x, double y, double width, double height, double tileWidth)
	{
		super(world, x, y, width, height, tileWidth);
	}
	
	public void loadTexture()
	{
		this.textures = new Image[9];
		this.textures[0] = getResourceManager().getImage("object_snow_topleft");
		this.textures[6] = getResourceManager().getImage("object_snow_top");
		this.textures[3] = getResourceManager().getImage("object_snow_topright");
		this.textures[2] = getResourceManager().getImage("object_snow_left");
		this.textures[8] = getResourceManager().getImage("object_snow_center");
		this.textures[5] = getResourceManager().getImage("object_snow_right");
		this.textures[1] = getResourceManager().getImage("object_snow_bottomleft");
		this.textures[7] = getResourceManager().getImage("object_snow_bottom");
		this.textures[4] = getResourceManager().getImage("object_snow_bottomright");
		this.texturesLoaded = true;
		
	}

}
