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
public class SelectStageScreen implements Screen {
    private static final float BTN_STAGE_POS_X = 72;
    private static final float BTN_STAGE_POS_Y = 72;
    private static final float BTN_STAGE_WIDTH = 342;
    private static final float BTN_STAGE_HEIGHT = 162;

    private static final float BTN_BATTLE_POS_X = 342;
    private static final float BTN_BATTLE_POS_Y = 630;
    private static final float BTN_BATTLE_WIDTH = 100;
    private static final float BTN_BATTLE_HEIGHT = 100;

    private final ArrowStormGame game;
    private Sprite battleImageSprite;
    private Sprite stageImageSprite;

    private float elapsedTime;

    public SelectStageScreen(final ArrowStormGame game) {
        this.game = game;

        battleImageSprite = AssetsLoader.battleImageSprite;
        stageImageSprite = AssetsLoader.stageImageSprite;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.spriteBatch.begin();
        game.spriteBatch.draw(battleImageSprite, BTN_BATTLE_POS_X, BTN_BATTLE_POS_Y, BTN_BATTLE_WIDTH, BTN_BATTLE_HEIGHT);
        game.spriteBatch.draw(stageImageSprite, BTN_STAGE_POS_X, BTN_STAGE_POS_Y, BTN_STAGE_WIDTH, BTN_STAGE_HEIGHT);
        game.spriteBatch.end();
        elapsedTime += delta;

        //check touchscreen
        if (Gdx.input.isTouched() && elapsedTime > 2.0) {
            //change display to GameScreen Display
            game.setScreen(new PlayStateScreen(game));
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
