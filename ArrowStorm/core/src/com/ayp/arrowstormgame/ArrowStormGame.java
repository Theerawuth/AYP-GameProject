package com.ayp.arrowstormgame;

import com.ayp.arrowstormgame.screen.MainMenuScreen;
import com.ayp.arrowstormgame.screen.PlayStateScreen;
import com.ayp.arrowstormgame.screen.TitleScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ArrowStormGame extends Game {
	public static final float GAME_WIDTH = 480;
	public static final float GAME_HEIGHT = 800;

	@Override
	public void create () {
		setScreen(new PlayStateScreen(this));

	}

	@Override
	public void render () {
		super.render();

	}
	
	@Override
	public void dispose () {

	}
}
