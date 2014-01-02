package com.trail.screens;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.trail.teacherbrawler.TeacherBrawler;

public class MainMenu implements Screen {
	
	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private Table table;
	private TextButton buttonPlay,buttonSettings,buttonHelp,buttonExit ;
	private Label heading;
	private final int BUTTONPADDING = 30;

	
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
		skin = new Skin(Gdx.files.internal("ui/menuSkin.json"),atlas);
		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		//Heading
		heading = new Label(TeacherBrawler.TITLE,skin);
		heading.setFontScale(4);
//		heading.
		
		//Play Button(leads to the setup screen
		buttonPlay = new TextButton("PLAY",skin);
		buttonPlay.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y){
				((Game) Gdx.app.getApplicationListener()).setScreen(new Selection());
			}
		});
		//Settings Button
		buttonSettings = new TextButton("Settings",skin);
		buttonSettings.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y){
				((Game) Gdx.app.getApplicationListener()).setScreen(new Settings());
			}
		});
		
		//HelpButton
		buttonHelp = new TextButton("Help",skin);
		buttonHelp.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y){
				((Game) Gdx.app.getApplicationListener()).setScreen(new Help());
			}
		});
		
		//Exit Button
		buttonExit = new TextButton("EXIT",skin);
		buttonExit.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y){
				Gdx.app.exit();
			}
		});
		
		Table sub1 = new Table();
		table.left();
		table.add().width((table.getWidth() - heading.getMinWidth()) /2);
		table.add(heading);
		table.row().padTop(100);
		table.add(sub1).left();;
		table.top();
		sub1.add(buttonPlay).padTop(BUTTONPADDING).padBottom(BUTTONPADDING).left();
		sub1.row();
		sub1.add(buttonSettings).padTop(BUTTONPADDING).padBottom(BUTTONPADDING).left();
		sub1.row();
		sub1.add(buttonHelp).padTop(BUTTONPADDING).padBottom(BUTTONPADDING).left();
		sub1.row();
		sub1.add(buttonExit).padTop(BUTTONPADDING).padBottom(BUTTONPADDING).left();
		
		buttonPlay.debug();
		sub1.debug();
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
