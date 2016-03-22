package me.winter.newx.adventure;

import me.winter.newx.DynamicGameState;
import me.winter.newx.NewX;
import me.winter.newx.adventure.gui.Gui;
import me.winter.newx.config.input.InputLayout;
import me.winter.newx.adventure.level.Level;
import me.winter.newx.adventure.world.World;
import me.winter.newx.adventure.world.WorldTickTask;
import me.winter.newx.adventure.world.object.creature.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

public class AdventureState extends DynamicGameState
{
	private Level level;

	private World world;
	private Drawer drawer;
	private Camera camera;
	private Gui gui;

	private boolean debugMode;

	public AdventureState(NewX game)
	{
		super(game);
		this.gui = new Gui(this);
		this.drawer = new Drawer(this);
		this.world = new World(this);
		this.camera = new Camera(this.world);
	}

	@Override
	public void register()
	{
		super.register();
		getGame().getContainer().getInput().addListener(new AdventureListener(this));
	}

	public void load(Level level)
	{
		this.level = level;

		this.getScheduler().cancelAll();

		this.world.load(this.level);
		this.setMusic(getResourceManager().getAudio(this.level.getMusic()));

		getScheduler().addTask(new WorldTickTask(this.world));
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game)
	{
		if(this.level == null)
			throw new IllegalStateException("Can't enter Adventure state without any level");

		super.enter(container, game);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{
		this.drawer.setGraphics(g);
		this.world.render(this.drawer);
		this.gui.render(g, container, this.drawer);
	}

	public Player getPlayer()
	{
		return world.getPlayer();
	}

	public Level getLevel()
	{
		return level;
	}

	public boolean isLoaded()
	{
		return this.level != null;
	}

	public World getWorld()
	{
		return world;
	}

	public Drawer getDrawer()
	{
		return drawer;
	}

	public Camera getCamera()
	{
		return camera;
	}

	public void setCamera(Camera camera)
	{
		this.camera = camera;
	}

	public InputLayout getInputLayout()
	{
		return getGame().getInputLayout();
	}

	public Gui getGui()
	{
		return gui;
	}

	public boolean isDebugMode()
	{
		return debugMode;
	}

	public void setDebugMode(boolean debugMode)
	{
		this.debugMode = debugMode;
	}

	@Override
	public void displayModeChanged(int width, int height, boolean fullscreen)
	{
		getGui().reloadFonts();
	}
}
