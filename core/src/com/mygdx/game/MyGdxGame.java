package com.mygdx.game;

import com.badlogic.gdx.Game;

public class MyGdxGame extends Game {
	private MenuScreen menuScreen;
	private GameScreen gameLevelOne;
	private GameScreen gameLevelTwo;
	private GameScreen gameLevelThree;
	
	private static MyGdxGame instance = new MyGdxGame();
	
	private MyGdxGame() {}
	
	public static MyGdxGame getInstance() {
		return instance;
	}
	
	public void ShowGameMenu() {
		setScreen(menuScreen);
	}
	
	public void ShowGame() {
		setScreen(gameLevelOne);
	}
	
	@Override
	public void create() {
		menuScreen = new MenuScreen();
		gameLevelOne = new GameScreen("data/network/Levels/LevelOne.txt");
		gameLevelTwo = new GameScreen("data/network/Levels/LevelTwo.txt");
		

		ShowGameMenu();
	}
	
	@Override
	public void dispose() {
		menuScreen.dispose();
		gameLevelOne.dispose();
	}
}