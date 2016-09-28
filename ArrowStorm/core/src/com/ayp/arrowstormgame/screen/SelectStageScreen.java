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
public class SelectStageScreen implements Screen {
    private static final float BTN_STAGE_POS_X = 72;
    private static final float BTN_STAGE_POS_Y = 72;
    private static final float BTN_STAGE_WIDTH = 342;
    private static final float BTN_STAGE_HEIGHT = 162;

    private static final float BTN_BATTLE_POS_X = 342;
    private static final float BTN_BATTLE_POS_Y = 630;
    private static final float BTN_BATTLE_WIDTH = 100;
    private static final float BTN_BATTLE_HEIGHT = 100;

    private OrthographicCamera mCamera;
    final ArrowStormGame mGame;
    Texture battleImage;
    Texture stageImage;
    Sprite battleButton;
    Sprite stageButton;


    public SelectStageScreen(final ArrowStormGame game) {
        mGame = game;

        mCamera = new OrthographicCamera();
        mCamera.setToOrtho(true, 480, 800);

        battleImage = new Texture(Gdx.files.internal("battle_button.png"));
        battleButton = new Sprite(battleImage);
        battleButton.flip(false, true);

        stageImage = new Texture(Gdx.files.internal("stage_button.png"));
        stageButton = new Sprite(stageImage);
        stageButton.flip(false, true);


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mCamera.update();

        mGame.batch.setProjectionMatrix(mCamera.combined);

        mGame.batch.begin();
        mGame.batch.draw(battleButton, BTN_BATTLE_POS_X, BTN_BATTLE_POS_Y, BTN_BATTLE_WIDTH, BTN_BATTLE_HEIGHT);
        mGame.batch.draw(stageButton, BTN_STAGE_POS_X, BTN_STAGE_POS_Y, BTN_STAGE_WIDTH, BTN_STAGE_HEIGHT);
        mGame.batch.end();

        //check touchscreen
        if (Gdx.input.isTouched()) {
            //change display to GameScreen Display
            mGame.setScreen(new PlayStateScreen(mGame));
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
