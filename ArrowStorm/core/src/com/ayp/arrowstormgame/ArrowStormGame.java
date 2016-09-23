package com.ayp.arrowstormgame;

import com.ayp.arrowstormgame.screen.MainMenuScreen;
import com.ayp.arrowstormgame.screen.TitleScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ArrowStormGame extends Game {
	public SpriteBatch batch;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new TitleScreen(this));

	}

	@Override
	public void render () {
		super.render();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
