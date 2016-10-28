package com.ayp.arrowstormgame.screen;

import com.ayp.arrowstormgame.ArrowStormGame;
import com.ayp.arrowstormgame.helper.AssetsLoader;
import com.ayp.arrowstormgame.helper.GdxPreference;
import com.ayp.arrowstormgame.helper.MusicManager;
import com.ayp.arrowstormgame.helper.SoundManager;
import com.ayp.arrowstormgame.model.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import java.util.Map;

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
    private static final float SWITCH_MUSIC_POS_X = 400;
    private static final float SWITCH_MUSIC_POS_Y = 20;
    private static final float CLOSE_SOUND_POS_X = 320;
    private static final float CLOSE_SOUND_POS_Y = 20;
    private static final float OPEN_SOUND_POS_X = 320;
    private static final float OPEN_SOUND_POS_Y = 20;
    private static final float SWITCH_SOUND_POS_X = 320;
    private static final float SWITCH_SOUND_POS_Y = 20;

    private static float MAIN_MENU_BG_WIDTH = 480;
    private static float MAIN_MENU_BG_HEIGHT = 800;
    private static float ICON_WIDTH = 96;
    private static float ICON_HEIGHT = 96;
    private static final float MUSIC_ICON_WIDTH = 48;
    private static final float MUSIC_ICON_HEIGHT = 48;
    private static final float SOUND_ICON_WIDTH = 48;
    private static final float SOUND_ICON_HEIGHT = 48;

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
    private Sprite openSoundSprite;
    private Sprite closeSoundSprite;
    private Vector3 touchButton;
    private Music mainMenuBackgroundMusic;
    private BitmapFont highScoreFont;
    private BitmapFont highScoreShadow;
    private String highScoreString;
    private GlyphLayout glyphLayout;
    private static final String HIGH_SCORE_LABEL = "SCORE";
    Player player;

    private MusicManager manageMusicMainBackground;

    //TODO Create menu button

    public MainMenuScreen(final ArrowStormGame game) {
        this.game = game;
        mainMenuImageSprite = AssetsLoader.mainMenuBackgroundSprite;
        highScoreImageSprite = AssetsLoader.highScoreImageSprite;
        upGradeImageSprite = AssetsLoader.upGradeImageSprite;
        battleImageSprite = AssetsLoader.battleImageSprite;
        monsterImageSprite = AssetsLoader.monsterImageSprite;
        facebookImageSprite = AssetsLoader.facebookImageSprite;
        openMusicSprite = AssetsLoader.openMusicSprite;
        closeMusicSprite = AssetsLoader.closeMusicSprite;
        openSoundSprite = AssetsLoader.openSoundSprite;
        closeSoundSprite = AssetsLoader.closeSoundSprite;
        mainMenuBackgroundMusic = AssetsLoader.mainMenuBackgroundMusic;
        highScoreFont = AssetsLoader.highScoreFont;
        highScoreShadow = AssetsLoader.highScoreShadow;
        highScoreString = String.valueOf(GdxPreference.getHighScore());
        glyphLayout = new GlyphLayout();
        elapsedTime = 0;
    }

    @Override
    public void show() {
        if (game.playServices.isSignedIn()) {
            Gdx.app.log(TAG, "Playservices is signed in");
            Map<String, Integer> user;
            user = game.firebaseAuthentication.retrieveUserData();
            player = new Player(user.get("AttackDamageLevel"), user.get("AttackSpeedLevel"),
                    user.get("HealthPointLevel"), user.get("Gold"));
        } else {
            Gdx.app.log(TAG, "Playservices is not signed in");
            player = new Player(GdxPreference.getCurrentAttackDamageLevel(),
                    GdxPreference.getCurrentAttackSpeedLevel(),
                    GdxPreference.getCurrentHealthPointLevel(),
                    GdxPreference.getCurrentGold());
        }
        manageMusicMainBackground = new MusicManager(mainMenuBackgroundMusic);
        if (GdxPreference.getMusicSetting()) {
            manageMusicMainBackground.backgroundMusicPlay();
        }
    }


    @Override
    public void render(float delta) {
        elapsedTime += delta;
        game.spriteBatch.setProjectionMatrix(game.camera.combined);
        game.spriteBatch.begin();
        game.spriteBatch.enableBlending();
        drawBackground();
        drawHighScore();
        drawIcon();
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

    private void drawHighScore() {

        glyphLayout.setText(highScoreFont, HIGH_SCORE_LABEL);
        highScoreShadow.draw(game.spriteBatch, HIGH_SCORE_LABEL,
                (game.GAME_WIDTH / 2 - glyphLayout.width / 2) + 2, 202);
        highScoreFont.draw(game.spriteBatch, HIGH_SCORE_LABEL,
                game.GAME_WIDTH / 2 - glyphLayout.width / 2, 200);

        glyphLayout.setText(highScoreFont, highScoreString);
        highScoreShadow.draw(game.spriteBatch, highScoreString,
                (game.GAME_WIDTH / 2 - glyphLayout.width / 2) + 2, 272);
        highScoreFont.draw(game.spriteBatch, highScoreString,
                game.GAME_WIDTH / 2 - glyphLayout.width / 2, 270);
    }

    private void drawBackground() {
        game.spriteBatch.draw(mainMenuImageSprite, 0, 0, MAIN_MENU_BG_WIDTH, MAIN_MENU_BG_HEIGHT);
    }


    private void drawIcon() {
        game.spriteBatch.draw(highScoreImageSprite, HIGH_SCORE_POS_X, HIGH_SCORE_POS_Y, ICON_WIDTH,
                ICON_HEIGHT);
        game.spriteBatch.draw(upGradeImageSprite, UPGRADE_POS_X, UPGRADE_POS_Y, ICON_WIDTH,
                ICON_HEIGHT);
        game.spriteBatch.draw(battleImageSprite, BATTLE_POS_X, BATTLE_POS_Y, ICON_WIDTH,
                ICON_HEIGHT);
        game.spriteBatch.draw(
                monsterImageSprite, MONSTER_POS_X, MONSTER_POS_Y, ICON_WIDTH, ICON_HEIGHT);
        game.spriteBatch.draw(
                facebookImageSprite, FACEBOOK_POS_X, FACEBOOK_POS_Y, ICON_WIDTH, ICON_HEIGHT);
        if (GdxPreference.getMusicSetting()) {
            game.spriteBatch.draw(closeMusicSprite, CLOSE_MUSIC_POS_X, CLOSE_MUSIC_POS_Y,
                    MUSIC_ICON_WIDTH, MUSIC_ICON_HEIGHT);
        } else {
            game.spriteBatch.draw(openMusicSprite, CLOSE_MUSIC_POS_X, CLOSE_MUSIC_POS_Y,
                    MUSIC_ICON_WIDTH, MUSIC_ICON_HEIGHT);
        }
        if (GdxPreference.getSoundSetting()) {
            game.spriteBatch.draw(closeSoundSprite, CLOSE_SOUND_POS_X, CLOSE_SOUND_POS_Y,
                    SOUND_ICON_WIDTH, SOUND_ICON_HEIGHT);
        } else {
            game.spriteBatch.draw(openSoundSprite, OPEN_SOUND_POS_X, OPEN_SOUND_POS_Y,
                    SOUND_ICON_WIDTH, SOUND_ICON_HEIGHT);
        }
    }

    private void handleTouchEvent() {
        float x = Gdx.input.getX();
        float y = Gdx.input.getY();
        touchButton = new Vector3(x, y, 0);
        highScoreImageSprite.setPosition(HIGH_SCORE_POS_X, HIGH_SCORE_POS_Y);
        battleImageSprite.setPosition(BATTLE_POS_X, BATTLE_POS_Y);
        monsterImageSprite.setPosition(MONSTER_POS_X, MONSTER_POS_Y);
        upGradeImageSprite.setPosition(UPGRADE_POS_X, UPGRADE_POS_Y);
        openMusicSprite.setPosition(OPEN_MUSIC_POS_X, OPEN_MUSIC_POS_Y);
        closeMusicSprite.setPosition(CLOSE_MUSIC_POS_X, CLOSE_MUSIC_POS_Y);
        openSoundSprite.setPosition(OPEN_SOUND_POS_X, OPEN_SOUND_POS_Y);
        closeSoundSprite.setPosition(CLOSE_SOUND_POS_X, CLOSE_SOUND_POS_Y);
        facebookImageSprite.setPosition(FACEBOOK_POS_X, FACEBOOK_POS_Y);
        game.camera.unproject(touchButton);
        if (Gdx.input.isTouched() && elapsedTime > 0.5) {
            elapsedTime = 0;
            if (onClickHighScore()) {
                // show high score here !!
                game.playServices.showScore();
            } else if (onClickBattle()) {
                if (GdxPreference.getSoundSetting()) {
                    SoundManager.playToBattleSound();
                }
                manageMusicMainBackground.backgroundMusicStop();
                game.setScreen(new PlayStateScreen(game, player));
            } else if (onClickMonsterInfo()) {
                game.setScreen(new MonsterInfoScreen(game));
            } else if (onClickUpgrade()) {
                game.setScreen(new UpGradeScreen(game, player));
            } else if (onClickFacebook()) {
//                game.setScreen(new TitleScreen(game));
                if (!game.firebaseAuthentication.isSignInGoogle()) {
                    if (game.playServices.isSignedIn()) {
                        Gdx.app.log(TAG, "sign in google");
                        game.firebaseAuthentication.signInGoogle();
                    }
                } else {
                    if (!game.playServices.isSignedIn()) {
                        Gdx.app.log(TAG, "sigh out google");
                        game.firebaseAuthentication.signOutGoogle();
                    }
                }
            } else if (onClickMusicSetting()) {
                manageMusicMainBackground.setSwitchMusic();
            } else if (onClickSoundSetting()) {
                boolean isOn = GdxPreference.getSoundSetting();
                isOn = !isOn;
                GdxPreference.putSoundSetting(isOn);
                GdxPreference.flushPreferences();
            }
        }
    }

    private boolean onClickSoundSetting() {
        return touchButton.x > SWITCH_SOUND_POS_X
                && touchButton.x < (SWITCH_SOUND_POS_X + openSoundSprite.getWidth())
                && touchButton.y > SWITCH_SOUND_POS_Y
                && touchButton.y < (SWITCH_SOUND_POS_Y + openSoundSprite.getHeight());
    }

    private boolean onClickMusicSetting() {
        return touchButton.x > SWITCH_MUSIC_POS_X
                && touchButton.x < (SWITCH_MUSIC_POS_X + closeMusicSprite.getWidth())
                && touchButton.y > SWITCH_MUSIC_POS_Y
                && touchButton.y < (SWITCH_MUSIC_POS_Y + closeMusicSprite.getHeight());
    }

    private boolean onClickFacebook() {
        return touchButton.x > facebookImageSprite.getX()
                && touchButton.x < (facebookImageSprite.getX()
                + facebookImageSprite.getWidth())
                && touchButton.y > facebookImageSprite.getY()
                && touchButton.y < (facebookImageSprite.getY()
                + facebookImageSprite.getHeight());
    }

    private boolean onClickUpgrade() {
        return touchButton.x > upGradeImageSprite.getX()
                && touchButton.x < (upGradeImageSprite.getX() + upGradeImageSprite.getWidth())
                && touchButton.y > upGradeImageSprite.getY()
                && touchButton.y < (upGradeImageSprite.getY()
                + upGradeImageSprite.getHeight());
    }

    private boolean onClickMonsterInfo() {
        return touchButton.x > monsterImageSprite.getX()
                && touchButton.x < (monsterImageSprite.getX() + monsterImageSprite.getWidth())
                && touchButton.y > monsterImageSprite.getY()
                && touchButton.y < (monsterImageSprite.getY()
                + monsterImageSprite.getHeight());
    }

    private boolean onClickBattle() {
        return touchButton.x > battleImageSprite.getX()
                && touchButton.x < (battleImageSprite.getX() + battleImageSprite.getWidth())
                && touchButton.y > battleImageSprite.getY()
                && touchButton.y < (battleImageSprite.getY() + battleImageSprite.getHeight());
    }

    private boolean onClickHighScore() {
        return touchButton.x > highScoreImageSprite.getX()
                && touchButton.x < (highScoreImageSprite.getX() + highScoreImageSprite.getWidth())
                && touchButton.y > highScoreImageSprite.getY()
                && touchButton.y < (highScoreImageSprite.getY() + highScoreImageSprite.getHeight());
    }
}
