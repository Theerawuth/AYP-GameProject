package com.ayp.arrowstormgame.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

/**
 * Created by Tanaphon on 9/28/2016.
 */

public class AssetsLoader {
    private static final int FRAME_COLS_LEAF = 7;
    private static final int FRAME_ROWS_LEAF = 1;
    private static final int FRAME_COLS_BUG = 8;
    private static final int FRAME_ROWS_BUG = 1;
    private static final int FRAME_COLS_BOSS_GOLDEN_BUG = 8;
    private static final int FRAME_ROWS_BOSS_GOLDEN_BUG = 1;
    private static final int FRAME_COLS_WORM = 8;
    private static final int FRAME_ROWS_WORM = 1;
    private static final int FRAME_COLS_BOSS_SCORPION = 8;
    private static final int FRAME_ROWS_BOSS_SCORPION = 1;
    private static final int FRAME_COLS_GUARDIAN = 5;
    private static final int FRAME_ROWS_GUARDIAN = 1;
    private static final int FRAME_COLS_BOSS_KRAKEN = 5;
    private static final int FRAME_ROWS_BOSS_KRAKEN = 1;

    private static final String LEAF_IMAGE_FILE_PATH = "anim/anim_leaf.png";
    private static final String ENEMY_BUG_IMAGE_FILE_PATH = "enemies_pack/enemy_bug.png";
    private static final String ENEMY_BOSS_GOLDEN_BUG_IMAGE_FILE_PATH
            = "enemies_pack/enemy_boss_golden_bug.png";
    private static final String ENEMY_WORM_IMAGE_FILE_PATH
            = "enemies_pack/enemy_worm.png";
    private static final String ENEMY_BOSS_SCORPION_FILE_PATH
            = "enemies_pack/enemy_boss_scorpion.png";
    private static final String ENEMY_GUARDIAN_IMAGE_FILE_PATH
            = "enemies_pack/enemy_guardian.png";
    private static final String ENEMY_BOSS_KRAKEN_FILE_PATH
            = "enemies_pack/enemy_boss_kraken.png";

    private static final float FONT_SCALE_X = 0.6f;
    private static final float FONT_SCALE_Y = -0.6f;

    // PlayStateScreen
    public static Texture arrowImageTexture;
    public static Sprite arrowImageSprite;
    public static BitmapFont font;
    public static BitmapFont shadow;
    public static Music playStateBackgroundMusic;
    public static Music bossEnemyMusic;
    public static Music gameOverMusic;
    public static Music monsterDeadMusic;
    public static Music shootingMusic;

    // // Enemy
    public static HashMap<String, Sprite> enemiesSprite;
    public static String BOAR_SPRITE = "Boar";
    public static Texture boarTexture;
    public static Sprite boarSprite;
    public static String TIGER_SPRITE = "Tiger";
    public static Texture tigerTexture;
    public static Sprite tigerSprite;

    // // Enemy animation in stage one  
    public static Texture enemyBugTexture;
    public static Animation enemyBugAnimation;
    public static TextureRegion[] enemyBugFrames;
    public static Texture bossGoldenBugTexture;
    public static Animation bossGoldenBugAnimation;
    public static TextureRegion[] bossGoldenBugFrames;
    // // Enemy animation in stage two
    public static Texture enemyWormTexture;
    public static Animation enemyWormAnimation;
    public static TextureRegion[] enemyWormFrames;
    public static Texture bossScorpionTexture;
    public static Animation bossScorpionAnimation;
    public static TextureRegion[] bossScorpionFrames;
    // // Enemy animation in last stage
    public static Texture enemyGuardianTexture;
    public static Animation enemyGuardianAnimation;
    public static TextureRegion[] enemyGuardianFrames;
    public static Texture bossKrakenTexture;
    public static Animation bossKrakenAnimation;
    public static TextureRegion[] bossKrakenFrames;

    // TitleScreen
    public static Texture introImageTexture;
    public static Sprite introImageSprite;
    public static Texture touchToStartTexture;
    public static Sprite touchToStartSprite;
    public static Music introBackgroundMusic;

    // TitleScreen // set animation leaf
    public static Texture leafImageTexture;
    public static Animation leafAnimation;
    public static TextureRegion[] leafFrames;

    // MainMenuScreen
    public static Texture mainMenuImageTexture;
    public static Sprite mainMenuImageSprite;
    public static Music mainMenuBackgroundMusic;
    public static Music toBattleMusic;

