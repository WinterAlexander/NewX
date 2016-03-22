package me.winter.newx.adventure.level;

import me.winter.newx.adventure.physics.Direction;
import me.winter.newx.adventure.physics.Location;
import me.winter.newx.adventure.world.object.Background;
import me.winter.newx.adventure.world.object.WorldGravity;
import me.winter.newx.adventure.world.object.WorldObject;
import me.winter.newx.adventure.world.object.border.RectangleWorldBorder;
import me.winter.newx.adventure.world.object.elements.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Alexander Winter on 2016-02-13.
 */
public class ExampleLevel extends TemplateLevel
{
	public ExampleLevel()
	{
		this.setName("Example");
		this.setMusic("music_greenlife");
		this.setPlayerSpawn(new Location(0, 0, Direction.RIGHT));

		List<WorldObject> objects = new ArrayList<>();

		objects.add(new Background(null, "bg_ferme"));
		objects.add(new Platform(null, -2, -1, 4));
		objects.add(new Ice(null, 2, -4, 20, 3));
		objects.add(new Box(null, 0, 20, 1));
		objects.add(new WorldGravity(null, WorldGravity.GRAVITY_EARTH));
		objects.add(new Television(null, 3, 20, 1, 173d / 242d));
		objects.add(new RectangleWorldBorder(null, -50, -50, 100, 100));
		objects.add(new Dirt(null, -10, -2, 8, 1, 0.5f));
		Bath bath = new Bath(null, -6, -1, 2, 1);
		objects.add(bath);
		objects.add(new BathFront(bath));
		objects.add(new Box(null, -5.5, 3, 1));
		objects.add(new Taco(null, new Location(1, 1)));

		objects.add(new Box(null, 3, 40, 1));
		objects.add(new Box(null, 4, 40, 1));
		objects.add(new Box(null, 5, 40, 1));
		objects.add(new Box(null, 6, 40, 1));

		this.setWorldObjects(objects);

	}

}
