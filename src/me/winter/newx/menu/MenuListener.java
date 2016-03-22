package me.winter.newx.menu;

import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;

import me.winter.newx.menu.component.Button;

public class MenuListener implements MouseListener
{
	private MenuState menu;

	public MenuListener(MenuState menu)
	{
		this.menu = menu;
	}

	@Override
	public void mouseReleased(int button, int x, int y) 
	{
		if(button == Input.MOUSE_LEFT_BUTTON)
		{
			for(Button b : menu.getButtons())
			{
				if(b.contains(x, y))
				{
					b.click();
					b.select();
				}
				else
				{
					b.unSelect();
				}
			}
		}
	}

	@Override
	public void inputEnded() {}

	@Override
	public void inputStarted() {}

	@Override
	public boolean isAcceptingInput() 
	{
		return menu.isCurrentState();
	}

	@Override
	public void setInput(Input input)
	{

	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {}

	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {}

	@Override
	public void mousePressed(int button, int x, int y){}

	@Override
	public void mouseWheelMoved(int change) {}
}
