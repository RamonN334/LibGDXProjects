package com.mygdx.game;

import com.badlogic.gdx.Game;

public class MyGdxGame extends Game {
	private MenuScreen menuScreen;
	private GameScreen gameScreen;
	
	private static MyGdxGame instance = new MyGdxGame();
	
	private MyGdxGame() {}
	
	public static MyGdxGame getInstance() {
		return instance;
	}
	
	public void ShowGameMenu() {
		setScreen(menuScreen);
	}
	
	public void ShowGame() {
		setScreen(gameScreen);
	}
	
	@Override
	public void create() {
		menuScreen = new MenuScreen();
		gameScreen = new GameScreen("data/network/LevelOne/sheetLevel1.png");

		ShowGameMenu();
	}
	
	@Override
	public void dispose() {
		
	}
}