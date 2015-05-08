package com.mygdx.game;

import com.badlogic.gdx.Game;

// Основной класс игры
public class MyGdxGame extends Game {
	private final int endLevel = 2;
	private MenuScreen menuScreen;
	private GameScreen [] levels;
	
	private static MyGdxGame instance = new MyGdxGame();
	
	private MyGdxGame() {}
	
	public static MyGdxGame getInstance() {
		return instance;
	}
	
	public void ShowGameMenu() {
		setScreen(menuScreen);
	}
	
	public void ShowVictoryScreen(int nextLevel) {
		setScreen(new VictoryScreen(nextLevel));
	}
	
	public void ShowGame() {
		setScreen(levels[0]);
	}
	
	public void nextLevel(int numLevel) {
		if (numLevel != endLevel)
			setScreen(levels[numLevel]);
		else
			ShowGameMenu();	
	}
	
	@Override
	public void create() {
		menuScreen = new MenuScreen();
		levels = new GameScreen[3];
		for (int i = 0; i < 2; i++)
			levels[i] = new GameScreen("data/network/Levels/level" + (i + 1) +".png", i);
		

		ShowGameMenu();
	}
	
	@Override
	public void dispose() {
		menuScreen.dispose();
		for (GameScreen i : levels)
			i.dispose();

	}
}