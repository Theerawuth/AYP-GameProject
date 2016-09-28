package com.ayp.arrowstormgame.screen;

import com.ayp.arrowstormgame.ArrowStormGame;
import com.ayp.arrowstormgame.helper.AssetsLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Theerawuth on 9/23/2016.
 */
public class MainMenuScreen implements Screen  {
    private static float BTN_START_POS_X = 71;
    private static float BTN_START_POS_Y = 504;
    private static float BTN_START_WIDTH = 343;
    private static float BTN_START_HEIGHT = 144;

    private float elapsedTime;

    private final ArrowStormGame game;
    private Sprite startImageSprite;

    //TODO Create menu button

    public MainMenuScreen(final ArrowStormGame game) {
        this.game = game;
        startImageSprite = AssetsLoader.startImageSprite;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //TODO Check that what menu is selected
        //Clear Display
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.spriteBatch.begin();
        game.spriteBatch.draw(startImageSprite, BTN_START_POS_X, BTN_START_POS_Y, BTN_START_WIDTH, BTN_START_HEIGHT);
        game.spriteBatch.end();

        elapsedTime += delta;

        //check touchscreen
        if (Gdx.input.isTouched() && elapsedTime > 2.0) {
            //change display to GameScreen Display
            game.setScreen(new SelectStageScreen(game));
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
