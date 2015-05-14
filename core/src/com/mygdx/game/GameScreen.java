package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class GameScreen implements Screen {
	boolean result;
	int numLevel;
	private Stage stage;
	private Group group;
	private final int ROW = 4; // количество строк
	private final int COLUMN = 4; // количество столбцов
	private MatrixActor matAct;
	
	
	public GameScreen(String namePath, int level) {
		stage = new Stage();
		group = new Group();
		matAct = new MatrixActor();
		numLevel = level;
		
		group.setSize(COLUMN * 64, ROW * 64);
		
		float x = 0;
		float y = 0;
		
		Texture texture = new Texture(namePath);
		
		// заполняем матрицу элементов из txt документа
			for (int i = 0; i < ROW; i++) {
				for (int j = 0; j < COLUMN; j++) {						
					matAct.addActor(i, j, new CellActor(i, j, x, y, new TextureRegion(texture, (int)x, (int)y, 64, 64)));
					y += 64;
					if (y == COLUMN * 64)
						y = 0;
				}
				x += 64;
			}
			
			matAct.addActors();
		
		//matAct.setPosition((Gdx.graphics.getWidth() / 2) - (group.getWidth() / 2), (Gdx.graphics.getHeight() / 2) - (group.getHeight() / 2));
		//matAct.setOrigin(group.getWidth() / 2, group.getHeight() / 2);
		
		stage.addActor(matAct);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		
		// устанавливаем Action для плавного перехода на другой экран
	//	stage.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(0.5f),Actions.delay(2),Actions.run(new Runnable() {
    //       @Override
    //        public void run() {
    //            MyGdxGame.getInstance().ShowVictoryScreen(numLevel + 1);
    //        }
    //    })));
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
