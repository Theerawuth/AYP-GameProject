package com.ayp.arrowstormgame.screen;

import com.ayp.arrowstormgame.ArrowStormGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Theerawuth on 9/23/2016.
 */
public class TitleScreen implements Screen {
    final ArrowStormGame mGame;
    private OrthographicCamera mCamera;
    Texture mIntroImage;

    private static float BTN_START_WIDTH = 480;
    private static float BTN_START_HEIGHT = 800;


    public TitleScreen(final ArrowStormGame game) {
        mGame = game;
        mCamera = new OrthographicCamera();
        mCamera.setToOrtho(false, 480, 800);

        mIntroImage = new Texture(Gdx.files.internal("startgame.png")); //TODO Add intro image
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //TODO Check touching
        mCamera.update();

        mGame.batch.setProjectionMatrix(mCamera.combined);

        mGame.batch.begin();
        mGame.batch.draw(mIntroImage, 0, 0, BTN_START_WIDTH, BTN_START_HEIGHT);
        mGame.batch.end();

        //check touchscreen
        if (Gdx.input.isTouched()) {
            //change display to GameScreen Display
            mGame.setScreen(new MainMenuScreen(mGame));
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
