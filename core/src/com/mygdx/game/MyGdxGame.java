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
		Gdx.input.setInputProcessor(stage);
		
		ComputerActor item = new ComputerActor();
		stage.addActor(item);
		
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