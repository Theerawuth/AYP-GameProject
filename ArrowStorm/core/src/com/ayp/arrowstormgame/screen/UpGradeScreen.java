package com.ayp.arrowstormgame.screen;

import com.ayp.arrowstormgame.ArrowStormGame;
import com.ayp.arrowstormgame.helper.AssetsLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Theerawuth on 10/18/2016.
 */

public class UpGradeScreen implements Screen {
    private static final float BACK_ICON_POS_X = 0;
    private static final float BACK_ICON_POS_Y = 0;
    private static final float UPGRADE_BG_WIDTH = 480;
    private static final float UPGRADE_BG_HEIGHT = 800;
    private static final float PLUS_ATK_ICON_POS_X = 140;
    private static final float PLUS_ATK_ICON_POS_Y = 240;
    private static final float PLUS_HP_ICON_POS_X = 140;
    private static final float PLUS_HP_ICON_POS_Y = 400;
    private static final float PLUS_ASPD_ICON_POS_X = 140;
    private static final float PLUS_ASPD_ICON_POS_Y = 560;

    private ArrowStormGame game;
    private Sprite coinIconSprite;
    private Sprite atkIconSprite;
    private Sprite hpIconSprite;
    private Sprite aspdIconSprite;
    private Sprite backIconSprite;
    private Sprite headerUpGradeSprite;
    private Sprite upGradeBackgroundSprite;
    private Sprite plusAtkIconSprite;
    private Sprite plusHpIconSprite;
    private Sprite plusAspdIconSprite;
    private Sprite coinUpgradeIconSprite;

    private String[][] titleStatus;
    private BitmapFont bodyFont;
    private BitmapFont bodyShadow;
    private Vector3 touchButton;
    private float elapsedTime;


    public UpGradeScreen(final ArrowStormGame game){
        this.game = game;
        upGradeBackgroundSprite = AssetsLoader.upGradeBackgroundSprite;
        headerUpGradeSprite = AssetsLoader.headerUpGradeSprite;
        backIconSprite = AssetsLoader.backIconSprite;
        coinIconSprite = AssetsLoader.coinIconSprite;
        atkIconSprite = AssetsLoader.atkIconSprite;
        hpIconSprite = AssetsLoader.hpIconSprite;
        aspdIconSprite = AssetsLoader.aspdIconSprite;
        plusAtkIconSprite = AssetsLoader.plusIconSprite;
        plusHpIconSprite = AssetsLoader.plusIconSprite;
        plusAspdIconSprite = AssetsLoader.plusIconSprite;
        coinUpgradeIconSprite = AssetsLoader.coinUpgradeIconSprite;
        bodyFont = AssetsLoader.font;
        bodyShadow = AssetsLoader.shadow;
        bodyFont.getData().setScale(0.5f, -0.5f);
        bodyShadow.getData().setScale(0.5f, -0.5f);

        elapsedTime = 0;

        titleStatus = new String[3][4];
        titleStatus[0][0] = "ATTACK DAMAGE";
        titleStatus[0][1] = "ATK : ";
        titleStatus[0][2] = "10"; // status atk
        titleStatus[0][3] = "300"; // coin up atk
        titleStatus[1][0] = "HEALTH POINT";
        titleStatus[1][1] = "HP : ";
        titleStatus[1][2] = "100"; // status hp
        titleStatus[1][3] = "400"; // coin up atk
        titleStatus[2][0] = "ATTACK SPEED";
        titleStatus[2][1] = "ASPD : ";
        titleStatus[2][2] = "30"; //status aspd
        titleStatus[2][3] = "300"; // coin up aspd

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        elapsedTime += delta;
        game.spriteBatch.setProjectionMatrix(game.camera.combined);
        game.spriteBatch.begin();
        game.spriteBatch.enableBlending();
        drawBackground();
        drawIcon();
        drawText();
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

    public void drawBackground() {
        game.spriteBatch.draw(upGradeBackgroundSprite, 0, 0, UPGRADE_BG_WIDTH, UPGRADE_BG_HEIGHT);
        game.spriteBatch.draw(headerUpGradeSprite, 65, 50, 340, 100);
    }

    public void drawIcon() {
        game.spriteBatch.draw(backIconSprite, BACK_ICON_POS_X, BACK_ICON_POS_Y);
        game.spriteBatch.draw(plusAtkIconSprite, PLUS_ATK_ICON_POS_X, PLUS_ATK_ICON_POS_Y);
        game.spriteBatch.draw(plusHpIconSprite, PLUS_HP_ICON_POS_X, PLUS_HP_ICON_POS_Y);
        game.spriteBatch.draw(plusAspdIconSprite, PLUS_ASPD_ICON_POS_X, PLUS_ASPD_ICON_POS_Y);
        game.spriteBatch.draw(coinUpgradeIconSprite, 275, 235);
        game.spriteBatch.draw(coinUpgradeIconSprite, 275, 395);
        game.spriteBatch.draw(coinUpgradeIconSprite, 275, 555);
        game.spriteBatch.draw(coinIconSprite, 240, 704);
        game.spriteBatch.draw(atkIconSprite, 45, 160);
        game.spriteBatch.draw(hpIconSprite, 45, 320);
        game.spriteBatch.draw(aspdIconSprite, 45, 480);
    }

    public void drawText() {
        //ATK
        bodyShadow.draw(game.spriteBatch, titleStatus[0][0], 140, 160);
        bodyFont.draw(game.spriteBatch, titleStatus[0][0], 140, 162);
        bodyShadow.draw(game.spriteBatch, titleStatus[0][1], 140, 200);
        bodyFont.draw(game.spriteBatch, titleStatus[0][1], 140, 202);
        bodyShadow.draw(game.spriteBatch, titleStatus[0][2], 250, 200);
        bodyFont.draw(game.spriteBatch, titleStatus[0][2], 250, 202);
        bodyShadow.draw(game.spriteBatch, titleStatus[0][3], 215, 240);
        bodyFont.draw(game.spriteBatch, titleStatus[0][3], 215, 242);
        //HP
        bodyShadow.draw(game.spriteBatch, titleStatus[1][0], 140, 320);
        bodyFont.draw(game.spriteBatch, titleStatus[1][0], 140, 322);
        bodyShadow.draw(game.spriteBatch, titleStatus[1][1], 140, 360);
        bodyFont.draw(game.spriteBatch, titleStatus[1][1], 140, 362);
        bodyShadow.draw(game.spriteBatch, titleStatus[1][2], 240, 360);
        bodyFont.draw(game.spriteBatch, titleStatus[1][2], 240, 362);
        bodyShadow.draw(game.spriteBatch, titleStatus[1][3], 215, 400);
        bodyFont.draw(game.spriteBatch, titleStatus[1][3], 215, 402);
        //ASPD
        bodyShadow.draw(game.spriteBatch, titleStatus[2][0], 140, 480);
        bodyFont.draw(game.spriteBatch, titleStatus[2][0], 140, 482);
        bodyShadow.draw(game.spriteBatch, titleStatus[2][1], 140, 520);
        bodyFont.draw(game.spriteBatch, titleStatus[2][1], 140, 522);
        bodyShadow.draw(game.spriteBatch, titleStatus[2][2], 270, 520);
        bodyFont.draw(game.spriteBatch, titleStatus[2][2], 270, 522);
        bodyShadow.draw(game.spriteBatch, titleStatus[2][3], 215, 560);
        bodyFont.draw(game.spriteBatch, titleStatus[2][3], 215, 562);

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
                game.setScreen(new MainMenuScreen(game));
            }
        }
    }
}
