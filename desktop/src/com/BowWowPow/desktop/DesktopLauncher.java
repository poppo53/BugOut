package com.BowWowPow.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.BowWowPow.BowWowPow;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "BowWowPow";
//		config.width = 272;
//		config.height = 408;
		config.width = 544;
		config.height = 816;
		new LwjglApplication(new BowWowPow(), config);
	}
}
