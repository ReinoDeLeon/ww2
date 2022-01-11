package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

import java.awt.Image;

import javax.swing.ImageIcon;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "WW2 animation game";
		config.width = 800;
		config.height = 480;
		config.resizable = false;
		new LwjglApplication(new MyGdxGame(), config);
	}
}
