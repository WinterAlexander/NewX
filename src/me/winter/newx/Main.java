package me.winter.newx;

import me.winter.newx.util.FileUtil;
import me.winter.newx.util.StringUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.util.Log;

import javax.swing.*;
import java.io.File;

public class Main
{
	public static void main(String[] args)
	{
		try
		{
			NewX game = new NewX();

			File directory = game.getDataFolder();
			FileUtil.createDirectory(directory);

			File settingsFile = new File(directory, "settings.conf");

			game.getConfig().load(settingsFile);
			game.getInputLayout().load(new File(directory, "input.conf"));
			game.getLang().load(game.getConfig().getLangFile());

			AppGameContainer windows = new AppGameContainer(game);

			windows.setDisplayMode(game.getConfig().getWindowsWidth(), game.getConfig().getWindowsHeight(), game.getConfig().isFullscreen());
			windows.setForceExit(false);
			windows.setVSync(game.getConfig().isvSync());
			windows.setSmoothDeltas(true);
			windows.setAlwaysRender(true);
			windows.setUpdateOnlyWhenVisible(false);
			windows.setTitle(game.getTitle());
			windows.setShowFPS(false);
			windows.setMusicVolume(game.getConfig().getMusicVolume());
			windows.setSoundVolume(game.getConfig().getSoundVolume());
			windows.setIcons(new String[]{"icon16.png", "icon32.png"});
			windows.start();

			game.getConfig().save(settingsFile);
		}
		catch(Throwable throwable)
		{
			Log.error(throwable);
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "We are sorry, an internal error occurred during your game session: \n\n" + StringUtil.getStackTrace(throwable) + "\nPlease report this error to me at a.w1nter@hotmail.com", "NewX has crashed :(", JOptionPane.ERROR_MESSAGE);
			frame.dispose();
		}
	}
}
