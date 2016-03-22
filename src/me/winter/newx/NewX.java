package me.winter.newx;

import me.winter.newx.adventure.AdventureState;
import me.winter.newx.config.Lang;
import me.winter.newx.config.NewXConfig;
import me.winter.newx.config.input.InputLayout;
import me.winter.newx.resource.ResourceManager;
import me.winter.newx.resource.SimpleResourceManager;
import me.winter.newx.util.FileUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import me.winter.newx.menu.MenuState;

import java.io.File;

public class NewX extends StateBasedGame
{
	private ResourceManager resourceManager;
	private InputLayout inputLayout;
	private NewXConfig config;
	private Lang lang;

	private LoadingState loading;
	private MenuState menu;
	private AdventureState adventure;


	public NewX()
	{
		super("NewX");

		this.resourceManager = new SimpleResourceManager();
		this.inputLayout = new InputLayout();
		this.config = new NewXConfig();
		this.lang = new Lang();
	}

	@Override
	public void initStatesList(GameContainer windows) throws SlickException 
	{
		this.inputLayout.setInput(getContainer().getInput());

		windows.getInput().addPrimaryListener(new WindowListener(this));
		windows.getInput().enableKeyRepeat();

		this.loading = new LoadingState(this);
		this.menu = new MenuState(this);
		this.adventure = new AdventureState(this);

		this.loading.register();
		this.menu.register();
		this.adventure.register();
	}

	@Override
	public AppGameContainer getContainer()
	{
		return (AppGameContainer)super.getContainer();
	}

	public File getDataFolder()
	{
		return new File(FileUtil.getAppData() + File.separator + "." + getTitle());
	}
	
	public DynamicGameState getState(int id)
	{
		return (DynamicGameState) super.getState(id);
	}

	public DynamicGameState getCurrentState()
	{
		return (DynamicGameState)super.getCurrentState();
	}

	public LoadingState getLoadingState()
	{
		return this.loading;
	}

	public MenuState getMenuState()
	{
		return this.menu;
	}

	public AdventureState getAdventureState()
	{
		return this.adventure;
	}

	public void enterState(GameState state)
	{
		enterState(state.getID());
	}

	public ResourceManager getResourceManager()
	{
		return resourceManager;
	}

	public InputLayout getInputLayout()
	{
		return inputLayout;
	}

	public NewXConfig getConfig()
	{
		return config;
	}

	public Lang getLang()
	{
		return lang;
	}
}
