package me.winter.newx.adventure;

import me.winter.newx.adventure.physics.Location;
import me.winter.newx.adventure.world.World;

public class Camera
{
	private World world;

	private double minZoom, maxZoom;
	private double zoom;

	private double centerX, centerY;
	private double maxX, maxY;
	private double minX, minY;

	private boolean noLimitX, noLimitY;

	public Camera(World world)
	{
		this(world, 5, 25, 0, 0, -50, -50, 50, 50);
	}

	public Camera(World world, double minZoom, double maxZoom, double x, double y, double minX, double minY, double maxX, double maxY)
	{
		this.world = world;

		this.minZoom = minZoom;
		this.maxZoom = maxZoom;
		this.zoom = (minZoom + maxZoom) / 2;
		this.centerX = x;
		this.centerY = y;
		this.maxX = maxX;
		this.maxY = maxY;
		this.minY = minY;
		this.minX = minX;

		this.noLimitX = true;
		this.noLimitY = true;
	}
	
	public double getZoom()
	{
		return this.zoom;
	}
	
	public void setZoom(double z)
	{
		if(z < this.minZoom)
			this.zoom = this.minZoom;
		else if(z > this.maxZoom)
			this.zoom = this.maxZoom;
		else
			this.zoom = z;
	}
	
	public double getCenterX()
	{
		return this.centerX;
	}
	
	public int getPixelCenterX()
	{
		return this.getScreenWidth() / 2 - this.toPixel(this.centerX);
	}
	
	public double getCenterY()
	{
		return this.centerY;
	}
	
	public int getPixelCenterY()
	{
		return this.getScreenHeight() / 2 - (this.getScreenHeight() - this.toPixel(this.centerY));
	}
	
	public void setCenter(double x, double y)
	{
		if(x <= this.maxX && x >= this.minX || noLimitX)
			this.centerX = x;
		if(y <= this.maxY && y >= this.minY || noLimitY)
			this.centerY = y;
	}
	
	public boolean isOnScreen(int x, int y, int w, int h)
	{
		if(x > this.getScreenWidth())
			return false;
		
		if(x + w < 0)
			return false;
		
		if(y > this.getScreenHeight())
			return false;

		return y + h >= 0;

	}
	
	public double getScale()
	{
		return this.getScreenWidth() / this.zoom;
	}
	
	public int toPixel(double i)
	{
		return (int) Math.round(i * this.getScale());
	}
	
	public int toXPixel(double x)
	{
		return (int) (this.getPixelCenterX() + Math.round(x * this.getScale()));
	}
	
	public int toYPixel(double y)
	{
		return (int) (this.getPixelCenterY() + Math.round(this.getScreenHeight() - y * this.getScale()));
	}
	
	public double toReal(int i)
	{
		return i / this.getScale();
	}
	
	public double toXReal(int x)
	{
		return (x - this.getPixelCenterX()) / this.getScale();
	}
	
	public double toYReal(int y)
	{
		return (y - this.getPixelCenterY() - this.getScreenHeight()) / -this.getScale();
	}
	
	public int getScreenWidth()
	{
		return world.getAdventure().getGame().getContainer().getWidth();
	}
	
	public int getScreenHeight()
	{
		return world.getAdventure().getGame().getContainer().getHeight();
	}
	
	public double getLandscapeWidth()
	{
		return this.zoom;
	}
	
	public double getLandscapeHeight()
	{
		return this.getScreenHeight() / this.getScale();
	}

	public boolean isNoLimitX()
	{
		return noLimitX;
	}

	public void setNoLimitX(boolean noLimitX)
	{
		this.noLimitX = noLimitX;
	}

	public boolean isNoLimitY()
	{
		return noLimitY;
	}

	public void setNoLimitY(boolean noLimitY)
	{
		this.noLimitY = noLimitY;
	}

	public void center(Location location)
	{
		this.setCenter((location.getX() + this.getCenterX()) / 2, (location.getY() + this.getCenterY()) / 2);
	}

}
