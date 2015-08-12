package com.yahoo.cornelg7.elevator;

import com.badlogic.gdx.Game;
import screens.GameScreen;
import utils.Assets;

public class Elevator extends Game {

	GameScreen gs;

	@Override
	public void create() {

		Assets.load();
		//TODO if there are many assets implement loading screen on a new thread
		gs = new GameScreen(this);
		setScreen(gs);
	}

}
