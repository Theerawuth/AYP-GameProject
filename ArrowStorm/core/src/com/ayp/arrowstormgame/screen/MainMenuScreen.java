package com.ayp.arrowstormgame.screen;

import com.ayp.arrowstormgame.ArrowStormGame;
import com.ayp.arrowstormgame.helper.AssetsLoader;
import com.ayp.arrowstormgame.helper.MusicManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Theerawuth on 9/23/2016.
 */
public class MainMenuScreen implements Screen {
    private static final String TAG = "MainMenuScreen";

    private static final float HIGH_SCORE_POS_X = 0;
    private static final float HIGH_SCORE_POS_Y = 704;
    private static final float UPGRADE_POS_X = 96;
    private static final float UPGRADE_POS_Y = 704;
    private static final float BATTLE_POS_X = 192;
    private static final float BATTLE_POS_Y = 704;
    private static final float MONSTER_POS_X = 288;
    private static final float MONSTER_POS_Y = 704;
    private static final float FACEBOOK_POS_X = 384;
    private static final float FACEBOOK_POS_Y = 704;
    private static final float OPEN_MUSIC_POS_X = 400;
    private static final float OPEN_MUSIC_POS_Y = 20;
    private static final float CLOSE_MUSIC_POS_X = 400;
    private static final float CLOSE_MUSIC_POS_Y = 20;

    private static float MAIN_MENU_BG_WIDTH = 480;
    private static float MAIN_MENU_BG_HEIGHT = 800;
    private static float ICON_WIDTH = 96;
    private static float ICON_HEIGHT = 96;
    private static final float MUSIC_ICON_WIDTH = 64;
    private static final float MUSIC_ICON_HEIGHT = 64;

    private float elapsedTime;

    private final ArrowStormGame game;
    private Sprite mainMenuImageSprite;
    private Sprite highScoreImageSprite;
    private Sprite upGradeImageSprite;
    private Sprite battleImageSprite;
    private Sprite monsterImageSprite;
    private Sprite facebookImageSprite;
    private Sprite openMusicSprite;
    private Sprite closeMusicSprite;
    private Vector3 touchButton;
    private Music mainMenuBackgroundMusic;
    private Music toBattleMusic;

    private MusicManager manageMusicMainBackground;
    private MusicManager manageMusicToBattle;
    boolean isClick;

    //TODO Create menu button

    public MainMenuScreen(final ArrowStormGame game) {
        this.game = game;
        mainMenuImageSprite = AssetsLoader.mainMenuImageSprite;
        highScoreImageSprite = AssetsLoader.highScoreImageSprite;
        upGradeImageSprite = AssetsLoader.upGradeImageSprite;
        battleImageSprite = AssetsLoader.battleImageSprite;
        monsterImageSprite = AssetsLoader.monsterImageSprite;
        facebookImageSprite = AssetsLoader.facebookImageSprite;
        openMusicSprite = AssetsLoader.openMusicSprite;
        closeMusicSprite = AssetsLoader.closeMusicSprite;
        mainMenuBackgroundMusic = AssetsLoader.mainMenuBackgroundMusic;
        toBattleMusic = AssetsLoader.toBattleMusic;
        elapsedTime = 0;
        isClick = false;
    }

    @Override
    public void show() {
        Gdx.app.log("MusicManager", "Start sound in main menu screen");
        manageMusicMainBackground = new MusicManager(mainMenuBackgroundMusic);
        manageMusicMainBackground.backgroundMusicPlay();
    }


    @Override
    public void render(float delta) {
        elapsedTime += delta;
        game.spriteBatch.setProjectionMatrix(game.camera.combined);
        game.spriteBatch.begin();
        drawIcon();
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

    private void drawIcon() {
        game.spriteBatch.draw(
                mainMenuImageSprite, 0, 0, MAIN_MENU_BG_WIDTH, MAIN_MENU_BG_HEIGHT
        );
        game.spriteBatch.draw(
                highScoreImageSprite, HIGH_SCORE_POS_X, HIGH_SCORE_POS_Y, ICON_WIDTH, ICON_HEIGHT
        );
        game.spriteBatch.draw(
                upGradeImageSprite, UPGRADE_POS_X, UPGRADE_POS_Y, ICON_WIDTH, ICON_HEIGHT
        );
        game.spriteBatch.draw(
                battleImageSprite, BATTLE_POS_X, BATTLE_POS_Y, ICON_WIDTH, ICON_HEIGHT
        );
        game.spriteBatch.draw(
                monsterImageSprite, MONSTER_POS_X, MONSTER_POS_Y, ICON_WIDTH, ICON_HEIGHT
        );
        game.spriteBatch.draw(
                facebookImageSprite, FACEBOOK_POS_X, FACEBOOK_POS_Y, ICON_WIDTH, ICON_HEIGHT
        );
        game.spriteBatch.draw(
                closeMusicSprite,
                CLOSE_MUSIC_POS_X,
                CLOSE_MUSIC_POS_Y,
                MUSIC_ICON_WIDTH,
                MUSIC_ICON_HEIGHT
        );
    }

    private void handleTouchEvent() {
        float x = Gdx.input.getX();
        float y = Gdx.input.getY();
        touchButton = new Vector3(x, y, 0);
        battleImageSprite.setPosition(BATTLE_POS_X, BATTLE_POS_Y);
        openMusicSprite.setPosition(OPEN_MUSIC_POS_X, OPEN_MUSIC_POS_Y);
        closeMusicSprite.setPosition(CLOSE_MUSIC_POS_X, CLOSE_MUSIC_POS_Y);
        game.camera.unproject(touchButton);
        if (Gdx.input.isTouched() && elapsedTime > 2.0) {
            elapsedTime = 0;
            Gdx.app.log(TAG, "x: " + touchButton.x + ", y: " + touchButton.y);
            Gdx.app.log(TAG, "x: " + battleImageSprite.getX() + ", y: " + battleImageSprite.getY());
            if (touchButton.x > battleImageSprite.getX()
                    && touchButton.x < (battleImageSprite.getX() + battleImageSprite.getWidth())
                    && touchButton.y > battleImageSprite.getY()
                    && touchButton.y < (battleImageSprite.getY() + battleImageSprite.getHeight())) {
                Gdx.app.log("MusicManager", "Click to PlayStateScreen");
                manageMusicToBattle = new MusicManager(toBattleMusic);
                manageMusicToBattle.backgroundMusicPlay();
                manageMusicMainBackground.backgroundMusicStop();
                game.setScreen(new PlayStateScreen(game));
            }
            if (touchButton.x > openMusicSprite.getX()
                    && touchButton.x < (openMusicSprite.getX() + openMusicSprite.getWidth())
                    && touchButton.y > openMusicSprite.getY()
                    && touchButton.y < (openMusicSprite.getY() + openMusicSprite.getHeight())) {
            }
            if (touchButton.x > closeMusicSprite.getX()
                    && touchButton.x < (closeMusicSprite.getX() + closeMusicSprite.getWidth())
                    && touchButton.y > closeMusicSprite.getY()
                    && touchButton.y < (closeMusicSprite.getY() + closeMusicSprite.getHeight())) {
                Gdx.app.log("MusicManager", "Click On/Off Sound");
                manageMusicMainBackground.setSwitchSound();
            }
        }
    }
}
