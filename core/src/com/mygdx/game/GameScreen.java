package com.mygdx.game;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameScreen implements Screen {
	private Stage stage;
	private Group group;
	private final int ROW = 7;
	private final int COLUMN = 6;
	private ComputerActor [][] arrayActors;
	
	public GameScreen(String namePath) {
		stage = new Stage();
		group = new Group();
		arrayActors = new ComputerActor[ROW][COLUMN];
		
		group.setSize(COLUMN * 64, ROW * 64);
		
		float x = 0;
		float y = 0;
		try {
			BufferedReader reader = new BufferedReader(Gdx.files.internal("data/network/LevelOne/LevelOne.txt").reader());
			String line;
			String[] retval;
			while (true) {
				for (int i = 0; i < ROW; i++) {
					line = reader.readLine();
					if (line != null)
						retval = line.split(" ");
					else
						break;
					for (int j = 0; j < COLUMN; j++) {						
						group.addActor(new ComputerActor(x, y, retval[j]));
						x += 64;
						//x1 += 64;
						if (x == COLUMN * 64)
							x = 0;
					}
					y += 64;
				}
				break;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		group.setPosition((Gdx.graphics.getWidth() / 2) - (group.getWidth() / 2), (Gdx.graphics.getHeight() / 2) - (group.getHeight() / 2));
		group.setOrigin(group.getWidth() / 2, group.getHeight() / 2);
		
		stage.addActor(group);
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
		stage.dispose();
	}

}
