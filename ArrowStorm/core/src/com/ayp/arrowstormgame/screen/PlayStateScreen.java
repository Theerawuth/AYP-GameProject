package com.ayp.arrowstormgame.screen;

import com.ayp.arrowstormgame.ArrowStormGame;
import com.ayp.arrowstormgame.model.Arrow;
import com.ayp.arrowstormgame.model.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

/**
 * Created by Theerawuth on 9/23/2016.
 */
public class PlayStateScreen implements Screen {
    public static String TAG = "PlayStateScreen";
    final ArrowStormGame mGame;
    private OrthographicCamera mCamera;
    private SpriteBatch mBatch;
    private ShapeRenderer mShapeRenderer;
    private Array<Arrow> mArrows;
    private Texture arrowImage;
    private static int PREPARE_SHOOT = -1;
    private long shootDelay = 500000000;
    private long lastArrow;
    private long lastTouched;
    private Sprite mSprite;
    private int scaleArrowX;
    private int scaleArrowY;
    private Vector3 touchPosition;
    private float ARROW_VELOCITY = 200;

    public PlayStateScreen(final ArrowStormGame game) {
        mGame = game;
        arrowImage = new Texture(Gdx.files.internal("Arrows.png"));
        mSprite = new Sprite(arrowImage);
        mSprite.flip(false, true);
        mCamera = new OrthographicCamera();
        mCamera.setToOrtho(true, ArrowStormGame.GAME_WIDTH, ArrowStormGame.GAME_HEIGHT);
        mBatch = new SpriteBatch();
        mBatch.setProjectionMatrix(mCamera.combined);
        mShapeRenderer = new ShapeRenderer();
        mShapeRenderer.setProjectionMatrix(mCamera.combined);
        mArrows = new Array<Arrow>();
        lastArrow = PREPARE_SHOOT;
        lastTouched = TimeUtils.nanoTime() - shootDelay;
        scaleArrowX = 1;
        scaleArrowY = 1;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        drawBackground();
        mShapeRenderer.end();

        mBatch.begin();
        drawArrow();
        mBatch.end();

        handleTouchEvent();
        updateArrow(delta);
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

    private void drawArrow() {
        for (Arrow arrow : mArrows) {
            mBatch.draw(
                    mSprite,
                    arrow.getPosition().x - arrow.getWidth() / 2,
                    arrow.getPosition().y,
                    arrow.getWidth() / 2,
                    arrow.getHeight() / 2,
                    arrow.getWidth(),
                    arrow.getHeight(),
                    scaleArrowX,
                    scaleArrowY,
                    arrow.getAngle()
            );
        }

    }

    private void updateArrow(float delta) {
        Iterator<Arrow> iterator = mArrows.iterator();
        while (iterator.hasNext()) {
            Arrow arrow = iterator.next();
            arrow.setPosition(arrow.getPosition().sub(arrow.getVelocity()));
            if (arrow.getPosition().x < 0
                    || arrow.getPosition().x > ArrowStormGame.GAME_WIDTH
                    || arrow.getPosition().y < 0) {
                iterator.remove();
            }
        }
    }

    private void drawBackground() {
        mShapeRenderer.setColor(1, 0, 0, 1);
        mShapeRenderer.rect(Player.POSITION_X, Player.POSITION_Y, Player.PLAYER_WIDTH, Player.PLAYER_HEIGHT);
        mShapeRenderer.setColor(1, 1, 0, 1);
        mShapeRenderer.rect(Player.POSITION_X + Player.PLAYER_WIDTH / 2, Player.POSITION_Y,
                Player.PLAYER_WIDTH / 2, Player.PLAYER_HEIGHT);
    }

    private void fireArrow(float arrowAngle, float velocityX, float velocityY) {
        Arrow arrow = new Arrow(Player.SHOOTING_POINT_X, Player.SHOOTING_POINT_Y, velocityX, velocityY, arrowAngle);
        mArrows.add(arrow);
        lastArrow = TimeUtils.nanoTime();
    }

    private void handleTouchEvent() {
        if (Gdx.input.isTouched()) {
            touchPosition = new Vector3();
            touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            mCamera.unproject(touchPosition);
            if (touchPosition.y < Player.SHOOTING_POINT_Y) {
                // Cancelled touch outside of player zone
                return;
            }

            float angleDegree = (float) Math.toDegrees(Math.atan2(
                    touchPosition.y - Player.SHOOTING_POINT_Y,
                    touchPosition.x - Player.SHOOTING_POINT_X)
            );

            float arrowAngle = angleDegree - 90;

            float arrowDirectionDegree = arrowAngle + 90;
            Gdx.app.log(TAG, "arrow Dir: " + arrowDirectionDegree);


            Gdx.app.log(TAG, "angle: " + arrowAngle);
            float velocityX = (float) (ARROW_VELOCITY * Math.cos(Math.toRadians(arrowDirectionDegree))) * Gdx.graphics.getDeltaTime();
            float velocityY = (float) (ARROW_VELOCITY * Math.sin(Math.toRadians(arrowDirectionDegree))) * Gdx.graphics.getDeltaTime();

            Gdx.app.log(TAG, "veloX: " + velocityX + " , veloY: " + velocityY);
            if (lastArrow == PREPARE_SHOOT) {
                fireArrow(arrowAngle, velocityX, velocityY);
            } else if (TimeUtils.nanoTime() - lastArrow > shootDelay) {
                fireArrow(arrowAngle, velocityX, velocityY);
            }
        } else {
            if (TimeUtils.nanoTime() - lastTouched > shootDelay && TimeUtils.nanoTime() - lastArrow > shootDelay) {
                lastArrow = PREPARE_SHOOT;
                lastTouched = TimeUtils.nanoTime();
            }
        }
    }
}