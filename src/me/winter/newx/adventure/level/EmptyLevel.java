package me.winter.newx.adventure.level;

import me.winter.newx.adventure.physics.Direction;
import me.winter.newx.adventure.physics.Location;
import me.winter.newx.adventure.world.object.Background;
import me.winter.newx.adventure.world.object.WorldGravity;
import me.winter.newx.adventure.world.object.WorldObject;
import me.winter.newx.adventure.world.object.border.RectangleWorldBorder;
import me.winter.newx.adventure.world.object.elements.Platform;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Alexander Winter on 2016-02-19.
 */
public class EmptyLevel extends TemplateLevel
{
	public EmptyLevel()
	{
		this.setName("Void");
		this.setMusic("music_lakeofemptiness");
		this.setPlayerSpawn(new Location(0, 0, Direction.RIGHT));

		List<WorldObject> objects = new ArrayList<>();

		objects.add(new Background(null, "bg_montagne"));
		objects.add(new WorldGravity(null, WorldGravity.GRAVITY_EARTH));
		objects.add(new RectangleWorldBorder(null, -50, -50, 100, 100));
		objects.add(new Platform(null, -1, 0, 2));

		this.setWorldObjects(objects);

	}
}
