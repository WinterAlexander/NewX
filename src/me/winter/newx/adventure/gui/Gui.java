package me.winter.newx.adventure.gui;

import me.winter.newx.adventure.AdventureState;
import me.winter.newx.adventure.Drawer;
import me.winter.newx.adventure.physics.Location;
import me.winter.newx.adventure.physics.collision.RectangleCollisionBox;
import me.winter.newx.adventure.world.object.creature.Player;
import me.winter.newx.util.MathUtil;
import org.newdawn.slick.*;

import java.util.ArrayList;
import java.util.List;

public class Gui
{
	private AdventureState adventure;

	private Chat chat;
	private List<GuiButton> buttons;

	private TrueTypeFont debugFont;
	private TrueTypeFont buttonFont;
	private TrueTypeFont chatFont;

	private Location pin;
	
	public Gui(AdventureState adventure)
	{
		this.adventure = adventure;


		this.buttons = new ArrayList<>();
		this.chat = new Chat(this);

		this.pin = new Location(0, 0);



		this.buttons.add(new GuiButton(this, 1.25, 5, "adv_gui_unpause")
		{
			@Override
			public void onClick()
			{
				Gui.this.adventure.getScheduler().start();
			}
		});

		this.buttons.add(new GuiButton(this, 1.25, 90, "adv_gui_exit")
		{
			@Override
			public void onClick()
			{
				Gui.this.adventure.getGame().enterState(Gui.this.adventure.getGame().getMenuState());
			}
		});
	}
	
	public void render(Graphics g, GameContainer container, Drawer drawer)
	{
		if(this.adventure.isDebugMode())
		{
			g.setFont(this.debugFont);
			drawer.setColor(new Color(128, 128, 128, 56));
			drawer.drawTiles();
			
			drawer.setColor(new Color(Color.pink.r, Color.pink.g, Color.pink.b, 0.5f));
			drawer.fillOval(getPin().getX() - 0.1, getPin().getY() - 0.1, 0.2, 0.2);

			g.setColor(new Color(1f, 1f, 1f, 0.8f));
			g.fillRect(0, 0, 350, 195);

			g.setColor(new Color(0, 0, 0));
			g.drawString("Frames per seconds: " + adventure.getGame().getContainer().getFPS(), 5, 5);
			g.drawString("Player location (" + this.getPlayer().getLocation().getX() + ", " + this.getPlayer().getLocation().getY() + ")", 5, 25);
			g.drawString("Player collision box (" + ((RectangleCollisionBox)this.getPlayer().getCollisionBox()).getX() + ", " + ((RectangleCollisionBox)this.getPlayer().getCollisionBox()).getY() + ")", 5, 45);
			g.drawString("Player collision box dimensions (" + this.getPlayer().getCollisionBox().getWidth() + "x" + this.getPlayer().getCollisionBox().getHeight() + ")", 5, 65);
			g.drawString("WorldObjects: " + this.getAdventure().getWorld().getWorldObjects().size(), 5, 85);
			double x = drawer.getCamera().toXReal(container.getInput().getMouseX());
			double y = drawer.getCamera().toYReal(container.getInput().getMouseY());
			g.drawString("Mouse Location: " + MathUtil.round(x, 2) + ", " + MathUtil.round(y, 2), 5, 105);
			g.drawString("Pim Location: " + getPin().getX() + ", " + getPin().getY(), 5, 125);
			g.drawString("Stability: " + this.getAdventure().getWorld().getStability(this.getPlayer().getCollisionBox()), 5, 145);
			g.drawString("Player velocity:" + getPlayer().getVelocity(), 5, 165);
		}

		this.chat.render(drawer, g);
		
		if(this.isMenuOpen())
		{
			Image mask = getAdventure().getGame().getResourceManager().getImage("gui_mask");
			if(mask == null)
				return;

			int width = mask.getWidth() / mask.getHeight() * adventure.getGame().getContainer().getWidth();

			mask.draw(0, 0, width, adventure.getGame().getContainer().getHeight());
			for(GuiButton button : this.buttons)
				button.render(g);
		}
	}

	public boolean isMenuOpen()
	{
		return adventure.getScheduler().isPause();
	}
	
	public Player getPlayer()
	{
		return adventure.getPlayer();
	}
	
	public Color getLifeColor()
	{
		if(this.getPlayer().getHealth() / this.getPlayer().getMaxHealth() <= 0.15)
			return new Color(128, 0, 0);
		else if(this.getPlayer().getHealth() / this.getPlayer().getMaxHealth() <= 0.25)
			return new Color(255, 0, 0);
		else if(this.getPlayer().getHealth() / this.getPlayer().getMaxHealth() <= 0.5)
			return new Color(255, 255, 0);
		else 
			return new Color(0, 255, 0);
	}
	
	public void onMouseMove(int mouseX, int mouseY)
	{
		if(this.isMenuOpen())
			for(GuiButton button : this.buttons)
				button.update(mouseX, mouseY, false);
	}
	
	public void onMouseClick(int mouseX, int mouseY)
	{
		if(this.isMenuOpen())
			for(GuiButton button : this.buttons)
				button.update(mouseX, mouseY, true);

		setPin(mouseX, mouseY);
	}

	public void openMenu()
	{
		adventure.getScheduler().pause();
	}


	public Chat getChat()
	{
		return this.chat;
	}

	public Location getPin()
	{
		return pin;
	}

	public void setPin(int pixelX, int pixelY)
	{
		this.pin = new Location(MathUtil.round(adventure.getDrawer().getCamera().toXReal(pixelX), 1),
				MathUtil.round(adventure.getDrawer().getCamera().toYReal(pixelY), 1));
	}

	public AdventureState getAdventure()
	{
		return adventure;
	}

	public TrueTypeFont getChatFont()
	{
		return chatFont;
	}

	public TrueTypeFont getButtonFont()
	{
		return buttonFont;
	}

	public void reloadFonts()
	{
		this.debugFont = new TrueTypeFont(getAdventure().getResourceManager().getFont("consola").deriveFont(getAdventure().getGame().getContainer().getHeight() / 48f), true);
		this.chatFont = new TrueTypeFont(getAdventure().getResourceManager().getFont("consola").deriveFont(getAdventure().getGame().getContainer().getHeight() / 48f), true);
		this.buttonFont = new TrueTypeFont(getAdventure().getResourceManager().getFont("trebuchet").deriveFont(getAdventure().getGame().getContainer().getHeight() / 32f), true);
	}
}
