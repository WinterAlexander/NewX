package me.winter.newx.adventure.level;

/**
 *
 * Created by Alexander Winter on 2016-02-13.
 */
public abstract class TemplateLevel extends Level
{
	@Override
	public void save() throws Exception
	{
		throw new UnsupportedOperationException("Can't save an TemplateLevel");
	}

	@Override
	public void load() throws Exception
	{
		throw new UnsupportedOperationException("Can't load an TemplateLevel");
	}
}
