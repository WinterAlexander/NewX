package me.winter.newx.adventure.gui;

import me.winter.newx.adventure.Drawer;
import me.winter.newx.adventure.world.object.creature.Creature;
import org.newdawn.slick.Color;

public class LifeBar 
{
	public static double HP_RECT_WIDTH = 0.05;
	public static double HP_RECT_HEIGHT = 0.07;
	public static double HP_RECT_PADDING = 0.02;
	
	private Creature entity;
	
	public LifeBar(Creature entity)
	{
		this.entity = entity;
	}

	public void render(Drawer drawer)
	{
		if(entity.getLastHit() + 3000 > entity.getScheduler().getGameTimeMillis())
		{
			for(double currentHp = 0; currentHp < this.entity.getMaxHealth(); currentHp++)
			{
				double currentX = this.getX() + currentHp * HP_RECT_WIDTH + currentHp * HP_RECT_PADDING;
				
				if(currentHp < this.entity.getHealth())
				{
					drawer.setColor(this.getColor());
					drawer.fillRect(currentX, this.getY(), HP_RECT_WIDTH, HP_RECT_HEIGHT);
				}
				drawer.setColor(entity.isSuffering() ? Color.red : Color.black);
				drawer.drawRect(currentX, this.getY(), HP_RECT_WIDTH, HP_RECT_HEIGHT);
			}
		}
	}
	
	public Color getColor()
	{
		int red = 255 - (int)(255 * this.entity.getHealth() / this.entity.getMaxHealth());
		int green = (int)(255 * this.entity.getHealth() / this.entity.getMaxHealth());

		return new Color(red, green, 0);
	}
	
	public double getX()
	{
		return entity.getLocation().getX() - (((int)entity.getMaxHealth()) * LifeBar.HP_RECT_WIDTH + ((int)entity.getMaxHealth() - 1) * LifeBar.HP_RECT_PADDING) / 2;
	}
	
	public double getY()
	{
		return entity.getLocation().getY() + entity.getHeight();
	}
}
