package me.winter.newx;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.InputListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.Log;

import java.awt.*;

/**
 *
 * Created by Alexander Winter on 2016-01-11.
 */
public class WindowListener implements InputListener
{
	private NewX game;

	public WindowListener(NewX game)
	{
		this.game = game;
	}

	@Override
	public void controllerLeftPressed(int i)
	{

	}

	@Override
	public void controllerLeftReleased(int i)
	{

	}

	@Override
	public void controllerRightPressed(int i)
	{

	}

	@Override
	public void controllerRightReleased(int i)
	{

	}

	@Override
	public void controllerUpPressed(int i)
	{

	}

	@Override
	public void controllerUpReleased(int i)
	{

	}

	@Override
	public void controllerDownPressed(int i)
	{

	}

	@Override
	public void controllerDownReleased(int i)
	{

	}

	@Override
	public void controllerButtonPressed(int i, int i1)
	{

	}

	@Override
	public void controllerButtonReleased(int i, int i1)
	{

	}

	@Override
	public void keyPressed(int i, char c)
	{

	}

	@Override
	public void keyReleased(int key, char c)
	{
		if(key == Input.KEY_F11)
		{
			try
			{
				if(game.getContainer().isFullscreen())
				{
					game.getConfig().setWindowsWidth(game.getConfig().getWindowsWidth() / 8 * 7);
					game.getConfig().setWindowsHeight(game.getConfig().getWindowsHeight() / 8 * 7);
					game.getConfig().setFullscreen(false);
				}
				else
				{
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

					game.getConfig().setWindowsWidth((int)screenSize.getWidth());
					game.getConfig().setWindowsHeight((int)screenSize.getHeight());
					game.getConfig().setFullscreen(true);
				}

				game.getContainer().setDisplayMode(game.getConfig().getWindowsWidth(), game.getConfig().getWindowsHeight(), game.getConfig().isFullscreen());
				game.getCurrentState().displayModeChanged(game.getConfig().getWindowsWidth(), game.getConfig().getWindowsHeight(), game.getConfig().isFullscreen());
			}
			catch(SlickException e)
			{
				Log.error(e);
			}
		}
	}

	@Override
	public void mouseWheelMoved(int i)
	{

	}

	@Override
	public void mouseClicked(int i, int i1, int i2, int i3)
	{

	}

	@Override
	public void mousePressed(int i, int i1, int i2)
	{

	}

	@Override
	public void mouseReleased(int i, int i1, int i2)
	{

	}

	@Override
	public void mouseMoved(int i, int i1, int i2, int i3)
	{

	}

	@Override
	public void mouseDragged(int i, int i1, int i2, int i3)
	{

	}

	@Override
	public void setInput(Input input)
	{

	}

	@Override
	public boolean isAcceptingInput()
	{
		return true;
	}

	@Override
	public void inputEnded()
	{

	}

	@Override
	public void inputStarted()
	{

	}
}
