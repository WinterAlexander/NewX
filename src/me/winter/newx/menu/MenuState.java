package me.winter.newx.menu;

import me.winter.newx.DynamicGameState;
import me.winter.newx.NewX;
import me.winter.newx.Task;
import me.winter.newx.adventure.level.ExampleLevel;
import me.winter.newx.menu.component.Button;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.List;

public class MenuState extends DynamicGameState
{
	private List<Button> buttons;
	private TrueTypeFont font;

	public MenuState(NewX game)
	{
		super(game);
		this.buttons = new ArrayList<>();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{
		g.setFont(this.font);
			
		getResourceManager().getImage("bg_montagne").draw(0, 0, container.getWidth(), container.getHeight());
		
		int w = (int) ((float)getResourceManager().getImage("menu_logo").getWidth() / (float)getResourceManager().getImage("menu_logo").getHeight() * (float)container.getHeight() * 0.30f);
		if(w > container.getWidth())
			w = container.getWidth();
		
		int h = (int) (container.getHeight() * 0.30);
		
		int logox = (int) (container.getWidth() / 2f - w / 2f);
		int logoy = (int) (container.getHeight() * 0.1);
		getResourceManager().getImage("menu_logo").draw(logox, logoy, w, h);
		
			
		for(Button b : this.getButtons())
			b.render(container, g);
		
	}

	@Override
	public void register()
	{
		super.register();
		getGame().getContainer().getInput().addMouseListener(new MenuListener(this));
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
	{
		super.init(container, game);
		this.setMusic(getResourceManager().getAudio("music_greenlife"));
	}

	
	@Override
	public void enter(GameContainer container, StateBasedGame game)
	{

		super.enter(container, game);
		
		this.buttons.add(new Button(this, 30, 50, 40, 10, "menu_play", "menu_button", () ->
		{
			getResourceManager().getAudio("sound_ding").playAsSoundEffect(1f, 1f, false);
			getGame().getAdventureState().load(new ExampleLevel());
			getGame().enterState(getGame().getAdventureState());
		}));


		this.buttons.add(new Button(this, 30, 65, 40, 10, "menu_exit", "menu_button", () ->
		{
			getResourceManager().getAudio("sound_dong").playAsSoundEffect(1f, 1f, false);
			getScheduler().addTask(new Task(100, false)
			{
				@Override
				public void run()
				{
					getGame().getContainer().exit();
				}
			});
		}));
	}
	
	@Override
	public void leave(GameContainer container, StateBasedGame game)
	{
		this.buttons.clear();
		this.getScheduler().cancelAll();
		super.leave(container, game);
	}
	
	public List<Button> getButtons()
	{
		return buttons;
	}

	@Override
	public void displayModeChanged(int width, int height, boolean fullscreen)
	{
		this.font = new TrueTypeFont(getGame().getResourceManager().getFont("trebuchet").deriveFont(getGame().getContainer().getHeight() / 32f), true);
	}
}
