package me.winter.newx.adventure.level;

import me.winter.newx.adventure.physics.Location;
import me.winter.newx.adventure.world.World;
import me.winter.newx.adventure.world.object.WorldObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by 1541869 on 2016-01-25.
 */
public class FileLevel extends Level
{
	private File file;

	public FileLevel(File file)
	{
		this.file = file;
		this.setName(null);
		this.setMusic(null);
		this.setPlayerSpawn(null);
		this.setWorldObjects(null);
	}

	public FileLevel(File file, String levelName, String music, Location playerSpawn, World world)
	{
		this.file = file;

		this.setName(levelName);
		this.setMusic(music);
		this.setPlayerSpawn(playerSpawn);
		this.setWorldObjects(new ArrayList<>());

		for(WorldObject object : world.getWorldObjects())
			if(object.doSave())
				getWorldObjects().add(object);
	}

	@Override
	public void save() throws Exception
	{
		if(this.file.exists() && (!this.file.delete() || !this.file.createNewFile()))
			throw new IOException("Couldn't delete and/or recreate file " + file.getName());

		ObjectOutputStream stream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(this.file)));

		stream.writeObject(getName());
		stream.writeObject(getMusic());
		stream.writeObject(getPlayerSpawn());
		stream.writeObject(getWorldObjects());

		stream.flush();
		stream.close();
	}

	@Override
	public void load() throws Exception
	{
		ObjectInputStream stream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(this.file)));

		this.setName((String)stream.readObject());
		this.setMusic((String)stream.readObject());
		this.setPlayerSpawn((Location)stream.readObject());
		this.setWorldObjects((List<WorldObject>)stream.readObject());

		stream.close();
	}

	public File getFile()
	{
		return file;
	}

	public void setFile(File file)
	{
		this.file = file;
	}
}
