package me.winter.newx.adventure.physics.collision;

import me.winter.newx.adventure.world.object.WorldObject;

import java.awt.*;
import java.awt.geom.Area;
import java.util.List;

/**
 *
 * Created by Alexander Winter on 2016-02-01.
 */
public abstract class CollisionBox
{
	private WorldObject object;

	public CollisionBox(WorldObject object)
	{
		this.object = object;
	}

	public boolean collides(CollisionBox box)
	{
		Polygon thisPoly = new Polygon();
		Polygon thatPoly = new Polygon();

		for(Limit limit : this.getLimits())
		{
			thisPoly.addPoint((int)(limit.getStart().getX() * 1000), (int)(limit.getStart().getY() * 1000));
			thisPoly.addPoint((int)(limit.getEnd().getX() * 1000), (int)(limit.getEnd().getY() * 1000));
		}

		for(Limit limit : box.getLimits())
		{
			thatPoly.addPoint((int)(limit.getStart().getX() * 1000), (int)(limit.getStart().getY() * 1000));
			thatPoly.addPoint((int)(limit.getEnd().getX() * 1000), (int)(limit.getEnd().getY() * 1000));
		}

		Area thisArea = new Area(thisPoly);
		thisArea.intersect(new Area(thatPoly));

		return !thisArea.isEmpty();
	}

	public boolean isTouching(CollisionBox box)
	{
		for(Limit limit : getLimits())
			if(limit.isTouching(box))
				return true;

		return false;
	}

	public boolean hasLimit(Limit limit)
	{
		for(Limit boxLimit : getLimits())
			if(boxLimit.equals(limit))
				return true;

		return false;
	}
	
	public double getHeight()
	{
		double minY = getLimits().get(0).getEnd().getX(), maxY = getLimits().get(0).getEnd().getY();
		
		for(Limit limit : getLimits())
		{
			if(limit.getStart().getY() < minY)
				minY = limit.getStart().getY();
			
			if(limit.getEnd().getY() < minY)
				minY = limit.getEnd().getY();

			if(limit.getStart().getY() > maxY)
				maxY = limit.getStart().getY();

			if(limit.getEnd().getY() > maxY)
				maxY = limit.getEnd().getY();
		}
		
		return maxY - minY;
	}
	
	public double getWidth()
	{
		double minX = getLimits().get(0).getEnd().getX(), maxX = getLimits().get(0).getEnd().getX();

		for(Limit limit : getLimits())
		{
			if(limit.getStart().getX() < minX)
				minX = limit.getStart().getX();

			if(limit.getEnd().getX() < minX)
				minX = limit.getEnd().getX();

			if(limit.getStart().getX() > maxX)
				maxX = limit.getStart().getX();

			if(limit.getEnd().getX() > maxX)
				maxX = limit.getEnd().getX();
		}

		return maxX - minX;
	}


	public abstract List<Limit> getLimits();
	public void round(int decimals)
	{

	}

	public WorldObject getObject()
	{
		return object;
	}
}
