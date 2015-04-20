package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class ComputerActor extends Actor {
	private boolean connect = false;
	private Texture texture;
	private Sprite sprite;
	
	public ComputerActor(float x, float y) {
		if (!connect) {
			texture = new Texture(Gdx.files.internal("data/network/computer.png"));
			sprite = new Sprite(texture);
		}
		else {
			texture = new Texture(Gdx.files.internal("data/network/computerActive.png"));
			sprite = new Sprite(texture);
		}
		
		sprite.setX(x);
		sprite.setY(y);
		this.setX(x);
		this.setY(y);
		setBounds(this.getX(), this.getY(), sprite.getWidth(), sprite.getHeight());
		sprite.setOriginCenter();
		addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				sprite.rotate(90);
				if (sprite.getRotation() == 360)
					sprite.setRotation(0);
				System.out.println(sprite.getRotation());
				return true;
			}
		}); 
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(sprite, 
				   sprite.getX(),
				   sprite.getY(),
				   sprite.getOriginX(),
				   sprite.getOriginY(),
				   sprite.getWidth(),
				   sprite.getHeight(),
				   sprite.getScaleX(),
				   sprite.getScaleY(),
				   sprite.getRotation());
	}
}