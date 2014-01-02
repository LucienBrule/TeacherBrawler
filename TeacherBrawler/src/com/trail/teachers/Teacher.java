package com.trail.teachers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.trail.screens.GameScreen;

public class Teacher extends Sprite {
	public Teacher(String name , int uniqNum ){//assuming we store things like(img/Teacher_<num>)
		this.name = name;
		number = uniqNum;
		icon = new Texture("img/Teacher_" + number);
		
	}
	public Teacher(String name,int uniqNum,String iconLoc){// use this and then initialize sprite (saves memory)
		this.name = name;
		icon = new Texture(iconLoc);
		number = uniqNum;
	}
	public Teacher(String name,int uniqNum,String iconLoc,String spriteLoc){//full featured constructor, dont use this
		super(new Sprite(new Texture(spriteLoc)));
		this.name = name;
		number = uniqNum;
		icon = new Texture(iconLoc);
	}

	//<--Graphical Properites-->\\
	private final int number;						//unique progrmaticly replicable unique id(for the selection screen)
	private float animTime = 0;
	private final int SPRITEWIDTH=	128;	//default width for our teacher sprites
	private final int SPRITEHEIGHT=	256;	//default height for our teacher sprites
	private final float animSpeed = 1/60f;
	private Texture icon;					//The Icon for the selection screen and ingame HUD
	private TextureAtlas textureAtlas;		//Player spritesheet
	private Animation still,walking,jump;	//Player spritesheet states
	
	//<--Physics Properties-->\\
	private final float DENSITY= 	0.2f;	//Higher density objects move lower ones
	private final float FRICTION=	.25f;	// derr
	private final float BOUNCE= 	.75f;	//Also read as resitution (sets bounce rebound)
	private Vector2 velocity;
	private FixtureDef fixtureDef;					//The fixture properties (phycial properties)
	private PolygonShape shape;						//The shape of the physics body (A square because for now sprites are squares)
	private BodyDef bodyDef;						//The properties of the body (a thing to hold fixtures) (for movement)
//	private Body body;								//The mathable body
//	private Fixture fixture;						//The mathable fixture of the body
	
	//<--Character Properties-->>\\
//	private boolean canJump;
	private int xpos;
	private int ypos;
	private final String name;
	



	/**
	 * Things initialized when placed in the GameScreen
	 * 
	 * <p>
	 * @param xpos The x starting position
	 * @param ypos The y starting position
	 * @return void 
	 */
	public void inititialize(int xpos, int ypos){
		//<--Graphial Properties-->\\
		textureAtlas = new TextureAtlas("img/");
		still = new Animation(animSpeed,textureAtlas.findRegions("still"));
		walking = new Animation(animSpeed,textureAtlas.findRegions("walking"));
		still.setPlayMode(Animation.LOOP);
		walking.setPlayMode(Animation.LOOP);
		jump.setPlayMode(Animation.LOOP);
		
		
		//<-- Body Def-->\\
		bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(xpos, ypos);
		bodyDef.allowSleep= 	true;
		
		//<-- Fixture Def-->\\
		fixtureDef = new FixtureDef();
		fixtureDef.shape= 		shape;
		fixtureDef.density= 	DENSITY;
		fixtureDef.friction=	FRICTION;
		fixtureDef.restitution=	BOUNCE;
		
		//<--Character Properties-->\\
		this.xpos = xpos;
		this.ypos = ypos;

		
		//<--Dispose Temporary Things-->\\
		
		
	}
	
	/**
	 * Updates the state of the player
	 * 
	 * @return returns 1 if succesfully updated,0 if dun-goofed
	 */
	public int update(float delta){
		
		animTime += delta;	//Update Animation time from delta
		//<-- Handle When animations happen-->\\
		if(velocity.x < 0 && velocity.y == 0){
//			this.setRegion(walking.getKeyFrame()); Need to make left	//Moving left
		}else if(velocity.x > 0 && velocity.y  == 0){
			setRegion(walking.getKeyFrame(animTime));					//Moving right
		}else if(velocity.y != 0){
			setRegion(jump.getKeyFrame(animTime));						//Moving up or down
		}else{
			setRegion(still.getKeyFrame(animTime));						//Not moving
		}
		return 0;
	}
	public void setPosition(int xpos, int ypos){
		this.xpos = xpos;
		this.ypos = ypos;
	}
	public int getXPos(){
		return xpos;
	}
	public int getYpos(){
		return ypos;
	}
	public int getNumber(){
		return number;
	}
	public Texture getIcon(){
		return icon;
	}
	public BodyDef getBodyDef(){
		return bodyDef;
	}
	public String getName(){
		return name;
	}
	public FixtureDef getFixtureDef(){
		return fixtureDef;
	}
}
//splashTexture = new Texture("img/splash.png");