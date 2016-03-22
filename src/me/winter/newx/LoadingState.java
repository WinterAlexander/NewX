package me.winter.newx;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class LoadingState extends DynamicGameState
{

	public LoadingState(NewX game)
	{
		super(game);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{
		g.setColor(Color.black);
		g.fillRect(0, 0, container.getWidth(), container.getHeight());
		g.setColor(Color.white);
		g.drawString(getGame().getLang().get("loading"), 5, 5);
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game)
	{
		super.enter(container, game);
		this.getScheduler().addTask(new Task(1, false)
		{
			@Override
			public void run()
			{
				getGame().getResourceManager().load();
				getGame().enterState(getGame().getMenuState().getID());
			}
		});
	}

}
