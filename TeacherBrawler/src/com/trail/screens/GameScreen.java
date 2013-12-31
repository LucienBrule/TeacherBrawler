package com.trail.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class GameScreen implements Screen {
	
	private World world;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camera;
	
	private final float TIMESTEP = 1/60f;
	private final int VELOCITYITERATIONS = 8;
	private final int POSITIONITERATIONS = 3;

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0,0,0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		debugRenderer.render(world, camera.combined);
		world.step(TIMESTEP, VELOCITYITERATIONS, POSITIONITERATIONS);
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width / 25;
		camera.viewportHeight = height/25;
		camera.update();
		}

	@Override
	public void show() {
		world = new World(new Vector2(0,-9.81f),true);
		debugRenderer = new Box2DDebugRenderer();
		
		camera = new OrthographicCamera();
		//Example: Body (a thing to hold fixtures) (for movement
		BodyDef ballDef = new BodyDef();
		ballDef.type = BodyType.DynamicBody;
		ballDef.position.set(0, 4);
		
		//ball shape
		CircleShape shape = new CircleShape();
		shape.setRadius(1f);
		
		//Fixture (the shape and physical properties)
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 2.5f;
		fixtureDef.friction = .25f;//friction from 0 - 1
		fixtureDef.restitution = .75f; //loss in force from bounces (0 = no bounce)(1 = full gain)
		
		//Assemble the thing in the world
		world.createBody(ballDef).createFixture(fixtureDef);
		shape.dispose();
		
		//Ground
		BodyDef groundDef = new BodyDef();
		groundDef.type = BodyType.StaticBody;
		groundDef.position.set(0,0);
		
		//ground Shape
		ChainShape groundShape = new ChainShape(); //A line with many verticies
		groundShape.createChain(new Vector2[]{new Vector2(-500,0), new Vector2(500,0)});
		
		//fixture def
		fixtureDef.shape = groundShape;
		fixtureDef.density = .5f;
		fixtureDef.friction = .25f;//friction from 0 - 1
		fixtureDef.restitution = .75f;		
		
		world.createBody(groundDef).createFixture(fixtureDef);
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		debugRenderer.dispose();
		world.dispose();
	}

}
