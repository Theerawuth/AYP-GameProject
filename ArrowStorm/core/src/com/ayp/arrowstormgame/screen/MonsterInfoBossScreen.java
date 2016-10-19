package com.ayp.arrowstormgame.screen;

import com.ayp.arrowstormgame.ArrowStormGame;
import com.ayp.arrowstormgame.helper.AssetsLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Theerawuth on 10/18/2016.
 */

public class MonsterInfoBossScreen implements Screen {
    private static final float MONSTER_INFO_BG_WIDTH = 480;
    private static final float MONSTER_INFO_BG_HEIGHT = 800;
    private static final float MODEL_ENEMY_POS_X = 30;
    private static final float MODEL_ENEMY_POS_Y = 160;
    private static final float BACK_ICON_POS_X = 0;
    private static final float BACK_ICON_POS_Y = 0;

    private ArrowStormGame game;
    private BitmapFont bodyFont;
    private BitmapFont bodyShadow;
    private Sprite monsterInfoImageSprite;
    private Sprite headerMonsterInfo;
    private Sprite backIconSprite;
    private String[][] nameBossEnemies;
    private Animation modelBossGoldenBugAnimation;
    private TextureRegion currentFrameBossGoldenBug;
    private Animation modelBossScorpion;
    private TextureRegion currentFrameBossScorpion;
    private Animation modelBossKraken;
    private TextureRegion currentFrameBossKraken;
    private Vector3 touchButton;
    private float elapsedTime;

