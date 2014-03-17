package com.gxp.aware.java;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import com.gxp.aware.core.GameMain;

public class GameMainDesktop {
	public static void main (String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.useGL20 = false;
		config.width = 480;
		config.height = 320;
		new LwjglApplication(new GameMain(), config);
	}
}