    // MainMenuScreen // set icon
    public static Texture highScoreImageTexture;
    public static Sprite highScoreImageSprite;
    public static Texture upGradeImageTexture;
    public static Sprite upGradeImageSprite;
    public static Texture battleImageTexture;
    public static Sprite battleImageSprite;
    public static Texture monsterImageTexture;
    public static Sprite monsterImageSprite;
    public static Texture facebookImageTexture;
    public static Sprite facebookImageSprite;
    public static Texture openMusicTexture;
    public static Sprite openMusicSprite;
    public static Texture closeMusicTexture;
    public static Sprite closeMusicSprite;

    public static void load() {
        enemiesSprite = new HashMap<String, Sprite>();
        // PlayStateScreen
        loadAssetsPlayStateScreen();

        // TitleScreen
        loadAssetsTitleScreen();

        // MainMenuScreen
        loadAssetsMainMenuScreen();
    }

    public static void dispose() {
        // PlatStateScreen
        disposeAssetsPlayStateScreen();

        // TitleScreen
        disposeAssetsTitleScreen();

        // MainMenuScreen
        disposeAssetsMainMenuScreen();
    }

    private static void loadAssetsPlayStateScreen() {
        arrowImageTexture = new Texture(Gdx.files.internal("Arrows.png"));
        arrowImageSprite = new Sprite(arrowImageTexture);
        arrowImageSprite.flip(false, true);

        // Enemies
        boarTexture = new Texture(Gdx.files.internal("enemies_pack/Boar.png"));
        boarSprite = new Sprite(boarTexture);
        boarSprite.flip(false, true);

        tigerTexture = new Texture(Gdx.files.internal("enemies_pack/Tiger.png"));
        tigerSprite = new Sprite(tigerTexture);
        tigerSprite.flip(false, true);

        playStateBackgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/play_state_bg_sound.mp3"));
        monsterDeadMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/monster_dead_sound.mp3"));
        shootingMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/shooting_sound.mp3"));
        bossEnemyMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/boss_enemy_sound.mp3"));
        gameOverMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/game_over_sound.mp3"));

        // Enemy Stage One
        enemyBugAnimation = AnimationUtils.newAnimation(
                ENEMY_BUG_IMAGE_FILE_PATH,
                enemyBugTexture,
                enemyBugFrames,
                FRAME_COLS_BUG,
                FRAME_ROWS_BUG,
                0.25f
        );
        bossGoldenBugAnimation = AnimationUtils.newAnimation(
                ENEMY_BOSS_GOLDEN_BUG_IMAGE_FILE_PATH,
                bossGoldenBugTexture,
                bossGoldenBugFrames,
                FRAME_COLS_BOSS_GOLDEN_BUG,
                FRAME_ROWS_BOSS_GOLDEN_BUG,
                0.25f
        );
        // Enemy Stage Two
        enemyWormAnimation = AnimationUtils.newAnimation(
                ENEMY_WORM_IMAGE_FILE_PATH,
                enemyWormTexture,
                enemyWormFrames,
                FRAME_COLS_WORM,
                FRAME_ROWS_WORM,
                0.25f
        );
        bossScorpionAnimation = AnimationUtils.newAnimation(
                ENEMY_BOSS_SCORPION_FILE_PATH,
                bossScorpionTexture,
                bossScorpionFrames,
                FRAME_COLS_BOSS_SCORPION,
                FRAME_ROWS_BOSS_SCORPION,
                0.25f
        );
        // Enemy Last Stage
        enemyGuardianAnimation = AnimationUtils.newAnimation(
                ENEMY_GUARDIAN_IMAGE_FILE_PATH,
                enemyGuardianTexture,
                enemyGuardianFrames,
                FRAME_COLS_GUARDIAN,
                FRAME_ROWS_GUARDIAN,
                0.25f
        );
        bossKrakenAnimation = AnimationUtils.newAnimation(
                ENEMY_BOSS_KRAKEN_FILE_PATH,
                bossKrakenTexture,
                bossKrakenFrames,
                FRAME_COLS_BOSS_KRAKEN,
                FRAME_ROWS_BOSS_KRAKEN,
                0.25f
        );

        enemiesSprite.put(BOAR_SPRITE, boarSprite);
        enemiesSprite.put(TIGER_SPRITE, tigerSprite);


        font = new BitmapFont(Gdx.files.internal("font/text.fnt"));
        font.getData().setScale(FONT_SCALE_X, FONT_SCALE_Y);

        shadow = new BitmapFont(Gdx.files.internal("font/shadow.fnt"));
        shadow.getData().setScale(FONT_SCALE_X, FONT_SCALE_Y);
    }

