package com.ayp.arrowstormgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ayp.arrowstormgame.ArrowStormGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Arrow Storm";
		config.width = 480;
		config.height = 800;
		new LwjglApplication(new ArrowStormGame(), config);

	}
}
