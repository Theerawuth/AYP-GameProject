package com.ayp.arrowstormgame.screen;

import com.ayp.arrowstormgame.ArrowStormGame;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by Theerawuth on 9/23/2016.
 */
public class SelectStageScreen implements Screen {
    private OrthographicCamera mCamera;
    final ArrowStormGame mGame;

    public SelectStageScreen(final ArrowStormGame game) {
        mGame = game;
        mCamera = new OrthographicCamera();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
