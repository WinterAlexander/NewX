package me.winter.newx.adventure.level;

import me.winter.newx.adventure.physics.Location;
import me.winter.newx.adventure.world.object.WorldObject;

import java.util.List;

/**
 *
 * Created by Alexander Winter on 2016-01-25.
 */
public abstract class Level
{
	private String name;
	private String musicName;
	private Location playerSpawn;
	private List<WorldObject> worldObjects;

	public abstract void save() throws Exception;
	public abstract void load() throws Exception;

	public String getName()
	{
		return name;
	}

	public void setName(String levelName)
	{
		this.name = levelName;
	}

	public String getMusic()
	{
		return musicName;
	}

	public void setMusic(String musicName)
	{
		this.musicName = musicName;
	}

	public Location getPlayerSpawn()
	{
		return playerSpawn;
	}

	public void setPlayerSpawn(Location playerSpawn)
	{
		this.playerSpawn = playerSpawn;
	}

	public List<WorldObject> getWorldObjects()
	{
		return worldObjects;
	}

	public void setWorldObjects(List<WorldObject> objects)
	{
		this.worldObjects = objects;
	}
}
