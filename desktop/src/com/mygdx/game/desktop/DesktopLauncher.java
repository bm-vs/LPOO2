package com.mygdx.game.desktop;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.GUI.Casino;


public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width= Casino.WIDTH;
		config.height = Casino.HEIGHT;
		config.title = Casino.TITTLE;

		new LwjglApplication(new Casino(), config);
	}
}
