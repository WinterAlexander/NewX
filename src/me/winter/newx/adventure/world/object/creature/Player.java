package me.winter.newx.adventure.world.object.creature;

import me.winter.newx.adventure.gui.LifeBar;

import me.winter.newx.adventure.world.object.properties.Touchable;
import me.winter.newx.config.input.InputAction;
import me.winter.newx.adventure.physics.collision.CollisionBox;
import me.winter.newx.adventure.physics.collision.Limit;
import me.winter.newx.adventure.physics.collision.RectangleCollisionBox;
import me.winter.newx.adventure.world.World;
import me.winter.newx.adventure.world.object.properties.Solid;
import me.winter.newx.adventure.Drawer;
import me.winter.newx.adventure.physics.Direction;
import me.winter.newx.adventure.physics.Location;
import me.winter.newx.adventure.physics.Vector;
import me.winter.newx.adventure.world.object.properties.Visible;
import org.newdawn.slick.Image;

public class Player extends Creature
{
	private double width, height;
	private long flashEnd;
	private long lastFlash;
	private long runningSpriteTimestamp;

	public Player(World world, Location loc)
	{
		this(world, loc, 20, 1);
	}

	public Player(World world, Location loc, int maxHp, double speed)
	{
		super(world, loc, maxHp, speed);
		this.width = 0.6;
		this.height = 1.8;
		this.flashEnd = 0;
		this.lastFlash = 0;
		this.runningSpriteTimestamp = 0;
	}

	@Override
	protected void die()
	{
		setLocation(getAdventure().getLevel().getPlayerSpawn().clone());

		setHealth(getMaxHealth());
		setFlashing(2000);

		setVelocity(new Vector(0, 0));
		previousMovement = getVelocity();


	}

	@Override
	public boolean doSave()
	{
		return false;
	}

	public boolean isSprinting()
	{
		if(getAdventure().getGui().getChat().isOpen() || isDeath())
			return false;

		return getWorld().getAdventure().getInputLayout().isPressed(InputAction.SPRINT);
	}

	public boolean isCrouching()
	{
		if(getAdventure().getGui().getChat().isOpen() || isDeath())
			return false;

		return getWorld().getAdventure().getInputLayout().isPressed(InputAction.CROUCH);
	}

	public boolean isJumping()
	{
		if(getAdventure().getGui().getChat().isOpen() || isDeath())
			return false;

		return getWorld().getAdventure().getInputLayout().isPressed(InputAction.JUMP);
	}

	private boolean leftPressed()
	{
		if(getAdventure().getGui().getChat().isOpen() || isDeath())
			return false;

		return getWorld().getAdventure().getInputLayout().isPressed(InputAction.LEFT);
	}

	private boolean rightPressed()
	{
		if(getAdventure().getGui().getChat().isOpen() || isDeath())
			return false;

		return getWorld().getAdventure().getInputLayout().isPressed(InputAction.RIGHT);
	}

	@Override
	public void move()
	{
		super.move();

		getAdventure().getCamera().center(getLocation().clone().add(0, getHeight() / 2));
	}

	@Override
	public CollisionBox getCollisionBox()
	{
		CollisionBox box = new RectangleCollisionBox(this, this.getLocation().getX() - this.width / 2, this.getLocation().getY(), this.width, this.height / (this.isCrouching() ? 1.5 : 1));
		box.round(2);
		return box;
	}

	@Override
	public void render(Drawer d)
	{
		if(this.isDeath())
			return;

		if(!this.isFlashing() || this.lastFlash + 150 < getWorld().getAdventure().getScheduler().getGameTimeMillis())
		{
			d.drawImage(getSprite(), this.getLocation().getX() - 0.4, this.getLocation().getY(), 0.8, 2);
			if(this.isFlashing() && this.lastFlash + 300 < getWorld().getAdventure().getScheduler().getGameTimeMillis())
			{
				this.lastFlash = getWorld().getAdventure().getScheduler().getGameTimeMillis();
			}
		}

		new LifeBar(this).render(d);
	}

	private Image getSprite()
	{

		if(isCrouching())
		{
			Image image = getResourceManager().getImage("creature_player_crouch");

			if(this.getLocation().getDirection() == Direction.LEFT)
				return image.getFlippedCopy(true, false);

			return image;
		}
		if(!this.isOnGround())
		{
			Image image = getResourceManager().getImage("creature_player_jumping");

			if(this.getLocation().getDirection() == Direction.LEFT)
				return image.getFlippedCopy(true, false);

			return image;
		}

		if(isSprinting() && Math.abs(previousMovement.getX()) > 0.1)
		{
			if(this.runningSpriteTimestamp + 100 > getWorld().getAdventure().getScheduler().getGameTimeMillis())
			{
				Image image = getResourceManager().getImage("creature_player_running_1");

				if(this.getLocation().getDirection() == Direction.LEFT)
					return image.getFlippedCopy(true, false);

				return image;
			}

			if(this.runningSpriteTimestamp + 200 < getWorld().getAdventure().getScheduler().getGameTimeMillis())
				this.runningSpriteTimestamp = getWorld().getAdventure().getScheduler().getGameTimeMillis();

			Image image = getResourceManager().getImage("creature_player_running_2");

			if(this.getLocation().getDirection() == Direction.LEFT)
				return image.getFlippedCopy(true, false);

			return image;
		}

		Image image = getResourceManager().getImage("creature_player_standby");

		if(this.getLocation().getDirection() == Direction.LEFT)
			return image.getFlippedCopy(true, false);

		return image;
	}
	
	@Override
	public int getPriority()
	{
		return Visible.MEDIUM_PRIORITY;
	}

	public void setFlashing(long flash)
	{
		this.flashEnd = getWorld().getAdventure().getScheduler().getGameTimeMillis() + flash;
		this.lastFlash = getWorld().getAdventure().getScheduler().getGameTimeMillis();
	}
	
	public boolean isFlashing()
	{
		return this.flashEnd > getWorld().getAdventure().getScheduler().getGameTimeMillis();
	}

	@Override
	protected boolean isPenetrating()
	{
		return isCrouching();
	}

	@Override
	protected Direction getFacing()
	{
		if(leftPressed() && rightPressed())
			return null;

		if(leftPressed())
			return Direction.LEFT;

		if(rightPressed())
			return Direction.RIGHT;

		return null;
	}

	@Override
	protected Vector getMovement()
	{
		double y = 0;
		if(isOnGround() && isJumping())
			y = 0.5;

		if(leftPressed() == rightPressed())
			return new Vector(0, y);

		if(leftPressed() && getLocation().getDirection() != Direction.LEFT
		|| rightPressed() && getLocation().getDirection() != Direction.RIGHT)
			return new Vector(0, y);

		return new Vector((leftPressed() ? -1 : 1) * (isSprinting() && !isCrouching() ? 0.3 : !isCrouching() ? 0.2 : 0.05), y);
	}

	@Override
	public void onTouch(Touchable solid, Limit limit)
	{
		if(solid instanceof Slime)
			hit(2, new Vector(((Slime)solid).getLocation(), getLocation()).normalize().multiply(0.5));
	}

	@Override
	public double getWidth()
	{
		return width;
	}

	@Override
	public double getHeight()
	{
		return height;
	}
}
