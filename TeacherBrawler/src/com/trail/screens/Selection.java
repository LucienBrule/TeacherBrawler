package com.trail.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Selection implements Screen {
	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private Table table;
	private TextButton buttonBack,buttonPlay;

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		
		Table.drawDebug(stage);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width,height,true);
		table.invalidateHierarchy();
		table.setSize(width, height);
	}

	@Override
	public void show() {

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		atlas = new TextureAtlas("ui/button.pack");
		skin = new Skin(Gdx.files.internal("ui/selectionSkin.json"),atlas);
		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		
		//Play Button(leads to the setup screen
		buttonPlay = new TextButton("PLAY",skin);
		buttonPlay.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y){
				((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen());
			}
		});
		buttonPlay.pad(20);
		
		//Back Button
				buttonBack = new TextButton("BACK",skin);
				buttonBack.addListener(new ClickListener() {
					public void clicked(InputEvent event, float x, float y){
						((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu());
					}
				});
				//TODO: ADD player selection list
				
				Table t2 = new Table(skin);
				Table t3 = new Table(skin);
				buttonPlay.setHeight(50);
				buttonBack.setHeight(50);
				buttonPlay.setWidth(80);
				buttonBack.setWidth(80);
				
				table.add().width(table.getWidth()/3);
				table.add("CHOOSE TEACHER").center();
				table.row().padTop(30);
				table.add("List1\nderp\nderp\nderp\nderp\nderp\nderp").width(table.getWidth()/3);
				table.add(t2).width(table.getWidth()/3);
				t2.add(buttonPlay);
				table.add("List2\nderp").width(table.getWidth()/3);
				table.row();
				table.add().width(table.getWidth()/3);
				table.add(t3);
				t3.add(buttonBack).center();
				table.debug();
				stage.addActor(table);

	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		stage.dispose();
		atlas.dispose();
		skin.dispose();
	}

}
