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

    public TitleScreen(final ArrowStormGame game) {
        mGame = game;
        mCamera = new OrthographicCamera();
        mIntroImage = new Texture(Gdx.files.internal("")); //TODO Add intro image
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //TODO Check touching
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
