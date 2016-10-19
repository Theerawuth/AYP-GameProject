package com.ayp.arrowstormgame.screen;

import com.ayp.arrowstormgame.ArrowStormGame;
import com.ayp.arrowstormgame.game_manager.EnemyLevelManager;
import com.ayp.arrowstormgame.game_manager.GamePlayManager;
import com.ayp.arrowstormgame.game_manager.GamePlayRenderer;
import com.ayp.arrowstormgame.model.Arrow;
import com.ayp.arrowstormgame.model.Enemy;
import com.ayp.arrowstormgame.model.Player;
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
    private EnemyLevelManager enemyLevelManager;

    private Array<Arrow> arrows;
    private Array<Enemy> enemies;

    private float runtime;


    public PlayStateScreen(final ArrowStormGame game) {
        this.game = game;
        gamePlayRenderer = new GamePlayRenderer(game);
        gamePlayManager = new GamePlayManager(game);
        enemyLevelManager = gamePlayManager.getEnemyLevelManager();
        arrows = new Array<Arrow>();
        enemies = new Array<Enemy>();
        runtime = 0;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        runtime += delta;
        Gdx.gl.glClearColor(0, 0, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.spriteBatch.begin();
        gamePlayRenderer.drawBackground();
        game.spriteBatch.end();
        if (Player.isAlive()) {
            if (gamePlayManager.isPause()) {
                game.spriteBatch.begin();
                gamePlayRenderer.drawResumeMessage();
                gamePlayRenderer.drawQuitMessage();
                game.spriteBatch.end();
            }

            gamePlayManager.handleTouchEvent(arrows);
            if (!gamePlayManager.isPause()) {
                gamePlayManager.updateCollision(delta, enemies, arrows);
                gamePlayManager.updateEnemy(delta, enemies);
                gamePlayManager.updateArrow(delta, arrows);
                enemyLevelManager.updateEnemyLevelByTime(delta);

                game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                game.shapeRenderer.end();

                game.spriteBatch.begin();
                game.spriteBatch.enableBlending();
                gamePlayRenderer.drawPlayer(runtime);
                gamePlayRenderer.drawArrow(arrows);
                gamePlayRenderer.drawEnemy(enemies, runtime);
                gamePlayRenderer.drawScore(gamePlayManager);
                gamePlayRenderer.drawGold(gamePlayManager);
                gamePlayRenderer.drawHeart();
                gamePlayRenderer.drawPauseButton();
                game.spriteBatch.disableBlending();
                game.spriteBatch.end();

                gamePlayManager.update();
                gamePlayManager.spawnEnemy(delta, enemies);
            }
        } else {
            game.spriteBatch.begin();
            gamePlayRenderer.drawGameOver();
            game.spriteBatch.end();
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