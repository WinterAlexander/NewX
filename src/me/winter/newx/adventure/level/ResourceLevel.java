package me.winter.newx.adventure.level;

import me.winter.newx.adventure.physics.Location;
import me.winter.newx.adventure.world.object.WorldObject;

import java.io.*;
import java.util.List;

/**
 *
 * Created by Alexander Winter on 2016-01-25.
 */
public class ResourceLevel extends Level
{
	private String file;

	public ResourceLevel(String file)
	{
		this.file = file;
		this.setName(null);
		this.setMusic(null);
		this.setPlayerSpawn(null);
		this.setWorldObjects(null);
	}

	@Override
	public void save() throws Exception
	{
		throw new UnsupportedOperationException("Can't save a ResourceLevel");
	}

	@Override
	public void load() throws Exception
	{
		ObjectInputStream stream = new ObjectInputStream(new BufferedInputStream(getClass().getClassLoader().getResourceAsStream(file)));

		this.setName((String)stream.readObject());
		this.setMusic((String)stream.readObject());
		this.setPlayerSpawn((Location)stream.readObject());
		this.setWorldObjects((List<WorldObject>)stream.readObject());

		stream.close();
	}
}