    public MonsterInfoBossScreen(final ArrowStormGame game) {
        this.game = game;
        bodyFont = AssetsLoader.scoreFont;
        bodyShadow = AssetsLoader.scoreShadow;
        bodyFont.getData().setScale(0.5f, -0.5f);
        bodyShadow.getData().setScale(0.5f, -0.5f);

        monsterInfoImageSprite = AssetsLoader.monsterInfoImageSprite;
        modelBossGoldenBugAnimation = AssetsLoader.bossGoldenBugAnimation;
        modelBossScorpion = AssetsLoader.bossScorpionAnimation;
        modelBossKraken = AssetsLoader.bossKrakenAnimation;
        headerMonsterInfo = AssetsLoader.headerSprite;
        backIconSprite = AssetsLoader.backIconSprite;

        nameBossEnemies = new String[6][4];
        nameBossEnemies[0][0] = "- BOSS GOLDEN BUG -";
        nameBossEnemies[0][1] = "HP = 800";
        nameBossEnemies[0][2] = "ATK = 20";
        nameBossEnemies[0][3] = "MS = 120";
        nameBossEnemies[1][0] = "- BOSS SCORPION -";
        nameBossEnemies[1][1] = "HP = 1800";
        nameBossEnemies[1][2] = "ATK = 40";
        nameBossEnemies[1][3] = "MS = 180";
        nameBossEnemies[2][0] = "- BOSS KRAKEN -";
        nameBossEnemies[2][1] = "HP = 4000";
        nameBossEnemies[2][2] = "ATK = 70";
        nameBossEnemies[2][3] = "MS = 90";

        elapsedTime = 0;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        elapsedTime += delta;
        game.spriteBatch.setProjectionMatrix(game.camera.combined);
        setCurrentFrameAnimation();
        game.spriteBatch.begin();
        game.spriteBatch.enableBlending();
        drawBackground();
        drawIcon();
        drawFont();
        drawModelEnemy();
        game.spriteBatch.disableBlending();
        game.spriteBatch.end();
        handleTouchEvent();

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

    public void setCurrentFrameAnimation() {
        currentFrameBossGoldenBug = modelBossGoldenBugAnimation.getKeyFrame(elapsedTime, true);
        currentFrameBossScorpion = modelBossScorpion.getKeyFrame(elapsedTime, true);
        currentFrameBossKraken = modelBossKraken.getKeyFrame(elapsedTime, true);
    }

    public void drawBackground() {
        game.spriteBatch.draw(monsterInfoImageSprite, 0, 0, MONSTER_INFO_BG_WIDTH, MONSTER_INFO_BG_HEIGHT);
        game.spriteBatch.draw(headerMonsterInfo, 65, 50, 340, 100);
    }

    public void drawFont() {
        //ENEMY BUG
        bodyShadow.draw(game.spriteBatch, nameBossEnemies[0][0], 130, 175);
        bodyFont.draw(game.spriteBatch, nameBossEnemies[0][0], 130, 177);
        bodyShadow.draw(game.spriteBatch, nameBossEnemies[0][1], 130, 215);
        bodyFont.draw(game.spriteBatch, nameBossEnemies[0][1], 130, 217);
        bodyShadow.draw(game.spriteBatch, nameBossEnemies[0][2], 130, 255);
        bodyFont.draw(game.spriteBatch, nameBossEnemies[0][2], 130, 257);
        bodyShadow.draw(game.spriteBatch, nameBossEnemies[0][3], 130, 295);
        bodyFont.draw(game.spriteBatch, nameBossEnemies[0][3], 130, 297);
        //ENEMY WORM
        bodyShadow.draw(game.spriteBatch, nameBossEnemies[1][0], 130, 365);
        bodyFont.draw(game.spriteBatch, nameBossEnemies[1][0], 130, 367);
        bodyShadow.draw(game.spriteBatch, nameBossEnemies[1][1], 130, 405);
        bodyFont.draw(game.spriteBatch, nameBossEnemies[1][1], 130, 407);
        bodyShadow.draw(game.spriteBatch, nameBossEnemies[1][2], 130, 445);
        bodyFont.draw(game.spriteBatch, nameBossEnemies[1][2], 130, 447);
        bodyShadow.draw(game.spriteBatch, nameBossEnemies[1][3], 130, 485);
        bodyFont.draw(game.spriteBatch, nameBossEnemies[1][3], 130, 487);
        //ENEMY GUARDIAN
        bodyShadow.draw(game.spriteBatch, nameBossEnemies[2][0], 130, 565);
        bodyFont.draw(game.spriteBatch, nameBossEnemies[2][0], 130, 567);
        bodyShadow.draw(game.spriteBatch, nameBossEnemies[2][1], 130, 605);
        bodyFont.draw(game.spriteBatch, nameBossEnemies[2][1], 130, 607);
        bodyShadow.draw(game.spriteBatch, nameBossEnemies[2][2], 130, 645);
        bodyFont.draw(game.spriteBatch, nameBossEnemies[2][2], 130, 647);
        bodyShadow.draw(game.spriteBatch, nameBossEnemies[2][3], 130, 685);
        bodyFont.draw(game.spriteBatch, nameBossEnemies[2][3], 130, 687);
    }

    public void drawModelEnemy() {
        game.spriteBatch.draw(currentFrameBossGoldenBug, MODEL_ENEMY_POS_X, MODEL_ENEMY_POS_Y);
        game.spriteBatch.draw(currentFrameBossScorpion, MODEL_ENEMY_POS_X, MODEL_ENEMY_POS_Y + 200);
        game.spriteBatch.draw(currentFrameBossKraken, MODEL_ENEMY_POS_X, MODEL_ENEMY_POS_Y + 400);
    }

    public void drawIcon() {
        game.spriteBatch.draw(backIconSprite, BACK_ICON_POS_X, BACK_ICON_POS_Y);
    }

    private void handleTouchEvent() {
        float x = Gdx.input.getX();
        float y = Gdx.input.getY();
        touchButton = new Vector3(x, y, 0);
        backIconSprite.setPosition(BACK_ICON_POS_X, BACK_ICON_POS_Y);
        game.camera.unproject(touchButton);
        if (Gdx.input.isTouched() && elapsedTime > 0.5) {
            elapsedTime = 0;
            if (touchButton.x > backIconSprite.getX()
                    && touchButton.x < (backIconSprite.getX() + backIconSprite.getWidth())
                    && touchButton.y > backIconSprite.getY()
                    && touchButton.y < (backIconSprite.getY() + backIconSprite.getHeight())) {
                game.setScreen(new MonsterInfoScreen(game));
            }
        }
    }

}
