package me.winter.newx.menu.component;

import me.winter.newx.menu.MenuState;

public abstract class Component implements Graphical
{
	private MenuState menu;

	private double x, y, w, h;
	private boolean selected;
	
	public Component(MenuState menu, double x, double y, double w, double h)
	{
		this.menu = menu;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public double getX()
	{
		return x;
	}

	public void setX(double x)
	{
		this.x = x;
	}

	public double getY()
	{
		return y;
	}

	public void setY(double y)
	{
		this.y = y;
	}

	public double getWidth()
	{
		return this.w;
	}

	public void setWidth(double width)
	{
		this.w = width;
	}

	public double getHeight()
	{
		return this.h;
	}

	public void setHeight(double height)
	{
		this.h = height;
	}
	
	public int getRelativeX()
	{
		return (int) (this.x / 100 * menu.getGame().getContainer().getWidth());
	}
	
	public int getRelativeY()
	{
		return (int) (this.y / 100 * menu.getGame().getContainer().getHeight());
	}
	
	public int getRelativeWidth()
	{
		return (int) (this.w / 100 * menu.getGame().getContainer().getWidth());
	}
	
	public int getRelativeHeight()
	{
		return (int) (this.h / 100 * menu.getGame().getContainer().getHeight());
	}
	
	public boolean contains(double x, double y)
	{
		return !(x < this.getRelativeX()
		|| x > this.getRelativeX() + this.getRelativeWidth()
		|| y < this.getRelativeY()
		|| y > this.getRelativeY() + this.getRelativeHeight());
	}
	
	public boolean isSelected()
	{
		return this.selected;
	}
	
	public void select()
	{
		this.selected = true;
	}
	
	public void unSelect()
	{
		this.selected = false;
	}

	public MenuState getMenu()
	{
		return menu;
	}
}
