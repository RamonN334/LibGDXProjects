package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ButtonActor extends Actor {
	private Sprite sprite;
	
	public ButtonActor(String pathName) {
		sprite = new Sprite(new Texture(Gdx.files.internal(pathName)));
		this.setPosition((Gdx.graphics.getWidth() / 2) - (sprite.getWidth() / 2), Gdx.graphics.getHeight() / 2);
		this.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
		//setPosition((Gdx.graphics.getWidth() / 2) - (this.getWidth() / 2), Gdx.graphics.getHeight() / 2);
		sprite.setX(this.getX());
		sprite.setY(this.getY());
		setBounds(this.getX(), this.getY(), sprite.getWidth(), sprite.getHeight());
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
