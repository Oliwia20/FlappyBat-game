package com.oliwia.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.oliwia.game.FlappyBirb;

public class DesktopLauncher
{
	public static void main (String[] arg)
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//ustawienia okna wyswietlania
		config.width = FlappyBirb.WIDTH;
		config.height = FlappyBirb.HEIGHT;
		config.title = FlappyBirb.TITLE;

		new LwjglApplication(new FlappyBirb(), config);
	}
}
