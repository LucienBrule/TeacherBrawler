package com.trail.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.trail.teachers.Teacher;

public class GameScreen implements Screen {

	public static World world;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camera;

	private final float TIMESTEP = (1f / 60f);
	private final float GRAVITY = -9.8f;
	private final int VELOCITYITERATIONS = 2;
	private final int POSITIONITERATIONS = 2;

	private Teacher player1;
	private Teacher player2;
	private SpriteBatch batch;
	private Array<Body> tempBodies = new Array<Body>();

	/**
	 * Gets called repeatedly
	 * 
	 * @param delta
	 *            The time in seconds since the last frame
	 */
	@Override
	public void render(float delta) {
		// <--Render Graphics-->>\\
		Gdx.gl.glClearColor(0, 0, 0, 1); // Set fill color white
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen, set fill
													// color
		camera.position.set(player1.getX() + player1.getWidth() / 2,
				player1.getY() + player1.getHeight() / 2, 0);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		debugRenderer.render(world, camera.combined);
		world.step(TIMESTEP, VELOCITYITERATIONS, POSITIONITERATIONS);
		player1.update(delta);
		player2.update(delta);
		batch.begin();
		world.getBodies(tempBodies);
		for (Body b : tempBodies) {
			if (b.getUserData() != null && b.getUserData() instanceof Teacher) {
				Teacher t = (Teacher) b.getUserData();
				t.setPosition(b.getPosition().x - t.getWidth() / 2,
						b.getPosition().y - t.getHeight() / 2);
				t.setRotation(b.getAngle() * MathUtils.radiansToDegrees);
				t.draw(batch);
			}
		}
		batch.end();

		// <--Do physics-->\\
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width / 25;
		camera.viewportHeight = height / 25;
		camera.update();
	}

	@Override
	public void show() {
		Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
		world = new World(new Vector2(0, GRAVITY), true);
		debugRenderer = new Box2DDebugRenderer();
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Gdx.graphics.getWidth() / 25,
				Gdx.graphics.getHeight() / 25);
		player1 = new Teacher("Player1", 0);
		player2 = new Teacher("Player2", 1);
		player1.flip(false, false);
		player1.getX();
		player1.getWidth();

		// <-- Assign keypress delagation-->\\

		Gdx.input.setInputProcessor(new InputMultiplexer(new InputAdapter() {
			@Override
			public boolean keyDown(int keycode) {
				switch (keycode) {
				case Keys.ESCAPE:
					((Game) Gdx.app.getApplicationListener())
							.setScreen(new Selection());
					break;
				}
				return false;
			}

			@Override
			public boolean scrolled(int amount) {
				camera.zoom += amount / 25f;
				return true;
			}
		}, player1));

		player1.inititialize(-1, 0, world);
		player2.inititialize(1, 0, world);
		player1.setBody(world.createBody(player1.getBodyDef())).createFixture(
				player1.getFixtureDef());
		player2.setBody(world.createBody(player2.getBodyDef())).createFixture(
				player2.getFixtureDef());

		player1.setSize(player1.getHEIGHT(), player1.getWIDTH());
		player2.setSize(player2.getHEIGHT(), player2.getWIDTH());
		player1.setOrigin(player1.getWIDTH() / 2, player1.getHEIGHT() / 2);
		player2.setOrigin(player2.getWIDTH() / 2, player2.getHEIGHT() / 2);

		player1.getBody().setUserData(player1);
		player2.getBody().setUserData(player2);

		// <--Debug Ground-->\\

		player1.setBody(world.createBody(player1.getBodyDef()));
		player2.setBody(world.createBody(player2.getBodyDef()));
		// GROUND
		BodyDef bodyDef = new BodyDef();
		FixtureDef fixtureDef = new FixtureDef();

		// GROUND
		// body definition
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(0, 0);
		// ground shape
		bodyDef = new BodyDef();
		fixtureDef = new FixtureDef();
		ChainShape groundShape = new ChainShape();
		Vector3 bottomLeft = new Vector3(0, Gdx.graphics.getHeight(), 0);
		Vector3 bottomRight = new Vector3(Gdx.graphics.getWidth(),
				bottomLeft.y, 0);
		camera.unproject(bottomLeft);
		camera.unproject(bottomRight);

		groundShape.createChain(new float[] { bottomLeft.x, bottomLeft.y,
				bottomRight.x, bottomRight.y });

		// fixture definition
		fixtureDef.shape = groundShape;
		fixtureDef.friction = .5f;
		fixtureDef.restitution = 0;

		Body ground = world.createBody(bodyDef);
		ground.createFixture(fixtureDef);

		// player1.draw();
		// player2.draw();
		// //Example: Body (a thing to hold fixtures) (for movement
		// BodyDef ballDef = new BodyDef();
		// ballDef.type = BodyType.DynamicBody;
		// ballDef.position.set(0, 4);
		//
		// //ball shape
		// CircleShape shape = new CircleShape();
		// shape.setRadius(1f);
		//
		// //Fixture (the shape and physical properties)
		// FixtureDef fixtureDef = new FixtureDef();
		// fixtureDef.shape = shape;
		// fixtureDef.density = 2.5f;
		// fixtureDef.friction = .25f;//friction from 0 - 1
		// fixtureDef.restitution = .75f; //loss in force from bounces (0 = no
		// bounce)(1 = full gain)
		//
		// //Assemble the thing in the world
		// world.createBody(ballDef).createFixture(fixtureDef);
		// shape.dispose();
		//
		// //Ground
		// BodyDef groundDef = new BodyDef();
		// groundDef.type = BodyType.StaticBody;
		// groundDef.position.set(0,0);
		//
		// //ground Shape
		// ChainShape groundShape = new ChainShape(); //A line with many
		// verticies
		// groundShape.createChain(new Vector2[]{new Vector2(-500,0), new
		// Vector2(500,0)});
		//
		// //fixture def
		// fixtureDef.shape = groundShape;
		// fixtureDef.density = .5f;
		// fixtureDef.friction = .25f;//friction from 0 - 1
		// fixtureDef.restitution = .75f;
		//
		// world.createBody(groundDef).createFixture(fixtureDef);
	}

	/**
	 * getsTeacher1 FOR TESTING PURPOSES CHANGE ME LATER
	 * 
	 * @param t
	 * @return
	 */
	private Teacher getTeacher1() {

		return new Teacher("SAMPLE1", 0);
	}

	private Teacher getTeacher2() {
		return new Teacher("SAMPLE2", 1);
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {

	}

	/**
	 * Called when the Screen initialized, and when the screen resumes from
	 * pause
	 */
	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		debugRenderer.dispose();
		world.dispose();
		player1.getTexture().dispose();
		player2.getTexture().dispose();

	}

}
