package com.ayp.arrowstormgame.screen;

import com.ayp.arrowstormgame.ArrowStormGame;
import com.ayp.arrowstormgame.helper.AssetsLoader;
import com.ayp.arrowstormgame.helper.GdxPreference;
import com.ayp.arrowstormgame.model.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
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
    private static final float UPGRADE_DELAY = 1.5f;

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
    private GlyphLayout glyphLayout;
    private String currentGoldString;
    private String[][] titleStatus;
    private BitmapFont bodyFont;
    private BitmapFont bodyShadow;
    private Vector3 touchButton;
    private float elapsedTime;
    private float upgradeDelay;

    public UpGradeScreen(final ArrowStormGame game) {
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
        bodyFont = AssetsLoader.upgradeScreenFont;
        bodyShadow = AssetsLoader.upgradeScreenShadow;
        glyphLayout = new GlyphLayout();
        currentGoldString = String.valueOf(GdxPreference.getCurrentGold());
        elapsedTime = 0;
        upgradeDelay = 0;

        titleStatus = new String[3][4];
        titleStatus[0][0] = "ATTACK DAMAGE";
        titleStatus[0][1] = "LEVEL : ";
        titleStatus[0][2] = String.valueOf(GdxPreference.getCurrentAttackDamageLevel());
        titleStatus[0][3] = Player.skillUpCostString(GdxPreference.getCurrentAttackDamageLevel());
        titleStatus[1][0] = "HEALTH POINT";
        titleStatus[1][1] = "LEVEL : ";
        titleStatus[1][2] = String.valueOf(GdxPreference.getCurrentHealthPointLevel());
        titleStatus[1][3] = Player.skillUpCostString(GdxPreference.getCurrentHealthPointLevel());
        titleStatus[2][0] = "ATTACK SPEED";
        titleStatus[2][1] = "LEVEL : ";
        titleStatus[2][2] = String.valueOf(GdxPreference.getCurrentAttackSpeedLevel());
        titleStatus[2][3] = Player.skillUpCostString(GdxPreference.getCurrentAttackSpeedLevel());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        upgradeDelay += delta;
        elapsedTime += delta;
        game.spriteBatch.setProjectionMatrix(game.camera.combined);
        game.spriteBatch.begin();
        game.spriteBatch.enableBlending();
        drawBackground();
        drawIcon();
        drawText();
        updateScreen();
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
        game.spriteBatch.draw(coinUpgradeIconSprite, 350, 235);
        game.spriteBatch.draw(coinUpgradeIconSprite, 350, 395);
        game.spriteBatch.draw(coinUpgradeIconSprite, 350, 555);
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
        bodyShadow.draw(game.spriteBatch, titleStatus[0][2], 270, 200);
        bodyFont.draw(game.spriteBatch, titleStatus[0][2], 272, 202);
        glyphLayout.setText(bodyFont, titleStatus[0][3]);
        bodyShadow.draw(game.spriteBatch, titleStatus[0][3], 340 - glyphLayout.width, 240);
        bodyFont.draw(game.spriteBatch, titleStatus[0][3], 342 - glyphLayout.width, 242);
        //HP
        bodyShadow.draw(game.spriteBatch, titleStatus[1][0], 140, 320);
        bodyFont.draw(game.spriteBatch, titleStatus[1][0], 140, 322);
        bodyShadow.draw(game.spriteBatch, titleStatus[1][1], 140, 360);
        bodyFont.draw(game.spriteBatch, titleStatus[1][1], 140, 362);
        bodyShadow.draw(game.spriteBatch, titleStatus[1][2], 270, 360);
        bodyFont.draw(game.spriteBatch, titleStatus[1][2], 272, 362);
        glyphLayout.setText(bodyFont, titleStatus[1][3]);
        bodyShadow.draw(game.spriteBatch, titleStatus[1][3], 340 - glyphLayout.width, 400);
        bodyFont.draw(game.spriteBatch, titleStatus[1][3], 342 - glyphLayout.width, 402);
        //ASPD
        bodyShadow.draw(game.spriteBatch, titleStatus[2][0], 140, 480);
        bodyFont.draw(game.spriteBatch, titleStatus[2][0], 140, 482);
        bodyShadow.draw(game.spriteBatch, titleStatus[2][1], 140, 520);
        bodyFont.draw(game.spriteBatch, titleStatus[2][1], 140, 522);
        bodyShadow.draw(game.spriteBatch, titleStatus[2][2], 270, 520);
        bodyFont.draw(game.spriteBatch, titleStatus[2][2], 272, 522);
        glyphLayout.setText(bodyFont, titleStatus[2][3]);
        bodyShadow.draw(game.spriteBatch, titleStatus[2][3], 340 - glyphLayout.width, 560);
        bodyFont.draw(game.spriteBatch, titleStatus[2][3], 342 - glyphLayout.width, 562);
        currentGoldString = String.valueOf(GdxPreference.getCurrentGold());
        glyphLayout.setText(bodyFont, currentGoldString);
        bodyShadow.draw(game.spriteBatch, currentGoldString, 420 - glyphLayout.width, 734);
        bodyFont.draw(game.spriteBatch, currentGoldString, 422 - glyphLayout.width, 736);
    }

    private void handleTouchEvent() {
        if (Gdx.input.isTouched() && elapsedTime > 0.5) {
            touchButton = new Vector3();
            touchButton.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            backIconSprite.setPosition(BACK_ICON_POS_X, BACK_ICON_POS_Y);
            game.camera.unproject(touchButton);
            elapsedTime = 0;
            if (touchButton.x > backIconSprite.getX()
                    && touchButton.x < (backIconSprite.getX() + backIconSprite.getWidth())
                    && touchButton.y > backIconSprite.getY()
                    && touchButton.y < (backIconSprite.getY() + backIconSprite.getHeight())) {
                game.setScreen(new MainMenuScreen(game));
            } else if (upgradeDelay > UPGRADE_DELAY && touchButton.x > PLUS_ATK_ICON_POS_X
                    && touchButton.x < PLUS_ATK_ICON_POS_X + plusAtkIconSprite.getWidth()
                    && touchButton.y > PLUS_ATK_ICON_POS_Y
                    && touchButton.y < PLUS_ATK_ICON_POS_Y + plusAtkIconSprite.getHeight()) {
                int remainGold = GdxPreference.getCurrentGold()
                        - Player.skillUpCostInt(GdxPreference.getCurrentAttackDamageLevel());
                if (remainGold >= 0 && GdxPreference.getCurrentAttackDamageLevel()
                        < Player.MAX_LEVEL_SKILL) {
                    GdxPreference.putCurrentGold(remainGold);
                    GdxPreference.flushPreferences();
                    int nextLevel = GdxPreference.getCurrentAttackDamageLevel() + 1;
                    GdxPreference.putCurrentAttackDamageLevel(nextLevel);
                    GdxPreference.flushPreferences();
                }
                upgradeDelay = 0;
            } else if (upgradeDelay > UPGRADE_DELAY && touchButton.x > PLUS_HP_ICON_POS_X
                    && touchButton.x < PLUS_HP_ICON_POS_X + plusAtkIconSprite.getWidth()
                    && touchButton.y > PLUS_HP_ICON_POS_Y
                    && touchButton.y < PLUS_HP_ICON_POS_Y + plusAtkIconSprite.getHeight()) {
                int remainGold = GdxPreference.getCurrentGold()
                        - Player.skillUpCostInt(GdxPreference.getCurrentHealthPointLevel());
                if (remainGold >= 0 && GdxPreference.getCurrentHealthPointLevel()
                        < Player.MAX_LEVEL_SKILL) {
                    GdxPreference.putCurrentGold(remainGold);
                    GdxPreference.flushPreferences();
                    int nextLevel = GdxPreference.getCurrentHealthPointLevel() + 1;
                    GdxPreference.putCurrentHealthPointLevel(nextLevel);
                    GdxPreference.flushPreferences();
                }
                upgradeDelay = 0;
            } else if (upgradeDelay > UPGRADE_DELAY && touchButton.x > PLUS_ASPD_ICON_POS_X
                    && touchButton.x < PLUS_ASPD_ICON_POS_X + plusAtkIconSprite.getWidth()
                    && touchButton.y > PLUS_ASPD_ICON_POS_Y
                    && touchButton.y < PLUS_ASPD_ICON_POS_Y + plusAtkIconSprite.getHeight()) {
                int remainGold = GdxPreference.getCurrentGold()
                        - Player.skillUpCostInt(GdxPreference.getCurrentAttackSpeedLevel());
                if (remainGold >= 0 && GdxPreference.getCurrentAttackSpeedLevel()
                        < Player.MAX_LEVEL_SKILL) {
                    GdxPreference.putCurrentGold(remainGold);
                    GdxPreference.flushPreferences();
                    int nextLevel = GdxPreference.getCurrentAttackSpeedLevel() + 1;
                    GdxPreference.putCurrentAttackSpeedLevel(nextLevel);
                    GdxPreference.flushPreferences();
                }
                upgradeDelay = 0;
            }
            touchButton = new Vector3();
        }
    }

    private void updateScreen() {
        titleStatus[0][2] = String.valueOf(GdxPreference.getCurrentAttackDamageLevel());
        titleStatus[0][3] = Player.skillUpCostString(GdxPreference.getCurrentAttackDamageLevel());
        titleStatus[1][2] = String.valueOf(GdxPreference.getCurrentHealthPointLevel());
        titleStatus[1][3] = Player.skillUpCostString(GdxPreference.getCurrentHealthPointLevel());
        titleStatus[2][2] = String.valueOf(GdxPreference.getCurrentAttackSpeedLevel());
        titleStatus[2][3] = Player.skillUpCostString(GdxPreference.getCurrentAttackSpeedLevel());
    }
}