    private static void disposeAssetsPlayStateScreen() {
        arrowImageTexture.dispose();
        boarTexture.dispose();
        tigerTexture.dispose();
        enemiesSprite.clear();
        font.dispose();
        monsterDeadMusic.dispose();
        bossEnemyMusic.dispose();
        gameOverMusic.dispose();
        shootingMusic.dispose();
        playStateBackgroundMusic.dispose();
//        enemyBugTexture.dispose();
//        bossGoldenBugTexture.dispose();
//        enemyWormTexture.dispose();
//        bossScorpionTexture.dispose();
//        enemyGuardianTexture.dispose();
//        bossKrakenTexture.dispose();
    }

    private static void loadAssetsTitleScreen() {
        // TitleScreen
        introImageTexture =
                new Texture(Gdx.files.internal("background/intro_title_background.png"));
        introImageSprite = new Sprite(introImageTexture);
        introImageSprite.flip(false, true);

        touchToStartTexture = new Texture(Gdx.files.internal("start_game_title.png"));
        touchToStartSprite = new Sprite(touchToStartTexture);
        touchToStartSprite.flip(false, true);

        introBackgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/intro_game_bg_sound.mp3"));

        // TitleScreen // set animation leaf
        leafAnimation = AnimationUtils.newAnimation(
                LEAF_IMAGE_FILE_PATH,
                leafImageTexture,
                leafFrames,
                FRAME_COLS_LEAF,
                FRAME_ROWS_LEAF,
                0.1f
        );
    }

    private static void disposeAssetsTitleScreen() {
        introImageTexture.dispose();
        touchToStartTexture.dispose();
        leafImageTexture.dispose();
        introBackgroundMusic.dispose();
    }

    private static void loadAssetsMainMenuScreen() {

        // MainMenuScreen
        mainMenuImageTexture =
                new Texture(Gdx.files.internal("background/main_menu_background.png"));
        mainMenuImageSprite = new Sprite(mainMenuImageTexture);
        mainMenuImageSprite.flip(false, true);

        mainMenuBackgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/main_menu_bg_sound.mp3"));
        toBattleMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/to_battle_sound.mp3"));

        //MainMenuScreen // set icon
        highScoreImageTexture = new Texture(Gdx.files.internal("icon/high_score_icon.png"));
        highScoreImageSprite = new Sprite(highScoreImageTexture);
        highScoreImageSprite.flip(false, true);

        upGradeImageTexture = new Texture(Gdx.files.internal("icon/upgrade_icon.png"));
        upGradeImageSprite = new Sprite(upGradeImageTexture);
        upGradeImageSprite.flip(false, true);

        battleImageTexture = new Texture(Gdx.files.internal("icon/battle_icon.png"));
        battleImageSprite = new Sprite(battleImageTexture);
        battleImageSprite.flip(false, true);

        monsterImageTexture = new Texture(Gdx.files.internal("icon/monster_icon.png"));
        monsterImageSprite = new Sprite(monsterImageTexture);
        monsterImageSprite.flip(false, true);

        facebookImageTexture = new Texture(Gdx.files.internal("icon/facebook_icon.png"));
        facebookImageSprite = new Sprite(facebookImageTexture);
        facebookImageSprite.flip(false, true);

        openMusicTexture = new Texture(Gdx.files.internal("icon/open_music_icon.png"));
        openMusicSprite = new Sprite(openMusicTexture);
        openMusicSprite.flip(false, true);

        closeMusicTexture = new Texture(Gdx.files.internal("icon/close_music_icon.png"));
        closeMusicSprite = new Sprite(closeMusicTexture);
        closeMusicSprite.flip(false, true);
    }

    private static void disposeAssetsMainMenuScreen() {
        // MainMenuScreen
        mainMenuImageTexture.dispose();
        highScoreImageTexture.dispose();
        upGradeImageTexture.dispose();
        battleImageTexture.dispose();
        monsterImageTexture.dispose();
        facebookImageTexture.dispose();
        mainMenuBackgroundMusic.dispose();
    }

}