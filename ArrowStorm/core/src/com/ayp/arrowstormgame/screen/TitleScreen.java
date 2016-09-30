package com.ayp.arrowstormgame.screen;

import com.ayp.arrowstormgame.ArrowStormGame;
import com.ayp.arrowstormgame.helper.AssetsLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

/**
 * Created by Theerawuth on 9/23/2016.
 */
public class TitleScreen implements Screen {
    private static String TAG = "TitleScreen";
    final ArrowStormGame game;

    private Sprite introImageSprite;
    //animation leaf
    private Animation leafAnimation;
    private Array<Rectangle> leafDrops;
    private TextureRegion currentFrameLeaf;
    //animation title
    private Animation titleAnimation;
    private Array<Rectangle> titleChanges;
    private TextureRegion currentFrameTitle;


    private float elapsedTime;
    long lastDropTime;

    private static float INTRO_BG_WIDTH = 480;
    private static float INTRO_BG_HEIGHT = 800;

    public TitleScreen(final ArrowStormGame game) {
        this.game = game;
        introImageSprite = AssetsLoader.introImageSprite;
        leafAnimation = AssetsLoader.leafAnimation;
        titleAnimation = AssetsLoader.titleAnimation;
        // create Array leafDrops
        leafDrops = new Array<Rectangle>();
        // create Array titleChange
        titleChanges = new Array<Rectangle>();
        lastDropTime = TimeUtils.nanoTime();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        game.spriteBatch.setProjectionMatrix(game.camera.combined);

        elapsedTime += delta;

        currentFrameLeaf = leafAnimation.getKeyFrame(elapsedTime, true);
        currentFrameTitle = titleAnimation.getKeyFrame(elapsedTime, true);

        if (!currentFrameTitle.isFlipY())
            currentFrameTitle.flip(false, true);

        game.spriteBatch.begin();
        game.spriteBatch.draw(introImageSprite, 0, 0, INTRO_BG_WIDTH, INTRO_BG_HEIGHT);
        for (Rectangle leafDrop : leafDrops) {
            game.spriteBatch.draw(currentFrameLeaf, leafDrop.x, leafDrop.y);
        }
        for (Rectangle titleChange : titleChanges) {
            game.spriteBatch.draw(currentFrameTitle, titleChange.x, titleChange.y);
        }
        game.spriteBatch.end();

        //check time for set start leaf drop and title change
        if (TimeUtils.nanoTime() - lastDropTime > 100000000) {
            spawnLeafDrop();
            spawnTitleChange();
        }


        //set motion leafDrop
        Iterator<Rectangle> iteratorLeaf = leafDrops.iterator();
        while (iteratorLeaf.hasNext()) {
            Rectangle leafDrop = iteratorLeaf.next();
            leafDrop.y += 200 * Gdx.graphics.getDeltaTime();
            if (leafDrop.y > ArrowStormGame.GAME_HEIGHT + leafDrop.width) {
                iteratorLeaf.remove();
            }
        }

        //check touchscreen
        if (Gdx.input.isTouched() && elapsedTime > 1.0) {
            //change display to GameScreen Display
            game.setScreen(new MainMenuScreen(game));
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

    private void spawnLeafDrop() {
        Rectangle leafDrop = new Rectangle();
        leafDrop.x = MathUtils.random(0, 480 - 32);
        leafDrop.y = 0;
        leafDrops.add(leafDrop);
        lastDropTime = TimeUtils.nanoTime();
    }

    private void spawnTitleChange() {
        Rectangle titleChange = new Rectangle();
        titleChange.x = 0;
        titleChange.y = 420;
        titleChanges.add(titleChange);
    }

}
