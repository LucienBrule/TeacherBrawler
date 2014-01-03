package com.trail.screens;
import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.trail.ClickListener.BetterClickListener;

public class Selection implements Screen {
	//<--Primary Layout-->\\
	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private Table table;
	
	//<--Teacher Selection lists-->\\
	private final int rowSize =	4;
	private final int numIcons=	11;
	private int selection1 =	-1;
	private int selection2 =	-1;
	private Table tableSel1;
	private Table tableSel2;
	private Label labelSel1;
	private Label labelSel2;

	//<-- Buttons-->\\
	private TextButton buttonBack,buttonPlay;
	private ArrayList<ImageButton> iconList1;
	private ArrayList<ImageButton> iconList2;

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);

		table.remove();
		stage.addActor(table);
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
		//<--Primary Layout-->\\
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		atlas = new TextureAtlas("ui/button.pack");
		skin = new Skin(Gdx.files.internal("ui/selectionSkin.json"),atlas);
		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		//<--Teacher Selection lists-->\\
		//Selection readout table and labels
		tableSel1 = new Table();
		tableSel2 = new Table();
		labelSel1 = new Label("Selection: ", skin);
		labelSel2 = new Label("Selection: ", skin);
		iconList1 = new ArrayList<ImageButton>();
		iconList2 = new ArrayList<ImageButton>();
		//Load lists to memory
		populateIconList1();
		populateIconList2();
		//Tables to draw icons to
		Table tableList1 = new Table(skin);
		Table tableList2 = new Table(skin);
		//populate tables
		tableList1 = populateTableList(tableList1, iconList1);
		tableList2 = populateTableList(tableList2, iconList2);
		tableList1.setHeight(200);
		tableList2.setHeight(200);
		//Selection tables
		tableSel1.add(labelSel1).expand();
		tableSel2.add(labelSel2).expand();
		
		//<-- Play Button--\\
		buttonPlay = new TextButton("PLAY",skin);
		buttonPlay.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y){
				((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen());
			}
		});
		Table tablePlay = new Table(skin);

		//<-- Back Button--\\
		buttonBack = new TextButton("BACK",skin);
		buttonBack.addListener(new ClickListener() {
		public void clicked(InputEvent event, float x, float y){
			((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu());
			}
		});
		Table tableBack = new Table(skin);
		
		//<--Do Layout-->\\
		buttonPlay.setHeight(50);
		buttonBack.setHeight(50);
		buttonPlay.setWidth(80);
		buttonBack.setWidth(80);
		table.top();
		table.add().width(table.getWidth()/3).top();
		table.add("CHOOSE TEACHER").center();
		table.row().padTop(30).height(300);
		table.add(tableList1).width(table.getWidth()/3);
		table.add(tablePlay).width(table.getWidth()/3);
		tablePlay.add(buttonPlay);
		table.add(tableList2).width(table.getWidth()/3);
		table.row();
		table.add(labelSel1).width(table.getWidth()/3);
		table.add(tableBack);
		tableBack.add(buttonBack).center();
		table.add(labelSel2);
		table.debug();
		stage.addActor(table);
				
	}
	private Table populateTableList(Table table,ArrayList<ImageButton> b) {
		Gdx.app.log("populateTableList","");
		table.top().padTop(10f);
		//<-- Add all of the Icons-->\\
		for(int i = 0,j = rowSize - 1; i <b.size(); i ++){
			table.add(b.get(i)).padRight(10f);
			if(i == (j)){
				table.row().padTop(10f);
				j+= rowSize;
			}
		}

		return table;
	}
	public void updateSelection1(int s){
		selection1 = s;
		
	}
	public void updateSelection2(int s){
		selection2 = s;
	}

	private static NinePatch processNinePatchFile(String fname) { //http://stackoverflow.com/questions/15355723/loading-nine-patch-image-as-a-libgdx-scene2d-button-background-looks-awful
		Gdx.app.log("processNinePatchFile", "");
		final Texture t = new Texture(Gdx.files.internal(fname));
	    final int width = t.getWidth() - 2;
	    final int height = t.getHeight() - 2;
	    return new NinePatch(new TextureRegion(t, 1, 1, width, height), 3, 3, 3, 3);
	}
	private void populateIconList1(){
		Gdx.app.log("populateIconList1","");
		for(int i = 0; i < numIcons; i++){
			ImageButton b = new ImageButton(new NinePatchDrawable(processNinePatchFile("playerIcons/playerIcon_" + i + ".png")));
			b.addListener(new BetterClickListener(i) {
				public void clicked(InputEvent event, float x, float y){
						updateSelection1(getNumber());
						labelSel1.setText("Selection: " + getNumber());
						Gdx.app.log("Player 1 Selection: ", "" + selection1);
				}
			});
			iconList1.add(b);
		}
		Gdx.app.log("Length of icon list 1: ","" + iconList1.size());

	}
	private void populateIconList2(){
		Gdx.app.log("populateIconList2","");
		for(int i = 0; i < numIcons ; i++){
			ImageButton b = new ImageButton(new NinePatchDrawable(processNinePatchFile("playerIcons/playerIcon_" + i + ".png")));
			b.addListener(new BetterClickListener(i) {
				public void clicked(InputEvent event, float x, float y){
					updateSelection2(getNumber());
					labelSel2.setText("Selection: " + getNumber());
					Gdx.app.log("Player 2 Selection: ", "" + selection2);
				}
			});
			iconList2.add(b);
		}
		Gdx.app.log("Length of icon list 2: ","" + iconList1.size());

	}
	
	




//	private Table populateTableList(Table tableList1,ArrayList<ImageButton> iconList12) {
//		
//		return null;
//	}
//
//	private void populateIconList2() {
//		int numIcons = new File("img/playerIcons").listFiles().length;
//		ImageButton b1 = new ImageButton(new Texture(img/))
//		iconList1.add()
//	}
//
//	private void populateIconList1() {
//		// TODO Auto-generated method stub
//		
//	}

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
		Texture.clearAllTextures(Gdx.app);
		
		
	}

}
