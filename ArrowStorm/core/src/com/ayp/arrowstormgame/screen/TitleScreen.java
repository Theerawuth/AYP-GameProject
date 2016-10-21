package com.ayp.arrowstormgame.screen;

import com.ayp.arrowstormgame.ArrowStormGame;
import com.ayp.arrowstormgame.helper.AssetsLoader;
import com.ayp.arrowstormgame.helper.MusicManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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
    final ArrowStormGame game;

    private Sprite introImageSprite;
    private Sprite touchToStartSprite;
    private Music introBackgroundMusic;
    private MusicManager manageTitleMusicBackground;
    //animation leaf
    private Animation leafAnimation;
    private Array<Rectangle> leafDrops;
    private TextureRegion currentFrameLeaf;

    private float elapsedTime;
    long lastDropTime;

    private static float INTRO_BG_WIDTH = 480;
    private static float INTRO_BG_HEIGHT = 800;
    private static float TOUCH_TO_START_WIDTH = 300;
    private static float TOUCH_TO_START_HEIGHT = 60;

    public TitleScreen(final ArrowStormGame game) {
        this.game = game;
        introImageSprite = AssetsLoader.introBackgroundSprite;
        touchToStartSprite = AssetsLoader.touchToStartSprite;
        leafAnimation = AssetsLoader.leafAnimation;
        introBackgroundMusic = AssetsLoader.introBackgroundMusic;

        // create Array leafDrops
        leafDrops = new Array<Rectangle>();

        lastDropTime = TimeUtils.nanoTime();
    }

    @Override
    public void show() {
        Gdx.app.log("MusicManager", "Start sound in title screen");
        manageTitleMusicBackground = new MusicManager(introBackgroundMusic);
        manageTitleMusicBackground.backgroundMusicPlay();
        game.playServices.signIn();

    }

    @Override
    public void render(float delta) {
        game.spriteBatch.setProjectionMatrix(game.camera.combined);
        elapsedTime += delta;
        currentFrameLeaf = leafAnimation.getKeyFrame(elapsedTime, true);
        game.spriteBatch.begin();
        game.spriteBatch.draw(introImageSprite, 0, 0, INTRO_BG_WIDTH, INTRO_BG_HEIGHT);
        game.spriteBatch.draw(touchToStartSprite, 85, 600, TOUCH_TO_START_WIDTH, TOUCH_TO_START_HEIGHT);
        for (Rectangle leafDrop : leafDrops) {
            game.spriteBatch.draw(currentFrameLeaf, leafDrop.x, leafDrop.y);
        }
        game.spriteBatch.end();
        //check time for set start leaf drop and title change
        if (TimeUtils.nanoTime() - lastDropTime > 100000000) {
            spawnLeafDrop();
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
            introBackgroundMusic.stop();
            //change display to GameScreen Display
            game.setScreen(new MainMenuScreen(game));
            manageTitleMusicBackground.backgroundMusicStop();
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
}
