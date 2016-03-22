package me.winter.newx;

import me.winter.newx.config.Lang;
import me.winter.newx.resource.ResourceManager;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public abstract class DynamicGameState extends BasicGameState
{
	private NewX game;
	private Scheduler scheduler;
	private Audio music;
	private int id;

	private int previousWidth, previousHeight;
	private boolean previousFullscreen;

	public DynamicGameState(NewX game)
	{
		this.game = game;
		this.id = -1;
	}

	public void displayModeChanged(int width, int height, boolean fullscreen)
	{

	}

	public void register()
	{
		this.id = this.getGame().getStateCount();
		this.game.addState(this);
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
	{
		this.scheduler = new Scheduler();
		this.music = null;
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
		this.scheduler.update();
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game)
	{
		if(container.getWidth() != previousWidth || container.getHeight() != previousHeight || container.isFullscreen() != previousFullscreen)
			displayModeChanged(container.getWidth(), container.getHeight(), container.isFullscreen());

		this.scheduler.start();
		if(this.music != null)
			this.music.playAsMusic(1f, 1f, true);
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game)
	{
		this.scheduler.pause();
		if(this.music != null)
			this.music.stop();

		this.previousWidth = container.getWidth();
		this.previousHeight = container.getHeight();
		this.previousFullscreen = container.isFullscreen();
	}

	public Scheduler getScheduler()
	{
		return scheduler;
	}

	public Audio getMusic()
	{
		return music;
	}

	public void setMusic(Audio music)
	{
		this.music = music;
	}

	public NewX getGame()
	{
		return this.game;
	}

	@Override
	public int getID()
	{
		return this.id;
	}

	public boolean isCurrentState()
	{
		return getGame().getCurrentState() == this;
	}

	public ResourceManager getResourceManager()
	{
		return getGame().getResourceManager();
	}

	public Lang getLang()
	{
		return getGame().getLang();
	}

	public String getMessage(String key)
	{
		return getGame().getLang().get(key);
	}
}
