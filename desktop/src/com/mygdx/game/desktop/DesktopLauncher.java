package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MainGame;

import java.awt.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = (int)screenSize.getWidth();
		config.height = (int)screenSize.getHeight();
		config.foregroundFPS = 60;
		config.fullscreen = true;
		new LwjglApplication(new MainGame(), config);


	}
}
