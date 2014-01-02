package com.trail.ClickListener;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class BetterClickListener extends ClickListener{
	int number;
	public BetterClickListener(int number){
		this.number = number;
	}
	public int getNumber(){
		return number;
	}
}
