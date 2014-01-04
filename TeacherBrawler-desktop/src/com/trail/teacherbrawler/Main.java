package com.trail.teacherbrawler;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = TeacherBrawler.TITLE + " v" + TeacherBrawler.Version;
		cfg.useGL20 = true;
		cfg.vSyncEnabled = true;
		
		cfg.width = 1280;
		cfg.height = 720;
		cfg.addIcon("img/icons/DesktopIcon.png", FileType.Internal);
		
		new LwjglApplication(new TeacherBrawler(), cfg);
	}
}

