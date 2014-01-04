package com.trail.teacherbrawler;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.trail.screens.Splash;

public class TeacherBrawler extends Game {
	
	public static final String TITLE = "Teacher Brawler", Version = "0.0";

	@Override
	public void create() {		
		setScreen(new Splash());
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {		
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}
	public static NinePatch processNinePatchFile(String fname) { //http://stackoverflow.com/questions/15355723/loading-nine-patch-image-as-a-libgdx-scene2d-button-background-looks-awful
		Gdx.app.log("processNinePatchFile", "");
		final Texture t = new Texture(Gdx.files.internal(fname));
	    final int width = t.getWidth() - 2;
	    final int height = t.getHeight() - 2;
	    return new NinePatch(new TextureRegion(t, 1, 1, width, height), 3, 3, 3, 3);
	}

	@Override
	public void resume() {
		super.resume();
	}
}
