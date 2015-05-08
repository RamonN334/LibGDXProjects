package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

// экран, если пройден уровень
public class VictoryScreen implements Screen {
	private ButtonActor button;
	private int nextLevel;
	private Stage stage;
	
	class GoToGameListener extends InputListener {
		@Override
		public boolean touchDown(InputEvent event, float x, float y,  int pointer, int button) {
			System.out.println("Down Pressed");
			MyGdxGame.getInstance().nextLevel(nextLevel);
			return true;
		}
	}
	
	public VictoryScreen(int level) {
		nextLevel = level;
		button = new ButtonActor("data/network/Buttons/buttonNext.png");
		button.addListener(new GoToGameListener());
		//button.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		
		stage = new Stage();
		stage.addActor(button);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		
		stage.act(delta);
		stage.draw();
		
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
