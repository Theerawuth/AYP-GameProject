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
    final ArrowStormGame game;

    private Sprite introImageSprite;
    private Animation leafAnimation;
    private Array<Rectangle> leafDrops;
    private TextureRegion currentFrame;

    private float elapsedTime;
    long lastDropTime;

    private static float INTRO_BG_WIDTH = 480;
    private static float INTRO_BG_HEIGHT = 800;

    public TitleScreen(final ArrowStormGame game) {
        this.game = game;
        introImageSprite = AssetsLoader.introImageSprite;
        leafAnimation = AssetsLoader.leafAnimation;

        leafDrops = new Array<Rectangle>();
        spawnLeafDrop();

    }

    @Override
    public void show() {

    }

    private void spawnLeafDrop() {
        Rectangle leafDrop = new Rectangle();
        leafDrop.x = MathUtils.random(0, 480-32);
        leafDrop.y = 800;
        leafDrop.width = 32;
        leafDrop.height = 32;
        leafDrops.add(leafDrop);
        lastDropTime = TimeUtils.nanoTime();
    }


    @Override
    public void render(float delta) {
        game.camera.update();

        game.spriteBatch.setProjectionMatrix(game.camera.combined);

        elapsedTime += delta;

        currentFrame = leafAnimation.getKeyFrame(elapsedTime, true);

        game.spriteBatch.begin();
        game.spriteBatch.draw(introImageSprite, 0, 0, INTRO_BG_WIDTH, INTRO_BG_HEIGHT);
        for(Rectangle leafDrop: leafDrops){
            game.spriteBatch.draw(currentFrame, leafDrop.x, leafDrop.y);
        }
        game.spriteBatch.end();

        //set motion leafDrop
        Iterator<Rectangle> iterator = leafDrops.iterator();
        while (iterator.hasNext()) {
            Rectangle leafDrop = iterator.next();
            leafDrop.y -= 200 * Gdx.graphics.getDeltaTime();
            if (leafDrop.y + 32 < 0)
                iterator.remove();
        }

        //check touchscreen
        if (Gdx.input.isTouched() && elapsedTime > 2.0) {
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

}
