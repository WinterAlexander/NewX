package me.winter.newx.adventure.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.util.Log;

public abstract class GuiButton
{
	private Gui gui;

	private int x, y;
	private String langKey;
	private boolean mouseOn;

	public GuiButton(Gui gui, double x, double y, String langKey)
	{

		this.gui = gui;

		this.setX((int) (x / 100 * gui.getAdventure().getGame().getContainer().getWidth()));
		this.setY((int) (y / 100 * gui.getAdventure().getGame().getContainer().getHeight()));
		this.langKey = langKey;
		this.mouseOn = false;
	}
	
	public void render(Graphics g)
	{
		g.setFont(gui.getButtonFont());
		int textShaderPadding = gui.getPlayer().getWorld().getAdventure().getGame().getContainer().getWidth() / 360;
		
		if(this.mouseOn)
		{
			g.setColor(Color.black);
			g.drawString(getTitle(), this.x, this.y);
			g.setColor(Color.white);
			g.drawString(getTitle(), this.x + textShaderPadding, this.y - textShaderPadding);
		}
		else
		{
			g.setColor(Color.white);
			g.drawString(getTitle(), this.x, this.y);
		}
	}
	
	public void update(int mouseX, int mouseY, boolean click)
	{
		this.mouseOn = this.collide(mouseX, mouseY);
		
		if(this.mouseOn && click)
		{
			Log.debug("click");
			this.onClick();
		}
	}
	
	public abstract void onClick();
	
	private boolean collide(int x, int y)
	{
		if(this.x > x)
			return false;
		if(this.x + this.getW() < x)
			return false;
		if(this.y > y)
			return false;
		return this.y + this.getH() >= y;
	}

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public int getW()
	{
		return gui.getButtonFont().getWidth(getTitle().toString());
	}

	public int getH()
	{
		return gui.getButtonFont().getHeight(getTitle().toString());
	}

	public String getTitle()
	{
		return gui.getAdventure().getMessage(langKey);
	}
}
