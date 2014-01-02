package com.trail.teachers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Teacher extends Actor {
	public Teacher(String name , int uniqNum ){//assuming we store things like(img/Teacher_<num>)
		super.setName(name);
		icon = new Texture("img/Teacher_" + uniqNum);
		
	}
	public Teacher(String name,int uniqNum,String iconLoc){// use this and then initialize sprite (saves memory)
		super.setName(name);
		icon = new Texture(iconLoc);
		number = uniqNum;
	}
	public Teacher(String name,int uniqNum,String iconLoc,String spriteLoc){//full featured constructor, dont use this
		super.setName(name);
		number = uniqNum;
		icon = new Texture(iconLoc);
		sprite = new Sprite(new Texture(spriteLoc));
	}

	
	public Sprite sprite;
	private Texture icon;
	private Texture spriteTexture;
	private int number;		//uniq progrmaticly replicable unique id(for the selection screen)
	
	//Phyics properties
	//Example: Body (a thing to hold fixtures) (for movement
	BodyDef ballDef = new BodyDef();
//	ballDef.type = BodyType.DynamicBody;
//	ballDef.position.set(0, 4);
	
	//ball shape
	CircleShape shape = new CircleShape();
//	shape.setRadius(1f);
	
	//Fixture (the shape and physical properties)
	FixtureDef physicalProperties;
//	fixtureDef.shape = shape;
//	fixtureDef.density = 2.5f;
//	fixtureDef.friction = .25f;//friction from 0 - 1
//	fixtureDef.restitution = .75f; //loss in force from bounces (0 = no bounce)(1 = full gain)
	
	
	public void inititialize(){
		
	}
	public void doPhysics(){

	

}
//splashTexture = new Texture("img/splash.png");