package me.winter.newx.adventure;


import org.newdawn.slick.Input;
import org.newdawn.slick.InputListener;

public class AdventureListener implements InputListener
{
	private AdventureState state;

	public AdventureListener(AdventureState state)
	{
		this.state = state;
	}

	@Override
	public void mouseClicked(int arg0, int x, int y, int arg3)
	{

	}

	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy)
	{
		
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy)
	{
		this.state.getGui().onMouseMove(newx, newy);
	}

	@Override
	public void mousePressed(int button, int x, int y)
	{

	}

	@Override
	public void mouseReleased(int button, int x, int y)
	{
		if(Input.MOUSE_LEFT_BUTTON == button)
			this.state.getGui().onMouseClick(x, y);
	}

	@Override
	public void mouseWheelMoved(int change)
	{
		double newzoom = this.state.getDrawer().getCamera().getZoom();
		newzoom -= change / 100;
		this.state.getDrawer().getCamera().setZoom(newzoom);
	}

	@Override
	public void inputEnded()
	{

	}

	@Override
	public void inputStarted()
	{

	}

	@Override
	public boolean isAcceptingInput()
	{
		return state.getGame().getCurrentState() == state;
	}

	@Override
	public void setInput(Input input)
	{

	}

	@Override
	public void keyPressed(int key, char c)
	{
		if(key == Input.KEY_F12)
			this.state.setDebugMode(!this.state.isDebugMode());

		if(this.state.getGui().getChat().isOpen())
		{
			state.getGui().getChat().input(key, c);
			return;
		}

		if(key == Input.KEY_ENTER)
			state.getGui().getChat().setOpen(true);

	}

	@Override
	public void keyReleased(int key, char c)
	{
		if(!this.state.getGui().getChat().isOpen())
		{
			if(key == Input.KEY_ESCAPE)
				this.state.getGui().openMenu();
		}
	}

	@Override
	public void controllerButtonPressed(int controller, int button)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerButtonReleased(int controller, int button)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerDownPressed(int controller)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerDownReleased(int controller)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerLeftPressed(int controller)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerLeftReleased(int controller)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerRightPressed(int controller)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerRightReleased(int controller)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerUpPressed(int controller)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerUpReleased(int controller)
	{
		// TODO Auto-generated method stub

	}

}
