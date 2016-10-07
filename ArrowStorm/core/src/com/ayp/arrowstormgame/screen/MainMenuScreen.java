package com.ayp.arrowstormgame.screen;

import com.ayp.arrowstormgame.ArrowStormGame;
import com.ayp.arrowstormgame.helper.AssetsLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Theerawuth on 9/23/2016.
 */
public class MainMenuScreen implements Screen  {

    private static float MAIN_MENU_BG_WIDTH = 480;
    private static float MAIN_MENU_BG_HEIGHT = 800;

    private float elapsedTime;

    private final ArrowStormGame game;
    private Sprite mainMenuImageSprite;

    //TODO Create menu button

    public MainMenuScreen(final ArrowStormGame game) {
        this.game = game;
        mainMenuImageSprite = AssetsLoader.mainMenuImageSprite;

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        elapsedTime += delta;

        game.spriteBatch.setProjectionMatrix(game.camera.combined);

        game.spriteBatch.begin();
        game.spriteBatch.draw(mainMenuImageSprite, 0, 0, MAIN_MENU_BG_WIDTH, MAIN_MENU_BG_HEIGHT);
        game.spriteBatch.end();

        //check touchscreen
        if (Gdx.input.isTouched() && elapsedTime > 1.0) {
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
