package com.trail.teachers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class Teacher extends Sprite implements InputProcessor {

	public Teacher(String name, int uniqNum) {// assuming we store things
												// like(img/Teacher_<num>)
		this.name = name;
		number = uniqNum;
		icon = new Texture("playerIcons/PlayerIcon_" + number + ".png");

	}

	public Teacher(String name, int uniqNum, String iconLoc) {// use this and
																// then
																// initialize
																// sprite (saves
																// memory)
		this.name = name;
		icon = new Texture(iconLoc);
		number = uniqNum;
	}

	public Teacher(String name, int uniqNum, String iconLoc, String spriteLoc) {// full
																				// featured
																				// constructor,
																				// dont
																				// use
																				// this
		super(new Sprite(new Texture(spriteLoc)));
		this.name = name;
		number = uniqNum;
		icon = new Texture(iconLoc);
	}

	// <--Graphical Properites-->\\
	private final int number; // unique progrmaticly replicable unique id(for
								// the selection screen)
	private float animTime = .5f;
	private final float WIDTH = 1f; // default width for our teacher sprites
	private final float HEIGHT = 2f; // default height for our teacher sprites
	private final float animSpeed = 1 / 60f;
	private Texture icon; // The Icon for the selection screen and ingame HUD
	private TextureAtlas textureAtlas; // Player spritesheet
	private TextureRegion textureStill, textureRight, textureLeft, textureJump; // Player
																				// spritesheet
																				// states
	private Animation animStillRight, animStillLeft, animRight, animLeft,
			animJumpRight, animJumpLeft;

	// <--Physics Properties-->\\
	private final float DENSITY = 1f; // Higher density objects move lower ones
	private final float FRICTION = 0; // derr
	private final float BOUNCE = 0f; // Also read as resitution (sets bounce
										// rebound)
	private float speed = 200f;
	private Vector2 velocity;
	private FixtureDef fixtureDef; // The fixture properties (phycial
									// properties)
	private PolygonShape shape; // The shape of the physics body (A square
								// because for now sprites are squares)
	private BodyDef bodyDef; // The properties of the body (a thing to hold
								// fixtures) (for movement)
	private Body body; // The mathable body
	private Fixture fixture; // The mathable fixture of the body
	boolean isRight;

	// <--Character Properties-->>\\
	// private boolean canJump;
	private int xpos;
	private int ypos;
	private final String name;

	/**
	 * Things initialized when placed in the GameScreen
	 * 
	 * <p>
	 * 
	 * @param xpos
	 *            The x starting position
	 * @param ypos
	 *            The y starting position
	 * @param world
	 *            The world to do stuff with
	 * @return void
	 */
	public void inititialize(int xpos, int ypos, World world) {
		// <--Graphial Properties-->\\

		// Set all of the texture regions, and set the left ones based on the
		// flipped right ones
		textureAtlas = new TextureAtlas(
				Gdx.files.internal("playerSprites/TeacherSprite" + number
						+ ".pack"));
		animStillRight = new Animation(animTime,
				textureAtlas.findRegions("still"));
		Gdx.app.log("Anim right is:", "" + (animStillRight == null) + " "
				+ number);
		animStillLeft = new Animation(animTime, flipAnimation(animStillRight));
		animRight = new Animation(animTime, textureAtlas.findRegions("walking"));
		animLeft = new Animation(animTime, flipAnimation(animRight));
		animJumpRight = new Animation(animTime,
				textureAtlas.findRegions("jump"));
		animJumpLeft = new Animation(animTime, flipAnimation(animJumpRight));
		Gdx.app.log("Got here 1", "");
		animStillRight.getKeyFrame(0);

		velocity = new Vector2();

		// Set animations to loop
		animStillRight.setPlayMode(Animation.LOOP);
		animStillLeft.setPlayMode(Animation.LOOP);
		animRight.setPlayMode(Animation.LOOP);
		animLeft.setPlayMode(Animation.LOOP);
		animJumpRight.setPlayMode(Animation.LOOP);
		animJumpLeft.setPlayMode(Animation.LOOP);
		Gdx.app.log("Got here 1", "");

		// <-- Body Def-->\\
		bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.fixedRotation = true;
		bodyDef.position.set(xpos, ypos);
		bodyDef.allowSleep = true;
		// <--Shape Def & sizing-->\\
		shape = new PolygonShape();
		shape.setAsBox(WIDTH / 2f, HEIGHT / 2f);

		// <-- Fixture Def-->\\
		fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = DENSITY;
		fixtureDef.friction = FRICTION;
		fixtureDef.restitution = BOUNCE;

		this.setOrigin(getWidth() / 2, getHeight() / 2);
		this.setSize(WIDTH, HEIGHT);

		if (isRight) {
			this.set(new Sprite(animStillRight.getKeyFrame(0)));
		} else {
			this.set(new Sprite(animStillLeft.getKeyFrame(0)));
		}
		// //<--Character Properties-->\\
		this.xpos = xpos;
		this.ypos = ypos;
		// //<--Dispose Temporary Things-->\\
		//
		//
	}

	// private Array<TextureRegion> flipAnimation(Animation a,int i){
	// TextureRegion b = new TextureRegion();
	// if(a == null ||a.getKeyFrames()== null){
	// Gdx.app.log("flipAnimation", "Gave null animation");
	// return null;
	// }
	// if(a.getKeyFrames().length == 0){
	// Gdx.app.log("flipAnimation", "Gave 0 size animation");
	// return null;
	// }
	// Array<TextureRegion> carrier = new Array<TextureRegion>();
	//
	// for(int i = 0;i < a.getKeyFrames().length; i++){
	// b = a.getKeyFrames()[i];
	// b.flip(false, true);
	// carrier.add(b);
	// }
	// carrier.reverse();
	// return carrier;
	// }
	private Array<TextureRegion> flipAnimation(Animation a) {
		TextureRegion b = new TextureRegion();
		if (a == null || a.getKeyFrames() == null) {
			Gdx.app.log("flipAnimation", "Gave null animation");
			return null;
		}
		if (a.getKeyFrames().length == 0) {
			Gdx.app.log("flipAnimation", "Gave 0 size animation");
			return null;
		}
		Array<TextureRegion> carrier = new Array<TextureRegion>();

		for (int i = 0; i < a.getKeyFrames().length; i++) {
			b = a.getKeyFrames()[i];
			b.flip(true, false);
			carrier.add(b);
		}
		carrier.reverse();
		return carrier;
	}

	/**
	 * Updates the state of the player 1142
	 * 
	 * @return returns 1 if succesfully updated,0 if dun-goofed
	 */
	public void update(float delta) {
		Gdx.app.log("Velocity: " + velocity, "Player " + number);
		// <-- Do Animations-->\\
		animTime += delta; // Update Animation time from delta
		// <-- Handle When animations happen-->\\
		if (velocity.x > 0 && velocity.y == 0) { // Moving right
			setRegion(animRight.getKeyFrame(animTime));
			isRight = true;
		} else if (velocity.x < 0 && velocity.y == 0) { // Moving left
			setRegion(animLeft.getKeyFrame(animTime));
		} else if (velocity.x > 0 && velocity.y != 0 && isRight) { // Moving up
																	// or down &
																	// right
			setRegion(animJumpRight.getKeyFrame(animTime));
			isRight = true;
		} else if (velocity.x < 0 && velocity.y != 0) { // Moving up or down &
														// left
			setRegion(animJumpLeft.getKeyFrame(animTime));
		} else if (velocity.x == 0 && velocity.y == 0 && isRight) { // Not
																	// moving
																	// and was
																	// faced
																	// right
			setRegion(animStillRight.getKeyFrame(animTime));
		} else { // Not moving and faced right
			setRegion(animStillLeft.getKeyFrame(animTime));
		}
		xpos = (int) speed;
		animTime = 0;
	}

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Keys.A:
			velocity.x -= speed / 10;
			break;
		case Keys.D:
			velocity.x += speed / 10;
			break;
		case Keys.W:
			velocity.y += speed / 10;
			break;
		case Keys.S:
			velocity.y -= speed / 10;
			break;
		default:
			return false;
		}
		return true;
	}

	public boolean keyUp(int keycode) {
		switch (keycode) {
		case Keys.A:
			velocity.x = 0;
			break;
		case Keys.D:
			velocity.x = 0;
			break;
		case Keys.W:
			velocity.y = 0;
			break;
		case Keys.S:
			velocity.y = 0;
			break;
		default:
			return false;
		}
		return true;
	}

	public int getXPos() {
		return xpos;
	}

	public int getYpos() {
		return ypos;
	}

	public int getNumber() {
		return number;
	}

	public Texture getIcon() {
		return icon;
	}

	public BodyDef getBodyDef() {
		return bodyDef;
	}

	public String getName() {
		return name;
	}

	public FixtureDef getFixtureDef() {
		return fixtureDef;
	}

	public float getWIDTH() {
		return WIDTH;
	}

	public float getHEIGHT() {
		return HEIGHT;
	}

	public Body getBody() {
		return body;
	}

	public Body setBody(Body body) {
		this.body = body;
		return body;
	}

	public Fixture getFixture() {
		return fixture;
	}

	public void setFixture(Fixture fixture) {
		this.fixture = fixture;
	}

	public Body doPhysics() {
		return body;

	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}