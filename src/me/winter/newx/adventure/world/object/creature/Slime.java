package me.winter.newx.adventure.world.object.creature;


import me.winter.newx.adventure.Drawer;
import me.winter.newx.adventure.gui.LifeBar;
import me.winter.newx.adventure.physics.collision.CollisionBox;
import me.winter.newx.adventure.physics.collision.Limit;
import me.winter.newx.adventure.physics.collision.RectangleCollisionBox;
import me.winter.newx.adventure.world.World;
import me.winter.newx.adventure.world.object.proprieties.Solid;
import me.winter.newx.adventure.world.object.proprieties.Visible;
import me.winter.newx.adventure.physics.Direction;
import me.winter.newx.adventure.physics.Location;
import me.winter.newx.adventure.physics.Vector;
import org.newdawn.slick.Color;

import java.util.Random;

public class Slime extends Creature
{
	public static double DEFAULT_WIDTH = 0.75;
	public static double DEFAULT_HEIGHT = 0.5;
	public static int DEFAULT_HP = 10;
	public static double DEFAULT_SPEED = 1.25;
	public static Color DEFAULT_COLOR = new Color(1, 223, 58);

	private Color color;
	private double width, height;
	private int sync;

	public Slime(World world)
	{
		this(world, new Location(0, 0, Direction.LEFT), DEFAULT_COLOR, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_HP, DEFAULT_SPEED);
	}

	public Slime(World world, Location loc, Color color, double width, double height, int health, double speed)
	{
		super(world, loc, health, speed);
		this.color = color;
		this.width = width;
		this.height = height;

		this.sync = new Random().nextInt(40);
	}

	@Override
	protected void die()
	{
		getWorld().getWorldObjects().remove(this);
	}

	@Override
	protected boolean isPenetrating()
	{
		return false;
	}

	@Override
	protected Direction getFacing()
	{
		return Direction.RIGHT;
	}

	@Override
	protected Vector getMovement()
	{
		Player player = getWorld().getPlayer();

		if(player == null)
			return new Vector(0, 0);

		return getLocation().difference(player.getLocation());
	}

	//Visible
	@Override
	public void render(Drawer drawer)
	{
		if(this.isDeath())
			return;

		double graphicalHeight = this.height + this.height * 0.1f * Math.sin((getScheduler().getGameTimeMillis() / 20 + this.sync) / Math.PI);
		
		double x = this.getLocation().getX() - this.width / 2;

		drawer.setColor(new Color(getColor().r, getColor().g, getColor().b, 0.5f));
		drawer.fillArc(x, this.getLocation().getY() - (1D / 3D) * graphicalHeight, this.getWidth(), graphicalHeight, 160, 20);

		drawer.setColor(getColor());
		drawer.fillOval(x, this.getLocation().getY() - (1D / 6D) * graphicalHeight, this.getWidth(), graphicalHeight / 2);

		drawer.setColor(Color.black);
		if(this.isSuffering())
			drawer.setColor(Color.red);

		drawer.drawArc(x, this.getLocation().getY() - (1D / 3D) * graphicalHeight, this.getWidth(), graphicalHeight, 160, 20);
		drawer.drawArc(x, this.getLocation().getY() - (1D / 6D) * graphicalHeight, this.getWidth(), graphicalHeight / 2, 20, 160);
		
		if(this.getLocation().getDirection() == Direction.RIGHT)
			drawer.fillOval(x + this.getWidth() * 0.8D, this.getLocation().getY() + graphicalHeight * 0.05D, this.getWidth() / 10D, graphicalHeight / 4D);
		else
			drawer.fillOval(x + this.getWidth() * 0.1D, this.getLocation().getY() + graphicalHeight * 0.05D, this.getWidth() / 10D, graphicalHeight / 4D);
		
		new LifeBar(this).render(drawer);
	}

	
	public Color getColor()
	{
		return this.color;
	}


	@Override
	public int getPriority()
	{
		return Visible.MEDIUM_PRIORITY;
	}

	public double getWidth()
	{
		return this.width;
	}

	public double getHeight()
	{
		return this.height;
	}

	@Override
	public CollisionBox getCollisionBox()
	{
		return new RectangleCollisionBox(this, this.getLocation().getX() - this.width / 2, this.getLocation().getY(), width, height / 2);
	}

	@Override
	public void onTouch(Solid solid, Limit limit, double power)
	{

	}

	public static Slime generateRandom(World world, Location loc)
	{
		Random r = new Random();
		double sizeMultiplier = r.nextDouble() * 3;
		if(sizeMultiplier < 0.1)
			sizeMultiplier = 0.1;

		double speedMultiplier = r.nextDouble() * 5;
		if(speedMultiplier < 0.5)
			speedMultiplier = 0.5;

		int healthBonus = 15 - r.nextInt(20);
		return new Slime(world, loc, new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)),  DEFAULT_WIDTH * sizeMultiplier, DEFAULT_HEIGHT * sizeMultiplier, DEFAULT_HP + healthBonus, DEFAULT_SPEED * speedMultiplier);
	}
}