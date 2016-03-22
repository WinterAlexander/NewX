package me.winter.newx.menu.component;

import me.winter.newx.menu.MenuState;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Button extends Component
{
	private String langKey;
	
	private String background;
	
	private Runnable action;
	
	public Button(MenuState menu, double x, double y, double width, double height, String langKey, String background, Runnable action)
	{
		super(menu, x, y, width, height);
		
		this.langKey = langKey;
		
		this.background = background;
		this.action = action;
	}
	
	public String getContent()
	{
		return getMenu().getLang().get(langKey);
	}
	
	public void click()
	{
		this.action.run();
	}
	
	@Override
	public void render(GameContainer c, Graphics g)
	{
		getMenu().getResourceManager().getImage(background).draw(this.getRelativeX(), this.getRelativeY(), this.getRelativeWidth(), this.getRelativeHeight());
		g.setColor(Color.white);
		if(this.isSelected())
			g.setColor(Color.gray);
		int fw = g.getFont().getWidth(this.getContent());
		int fh = g.getFont().getHeight(this.getContent());
		g.drawString(this.getContent(), this.getRelativeX() + (this.getRelativeWidth() / 2 - fw / 2), this.getRelativeY() + (this.getRelativeHeight() / 2 - fh / 2));
	}
}
