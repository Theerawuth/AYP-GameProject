package com.ayp.arrowstormgame.screen;

import com.ayp.arrowstormgame.ArrowStormGame;
import com.ayp.arrowstormgame.helper.AssetsLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Theerawuth on 9/23/2016.
 */
public class TitleScreen implements Screen {
    final ArrowStormGame game;
    private Texture introImage;

    private static float INTRO_BG_WIDTH = 480;
    private static float INTRO_BG_HEIGHT = 800;

    public TitleScreen(final ArrowStormGame game) {
        this.game = game;
        introImage = AssetsLoader.introImage;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        game.spriteBatch.begin();
        game.spriteBatch.draw(introImage, 0, 0, INTRO_BG_WIDTH, INTRO_BG_HEIGHT);
        game.spriteBatch.end();

        //check touchscreen
        if (Gdx.input.isTouched()) {
            //change display to GameScreen Display
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
