package me.winter.newx.adventure.world.object.creature;

import me.winter.newx.adventure.physics.Location;
import me.winter.newx.adventure.physics.Vector;
import me.winter.newx.adventure.world.World;
import me.winter.newx.adventure.world.object.WorldObject;
import me.winter.newx.adventure.physics.Direction;
import me.winter.newx.adventure.world.object.properties.*;


public abstract class Creature extends WorldObject implements Visible, Damageable, GravityAffected, Touchable, Body
{
	private long sufferingEnd;
	private double health, maxHealth;
	private double speed, airStability;

	private Location location;
	protected Vector previousMovement;
	private Vector velocity;

	private long lastHit;

	public Creature(World world, Location location, int health, double speed)
	{
		super(world);
		this.location = new Location(location);
		this.previousMovement = new Vector(0, 0);
		this.velocity = null;
		this.sufferingEnd = System.currentTimeMillis();
		this.maxHealth = health;
		this.health = this.maxHealth;
		this.speed = speed;
		this.airStability = 0.1f;
		this.lastHit = getScheduler().getGameTimeMillis() - 3000;
	}

	protected abstract boolean isPenetrating();
	protected abstract Direction getFacing();
	protected abstract Vector getMovement();
	protected abstract void die();

	@Override
	public void move()
	{
		if(isDeath())
			return;

		Vector movement = getMovement();
		movement.multiply(getSpeed());
		movement.round(2);

		boolean wasOnGround = false;


		if(velocity != null && !velocity.isNull())
		{
			previousMovement = velocity;
			velocity = null;
		}
		else if(wasOnGround = isOnGround())
		{
			float stability = getWorld().getStability(getCollisionBox());
			if(stability == 0)
				stability = 1;

			previousMovement.setX(previousMovement.getX() * (1 - stability) + movement.getX() * stability);
			previousMovement.setY(movement.getY());
		}
		else
			previousMovement.setX(previousMovement.getX() * (1 - airStability) + movement.getX() * airStability);

		if(getWorld().getGravity() != null)
			previousMovement.setY(previousMovement.getY() + getWorld().getGravity().getDeltaY());

		Vector vector = getWorld().getNoCollisionVector(previousMovement.clone(), this, isPenetrating());

		getLocation().add(vector);
		getLocation().round(2);

		previousMovement.add(vector).multiply(0.5);

		if(!wasOnGround && isOnGround())
			previousMovement.setY(0);

		if(getFacing() != null)
			getLocation().setDirection(getFacing());

		if(getWorld().getBorder() != null && !getWorld().getBorder().contains(getCollisionBox()))
			kill();
	}

	@Override
	public boolean doSave()
	{
		return !isDeath();
	}

	public boolean isSuffering()
	{
		return this.sufferingEnd > System.currentTimeMillis();
	}

	@Override
	public boolean isDeath()
	{
		return this.health <= 0;
	}

	@Override
	public void kill()
	{
		setHealth(0);
	}

	@Override
	public double getHealth()
	{
		return this.health;
	}

	@Override
	public void setHealth(double health)
	{
		this.health = health;
		if(this.health <= 0)
		{
			this.health = 0;
			die();
		}
	}

	@Override
	public double getMaxHealth()
	{
		return this.maxHealth;
	}

	@Override
	public void hit(double damage, Vector velocity)
	{
		if(this.lastHit + 500 > getScheduler().getGameTimeMillis())
			return;

		setHealth(getHealth() - damage);

		setVelocity(velocity);
		this.lastHit = getScheduler().getGameTimeMillis();
	}

	@Override
	public boolean isOnGround()
	{
		return getWorld().isBlocked(getCollisionBox(), Direction.DOWN);
	}

	public double getSpeed()
	{
		return this.speed;
	}

	public void setSpeed(double speed)
	{
		this.speed = speed;
	}

	public long getSufferingEnd()
	{
		return sufferingEnd;
	}

	public void setSufferingEnd(long sufferingEnd)
	{
		this.sufferingEnd = sufferingEnd;
	}

	public void setMaxHealth(double maxHealth)
	{
		this.maxHealth = maxHealth;
	}

	@Override
	public Location getLocation()
	{
		return location;
	}

	@Override
	public void setLocation(Location location)
	{
		this.location = location;
	}

	@Override
	public Vector getVelocity()
	{
		return velocity;
	}

	@Override
	public void setVelocity(Vector velocity)
	{
		this.velocity = velocity;
	}

	public long getLastHit()
	{
		return lastHit;
	}


	public double getAirStability()
	{
		return airStability;
	}

	public void setAirStability(double airStability)
	{
		this.airStability = airStability;
	}
}
