package me.winter.newx.adventure.world;

import me.winter.newx.adventure.AdventureState;
import me.winter.newx.adventure.Drawer;
import me.winter.newx.adventure.level.Level;
import me.winter.newx.adventure.physics.Vector;
import me.winter.newx.adventure.world.object.Background;
import me.winter.newx.adventure.world.object.WorldGravity;
import me.winter.newx.adventure.world.object.WorldObject;
import me.winter.newx.adventure.world.object.border.WorldBorder;
import me.winter.newx.adventure.world.object.creature.Player;
import me.winter.newx.adventure.world.object.proprieties.Visible;
import me.winter.newx.adventure.physics.Direction;
import me.winter.newx.adventure.physics.collision.CollisionBox;
import me.winter.newx.adventure.physics.collision.Limit;
import me.winter.newx.adventure.physics.collision.TopLimit;
import me.winter.newx.adventure.world.object.proprieties.Solid;
import me.winter.newx.adventure.world.object.proprieties.Touchable;
import me.winter.newx.util.SortingUtil;
import org.newdawn.slick.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class World
{
	private AdventureState adventureState;

	private List<WorldObject> objects;

	public World(AdventureState state)
	{
		this.adventureState = state;
		this.objects = new ArrayList<>();
	}

	public List<WorldObject> getWorldObjects()
	{
		return this.objects;
	}
	
	public void render(Drawer d)
	{
		int minPriority = 0, maxPriority = 1;

		for(WorldObject object : this.getWorldObjects())
		{
			if(object instanceof Visible)
			{
				if(((Visible)object).getPriority() < minPriority)
					minPriority = ((Visible)object).getPriority();

				if(((Visible)object).getPriority() > maxPriority)
					maxPriority = ((Visible)object).getPriority();
			}
		}

		for(int priority = minPriority; priority <= maxPriority; priority++)
		{
			for(WorldObject object : this.getWorldObjects())
				if(object instanceof Visible)
					if(((Visible)object).getPriority() == priority)
						((Visible)object).render(d);
		}

		if(getAdventure().isDebugMode())
			renderLimits(d);
	}
	
	public void renderLimits(Drawer d)
	{
		for(Limit limit : this.getLimits())
		{
			Color color = Color.gray;
			switch(limit.getBlockDirection())
			{
			case DOWN:
				color = Color.red;
				break;
			case LEFT:
				color = Color.blue;
				break;
			case RIGHT:
				color = Color.green;
				break;
			case UP:
				color = Color.yellow;
			}

			d.setColor(color);
			d.drawLine(limit.getStart(), limit.getEnd());

		}
	}

	public Vector getNoCollisionVector(Vector vector, Solid solid, boolean penetration)
	{
		CollisionBox box = solid.getCollisionBox();
		List<Limit> collisions = new ArrayList<>();

		for(Limit limit : this.getLimits())
		{
			if(box.hasLimit(limit))
				continue;

			for(Limit boxLimit : box.getLimits())
			{
				if(limit.collides(vector, boxLimit, penetration))
				{
					collisions.add(limit);
					for(Limit collidingLimit : new ArrayList<>(collisions))
					{
						if(collidingLimit.equals(limit))
							continue;

						if(limit.canSubstitute(collidingLimit))
						{
							collisions.remove(collidingLimit);
							break;
						}

						if(collidingLimit.canSubstitute(limit))
						{
							collisions.remove(limit);
							break;
						}
					}
				}
			}
		}

		if(collisions.size() == 0)
			return vector;

		double[] priorities = new double[collisions.size()];

		for(int i = 0; i < collisions.size(); i++)
		{
			for(Limit limit : box.getLimits())
			{
				double current = collisions.get(i).getPriority(vector, limit);

				if(current > priorities[i])
					priorities[i] = current;
			}
		}

		collisions = Arrays.asList(Arrays.copyOf(SortingUtil.quickSort(collisions.toArray(), priorities), collisions.size(), Limit[].class));


		for(Limit limit : collisions)
		{
			for(Limit boxLimit : box.getLimits())
				if(limit.collides(vector, boxLimit, penetration))
				{
					Solid owner = getOwner(limit);

					if(owner instanceof Touchable || solid instanceof Touchable)
					{
						double power = 0;

						switch(limit.getBlockDirection())
						{
							case UP:
								power = vector.getY();
								break;

							case DOWN:
								power = -vector.getY();
								break;

							case RIGHT:
								power = vector.getX();
								break;

							case LEFT:
								power = -vector.getX();
								break;
						}

						if(power < 0)
							power = 0;

						if(owner instanceof Touchable)
							((Touchable)owner).onTouch(solid, limit, power);

						if(solid instanceof Touchable)
							((Touchable)solid).onTouch(solid, limit, -power);
					}

					vector = limit.replace(vector, boxLimit);
					vector.round(2);
				}
		}


		return vector;
	}
	
	public boolean isBlocked(CollisionBox box, Direction direction)
	{
		for(WorldObject element : this.getWorldObjects()) //pour chaque éléments
			if(element instanceof Solid) //si il est interactif
				for(Limit limit : ((Solid) element).getCollisionBox().getLimits())
					if(limit.isTouching(box))
						if(limit.getBlockDirection().equals(direction))
							return true;
		return false;
	}

	public List<Limit> getLimits()
	{
		List<Limit> limits = new ArrayList<>();
		for(WorldObject element : getWorldObjects())
			if(element instanceof Solid)
				limits.addAll(((Solid)element).getCollisionBox().getLimits());
		 
		return limits;
	}

	public Solid getOwner(Limit limit)
	{
		for(WorldObject element : getWorldObjects())
			if(element instanceof Solid)
				if(((Solid)element).getCollisionBox().hasLimit(limit))
					return (Solid)element;
		return null;
	}
	
	public float getStability(CollisionBox box)
	{
		float bestStability = 0;
		
		for(WorldObject element : getWorldObjects())
			if(element instanceof Solid)
				for(Limit limit : ((Solid)element).getCollisionBox().getLimits())
					if(limit instanceof TopLimit)
						if(limit.isTouching(box))
							if(((TopLimit)limit).getStability() > bestStability)
								bestStability = ((TopLimit)limit).getStability();
		 
		return bestStability;
	}

	public WorldGravity getGravity()
	{
		for(WorldObject object : getWorldObjects())
			if(object instanceof WorldGravity)
				return (WorldGravity)object;

		return null;
	}

	public void setGravity(WorldGravity gravity)
	{
		for(WorldObject object : new ArrayList<>(getWorldObjects()))
			if(object instanceof WorldGravity)
				getWorldObjects().remove(object);

		getWorldObjects().add(gravity);
	}

	public WorldBorder getBorder()
	{
		for(WorldObject object : getWorldObjects())
			if(object instanceof WorldBorder)
				return (WorldBorder)object;

		return null;
	}

	public void setBorder(WorldBorder border)
	{
		for(WorldObject object : new ArrayList<>(getWorldObjects()))
			if(object instanceof WorldBorder)
				getWorldObjects().remove(object);

		getWorldObjects().add(border);
	}

	public Player getPlayer()
	{
		for(WorldObject object : getWorldObjects())
			if(object instanceof Player)
				return (Player)object;

		return null;
	}

	public Background getBackground()
	{
		for(WorldObject object : getWorldObjects())
			if(object instanceof Background)
				return (Background)object;

		return null;
	}

	public void setBackground(Background background)
	{
		for(WorldObject object : new ArrayList<>(getWorldObjects()))
			if(object instanceof Background)
				getWorldObjects().remove(object);

		getWorldObjects().add(background);
	}

	public void load(Level level)
	{
		clear();

		for(WorldObject object : level.getWorldObjects())
		{
			object.setWorld(this);
			this.getWorldObjects().add(object);
		}

		for(WorldObject object : new ArrayList<>(this.getWorldObjects()))
			if(object instanceof Player)
				this.getWorldObjects().remove(object);

		this.getWorldObjects().add(new Player(this, level.getPlayerSpawn().clone()));
	}

	public void clear()
	{
		this.objects = new ArrayList<>();
	}

	public AdventureState getAdventure()
	{
		return adventureState;
	}
}
