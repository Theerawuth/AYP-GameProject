package com.ayp.arrowstormgame.screen;

import com.ayp.arrowstormgame.ArrowStormGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Theerawuth on 9/23/2016.
 */
public class MainMenuScreen implements Screen  {
    private static float BTN_START_POS_X = 71;
    private static float BTN_START_POS_Y = 504;
    private static float BTN_START_WIDTH = 343;
    private static float BTN_START_HEIGHT = 144;

    private OrthographicCamera mCamera;
    final ArrowStormGame mGame;
    Texture startImage;
    Sprite startButton;

    //TODO Create menu button

    public MainMenuScreen(final ArrowStormGame game) {
        mGame = game;

        mCamera = new OrthographicCamera();
        mCamera.setToOrtho(true, 480, 800);
        startImage = new Texture(Gdx.files.internal("start_button.png"));
        startButton = new Sprite(startImage);
        startButton.flip(false, true);
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

        mCamera.update();

        mGame.batch.setProjectionMatrix(mCamera.combined);

        mGame.batch.begin();
        mGame.batch.draw(startButton, BTN_START_POS_X, BTN_START_POS_Y, BTN_START_WIDTH, BTN_START_HEIGHT);
        mGame.batch.end();

        //check touchscreen
        if (Gdx.input.isTouched()) {
            //change display to GameScreen Display
            mGame.setScreen(new SelectStageScreen(mGame));
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
