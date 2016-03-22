package me.winter.newx.resource;

import org.newdawn.slick.Image;
import org.newdawn.slick.openal.Audio;

import java.awt.*;

/**
 *
 * Created by 1541869 on 2016-02-25.
 */
public interface ResourceManager
{
	void load();

	void dispose();

	float getLoadingProgression();

	default boolean isFinished()
	{
		return getLoadingProgression() >= 1f;
	}

	Image getImage(String name);
	Font getFont(String name);
	Audio getAudio(String name);

	String[] getImages();
	String[] getFonts();
	String[] getAudios();
}
