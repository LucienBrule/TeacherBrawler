package com.trail.teachers;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.trail.screens.tween.SpriteAccessor;

public class Teacher extends Actor{
	
	batch = new SpriteBatch();
	tweenManager = new TweenManager();
	Tween.registerAccessor(Sprite.class, new SpriteAccessor());
	Texture splashTexture = new Texture("img/splash.png");
	splash = new Sprite(splashTexture);
	splash.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	

}
