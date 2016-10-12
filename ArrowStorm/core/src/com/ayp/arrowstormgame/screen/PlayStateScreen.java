package com.ayp.arrowstormgame.screen;

import com.ayp.arrowstormgame.ArrowStormGame;
import com.ayp.arrowstormgame.game_manager.GamePlayManager;
import com.ayp.arrowstormgame.game_manager.GamePlayRenderer;
import com.ayp.arrowstormgame.model.Arrow;
import com.ayp.arrowstormgame.model.Enemy;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Theerawuth on 9/23/2016.
 */
public class PlayStateScreen implements Screen {
    public static String TAG = "PlayStateScreen";
    final private ArrowStormGame game;
    private GamePlayRenderer gamePlayRenderer;
    private GamePlayManager gamePlayManager;

    private Array<Arrow> arrows;
    private Array<Enemy> enemies;

    private float elapseTime;


    public PlayStateScreen(final ArrowStormGame game) {
        this.game = game;
        gamePlayRenderer = new GamePlayRenderer(game);
        gamePlayManager = new GamePlayManager(game);
        arrows = new Array<Arrow>();
        enemies = new Array<Enemy>();
        elapseTime = 0;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        gamePlayRenderer.drawBackground();
        game.shapeRenderer.end();
        gamePlayManager.updateCollision(delta, enemies, arrows);
        gamePlayManager.handleTouchEvent(arrows);
        gamePlayManager.updateEnemy(delta, enemies);
        gamePlayManager.updateArrow(delta, arrows);

        game.spriteBatch.begin();
        game.spriteBatch.enableBlending();
        gamePlayRenderer.drawArrow(arrows);
        gamePlayRenderer.drawEnemy(enemies);
        gamePlayRenderer.drawScore(gamePlayManager);
        game.spriteBatch.disableBlending();
        game.spriteBatch.end();

        elapseTime += delta;
        if (elapseTime > 0.9f) {
            gamePlayManager.randomSpawnAnWithFixedTime(enemies);
            elapseTime = 0;
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