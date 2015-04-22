package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class ComputerActor extends Actor {
	private boolean connect = false;
	//private TextureRegion textureReg;
	private Texture texture;
	private Sprite sprite;
	private boolean top = false;
	private boolean left = false;
	private boolean bottom = false;
	private boolean right = false;
	
	private void SwapPointsCollision() {
		boolean swap = right;
		right = bottom;
		bottom = left;
		left = top;
		top = swap;
	} 
	
	public ComputerActor(float x, float y, String name) {
	//	if (!connect) {
	//		texture = new Texture(Gdx.files.internal("data/network/computer.png"));
	//		sprite = new Sprite(texture);
	//	}
	//	else {
	//		texture = new Texture(Gdx.files.internal("data/network/computerActive.png"));
	//		sprite = new Sprite(texture);
	//	}
		
		//texture = new Texture(Gdx.files.internal(namePath));
		String path = "data/network/" + name + ".png";
		Texture texture = new Texture(Gdx.files.internal(path));
		sprite = new Sprite(texture);
		
		sprite.setX(x);
		sprite.setY(y);
		this.setX(x);
		this.setY(y);
		setBounds(this.getX(), this.getY(), sprite.getWidth(), sprite.getHeight());
		sprite.setOriginCenter();
		//switch (name) {
		addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				sprite.rotate(90);
				SwapPointsCollision();
				if (sprite.getRotation() == 360)
					sprite.setRotation(0);
				System.out.println(sprite.getRotation());
				return true;
			}
		}); 
		//}
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