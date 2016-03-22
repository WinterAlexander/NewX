package me.winter.newx.config.input;

import org.newdawn.slick.Input;

import java.io.Serializable;

/**
 *
 * Created by 1541869 on 2016-02-05.
 */
public interface InputEvent extends Serializable
{
	String getName();
	boolean isPressed(Input input);
}
