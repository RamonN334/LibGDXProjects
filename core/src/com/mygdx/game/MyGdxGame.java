package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MyGdxGame extends Game {
	private Stage stage;
	
	@Override
	public void create() {
		stage = new Stage();
		
		float x = 0;
		float y = 0;
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 6; j++) {
		//		ComputerActor item = new ComputerActor(x, y);
				stage.addActor(new ComputerActor(x, y));
				x += 64;
				if (x == (6 * 64))
					x = 0;
			}
			y += 64;
		}
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void render() {
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}
	
	@Override
	public void dispose() {
		
	}
}