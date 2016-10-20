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
    private static final int WIDTH_PLAYER = 96;
    private static final int HEIGHT_PLAYER = 96;
    private static final int PLAY_STATE_BG_WIDTH = 480;
    private static final int PLAY_STATE_BG_HEIGHT = 800;

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

    // Enemy animation key
    public static final String BUG_ANIMATION = "BugAnimation";
    public static final String WORM_ANIMATION = "WormAnimation";
    public static final String GUARDIAN_ANIMATION = "GuardianAnimation";
    // Boss animation key
    public static final String BOSS_GOLDEN_BUG_ANIMATION = "BossGoldenBugAnimation";
    public static final String BOSS_SCORPION_ANIMATION = "BossScorpionAnimation";
    public static final String BOSS_KRAKEN_ANIMATION = "BossKrakenAnimation";

    private static final float FONT_SCALE_X = 0.4f;
    private static final float FONT_SCALE_Y = -0.4f;

    private static final float UPGRAGE_SCREEN_FONT_SCALE_X = 0.5f;
    private static final float UPGRAGE_SCREEN_FONT_SCALE_Y = -0.5f;
    private static final float HIGH_SCORE_FONT_SCALE_X = 1.0f;
    private static final float HIGH_SCORE_FONT_SCALE_Y = -1.0f;


    // PlayStateScreen
    public static Texture arrowImageTexture;
    public static Sprite arrowImageSprite;
    public static BitmapFont font;
    public static BitmapFont shadow;
    public static BitmapFont highScoreFont;
    public static BitmapFont highScoreShadow;
    public static BitmapFont upgradeScreenFont;
    public static BitmapFont upgradeScreenShadow;
    public static BitmapFont scoreFont;
    public static BitmapFont scoreShadow;
    public static BitmapFont goldFont;
    public static BitmapFont goldShadow;
    public static BitmapFont hearthFont;
    public static BitmapFont hearthShadow;
    public static Music playStateBackgroundMusic;
    public static Music bossEnemyMusic;
    public static Music gameOverMusic;
    public static Music monsterDeadMusic;
    public static Music shootingMusic;
    //Background
    public static Texture playStateImageTexture;
    public static TextureRegion playStateBackgroundOne, playStateBackgroundTwo, playStateBackgroundThree;
    //Icon
    public static Texture pauseTexture;
    public static Sprite pauseSprite;
    public static Texture quitTexture;
    public static Sprite quitSprite;
    public static Texture resumeTexture;
    public static Sprite resumeSprite;
    public static Texture goldIconTexture;
    public static Sprite goldIconSprite;
    public static Texture hearthIconTexture;
    public static Sprite hearthIconSprite;
    //Player
    public static Texture playerTexture;
    public static TextureRegion playerStandBy, playerShooting;
    public static Animation playerAnimation;

    // // Enemy
    public static HashMap<String, Animation> enemyAnimationMap;

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

    //MonsterInfoScreen
    public static Texture monsterInfoBackgroundTexture;
    public static Sprite monsterInfoBackgroundSprite;
    public static Texture headerMonsterInfoTexture;
    public static Sprite headerMonsterInfoSprite;
    public static Texture backIconTexture;
    public static Sprite backIconSprite;
    public static Texture forwardIconTexture;
    public static Sprite forwardIconSprite;

    //UpgradeScreen
    public static Texture upGradeBackgroundTexture;
    public static Sprite upGradeBackgroundSprite;
    public static Texture headerUpGradeTexture;
    public static Sprite headerUpGradeSprite;
    //Icon
    public static Texture coinIconTexture;
    public static Sprite coinIconSprite;
    public static Texture atkIconTexture;
    public static Sprite atkIconSprite;
    public static Texture hpIconTexture;
    public static Sprite hpIconSprite;
    public static Texture aspdIconTexture;
    public static Sprite aspdIconSprite;
    public static Texture plusIconTexture;
    public static Sprite plusIconSprite;
    public static Texture coinUpgradeIconTexture;
    public static Sprite coinUpgradeIconSprite;


    public static void load() {
        // PlayStateScreen
        loadAssetsPlayStateScreen();

        // TitleScreen
        loadAssetsTitleScreen();

        // MainMenuScreen
        loadAssetsMainMenuScreen();

        //MonsterInfoScreen
        loadAssetsMonsterInfoScreen();

        //UpGradeScreen
        loadAssetsUpGradeScreen();
    }

    public static void dispose() {
        // PlatStateScreen
        disposeAssetsPlayStateScreen();

        // TitleScreen
        disposeAssetsTitleScreen();

        // MainMenuScreen
        disposeAssetsMainMenuScreen();

        //MonsterInfoScreen
        disposeAssetsMonsterInfoScreen();

        //UpGradeScreen
        disposeAssetsUpGradeScreen();
    }

    private static void loadAssetsPlayStateScreen() {
        arrowImageTexture = new Texture(Gdx.files.internal("Arrows.png"));
        arrowImageSprite = new Sprite(arrowImageTexture);
        arrowImageSprite.flip(false, true);
        //PlayState background
        playStateImageTexture = new Texture(
                Gdx.files.internal("background/play_state_background.png"));
        playStateBackgroundOne = new TextureRegion(playStateImageTexture, 0, 0, PLAY_STATE_BG_WIDTH,
                PLAY_STATE_BG_HEIGHT);
        playStateBackgroundOne.flip(false, true);
        //Icon
        pauseTexture = new Texture(Gdx.files.internal("icon/pause_icon.png"));
        pauseSprite = new Sprite(pauseTexture);
        pauseSprite.flip(false, true);
        quitTexture = new Texture(Gdx.files.internal("icon/quit_icon.png"));
        quitSprite = new Sprite(quitTexture);
        quitSprite.flip(false, true);
        resumeTexture = new Texture(Gdx.files.internal("icon/resume_icon.png"));
        resumeSprite = new Sprite(resumeTexture);
        resumeSprite.flip(false, true);
        goldIconTexture = new Texture(Gdx.files.internal("icon/coin_point_icon.png"));
        goldIconSprite = new Sprite(goldIconTexture);
        goldIconSprite.flip(false, true);
        hearthIconTexture = new Texture(Gdx.files.internal("icon/heart_icon.png"));
        hearthIconSprite = new Sprite(hearthIconTexture);
        hearthIconSprite.flip(false, true);
        //Player
        playerTexture = new Texture(Gdx.files.internal("anim/anim_player.png"));
        playerStandBy = new TextureRegion(playerTexture, 0, 0, WIDTH_PLAYER, HEIGHT_PLAYER);
        playerStandBy.flip(false, true);
        playerShooting = new TextureRegion(playerTexture, 96, 0, WIDTH_PLAYER, HEIGHT_PLAYER);
        playerShooting.flip(false, true);
        //Player Animation
        TextureRegion[] players = {playerStandBy, playerShooting};
        playerAnimation = new Animation(0.5f, players);
        playerAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);


        playStateBackgroundMusic = Gdx.audio.newMusic(
                Gdx.files.internal("sound/play_state_bg_sound.mp3"));
        monsterDeadMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/monster_dead_sound.mp3"));
        shootingMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/shooting_sound.mp3"));
        bossEnemyMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/boss_enemy_sound.mp3"));
        gameOverMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/game_over_sound.mp3"));
        // Enemy Stage One
        enemyBugAnimation = AnimationUtils.newAnimation(ENEMY_BUG_IMAGE_FILE_PATH, enemyBugTexture,
                enemyBugFrames, FRAME_COLS_BUG, FRAME_ROWS_BUG, 0.1f);
        bossGoldenBugAnimation = AnimationUtils.newAnimation(ENEMY_BOSS_GOLDEN_BUG_IMAGE_FILE_PATH,
                bossGoldenBugTexture, bossGoldenBugFrames, FRAME_COLS_BOSS_GOLDEN_BUG,
                FRAME_ROWS_BOSS_GOLDEN_BUG, 0.1f);
        // Enemy Stage Two
        enemyWormAnimation = AnimationUtils.newAnimation(ENEMY_WORM_IMAGE_FILE_PATH,
                enemyWormTexture, enemyWormFrames, FRAME_COLS_WORM, FRAME_ROWS_WORM, 0.1f);
        bossScorpionAnimation = AnimationUtils.newAnimation(ENEMY_BOSS_SCORPION_FILE_PATH,
                bossScorpionTexture, bossScorpionFrames, FRAME_COLS_BOSS_SCORPION,
                FRAME_ROWS_BOSS_SCORPION, 0.1f);
        // Enemy Last Stage
        enemyGuardianAnimation = AnimationUtils.newAnimation(ENEMY_GUARDIAN_IMAGE_FILE_PATH,
                enemyGuardianTexture, enemyGuardianFrames, FRAME_COLS_GUARDIAN, FRAME_ROWS_GUARDIAN,
                0.1f);
        bossKrakenAnimation = AnimationUtils.newAnimation(ENEMY_BOSS_KRAKEN_FILE_PATH,
                bossKrakenTexture, bossKrakenFrames, FRAME_COLS_BOSS_KRAKEN, FRAME_ROWS_BOSS_KRAKEN,
                0.1f);

        // put enemies and bosses animation to map
        enemyAnimationMap = new HashMap<String, Animation>();
        enemyAnimationMap.put(BUG_ANIMATION, enemyBugAnimation);
        enemyAnimationMap.put(WORM_ANIMATION, enemyWormAnimation);
        enemyAnimationMap.put(GUARDIAN_ANIMATION, enemyGuardianAnimation);
        enemyAnimationMap.put(BOSS_GOLDEN_BUG_ANIMATION, bossGoldenBugAnimation);
        enemyAnimationMap.put(BOSS_SCORPION_ANIMATION, bossScorpionAnimation);
        enemyAnimationMap.put(BOSS_KRAKEN_ANIMATION, bossKrakenAnimation);

        font = new BitmapFont(Gdx.files.internal("font/text.fnt"));
        font.getData().setScale(FONT_SCALE_X, FONT_SCALE_Y);

        shadow = new BitmapFont(Gdx.files.internal("font/shadow.fnt"));
        shadow.getData().setScale(FONT_SCALE_X, FONT_SCALE_Y);

        upgradeScreenFont = new BitmapFont(Gdx.files.internal("font/text.fnt"));
        upgradeScreenFont.getData().setScale(UPGRAGE_SCREEN_FONT_SCALE_X,
                UPGRAGE_SCREEN_FONT_SCALE_Y);

        upgradeScreenShadow = new BitmapFont(Gdx.files.internal("font/shadow.fnt"));
        upgradeScreenShadow.getData().setScale(UPGRAGE_SCREEN_FONT_SCALE_X,
                UPGRAGE_SCREEN_FONT_SCALE_Y);

        highScoreFont = new BitmapFont(Gdx.files.internal("font/text.fnt"));
        highScoreFont.getData().setScale(HIGH_SCORE_FONT_SCALE_X, HIGH_SCORE_FONT_SCALE_Y);

        highScoreShadow = new BitmapFont(Gdx.files.internal("font/shadow.fnt"));
        highScoreShadow.getData().setScale(HIGH_SCORE_FONT_SCALE_X, HIGH_SCORE_FONT_SCALE_Y);

        scoreFont = new BitmapFont(Gdx.files.internal("font/text.fnt"));
        scoreFont.getData().setScale(FONT_SCALE_X, FONT_SCALE_Y);

        scoreShadow = new BitmapFont(Gdx.files.internal("font/shadow.fnt"));
        scoreShadow.getData().setScale(FONT_SCALE_X, FONT_SCALE_Y);

        goldFont = new BitmapFont(Gdx.files.internal("font/text.fnt"));
        goldFont.getData().setScale(0.4f, -0.4f);

        goldShadow = new BitmapFont(Gdx.files.internal("font/shadow.fnt"));
        goldShadow.getData().setScale(0.4f, -0.4f);

        hearthFont = new BitmapFont(Gdx.files.internal("font/text.fnt"));
        hearthFont.getData().setScale(0.4f, -0.4f);

        hearthShadow = new BitmapFont(Gdx.files.internal("font/shadow.fnt"));
        hearthShadow.getData().setScale(0.4f, -0.4f);
    }

    private static void disposeAssetsPlayStateScreen() {
        arrowImageTexture.dispose();
        scoreFont.dispose();
        monsterDeadMusic.dispose();
        bossEnemyMusic.dispose();
        gameOverMusic.dispose();
        shootingMusic.dispose();
        playStateBackgroundMusic.dispose();
        enemyBugTexture.dispose();
        bossGoldenBugTexture.dispose();
        enemyWormTexture.dispose();
        bossScorpionTexture.dispose();
        enemyGuardianTexture.dispose();
        bossKrakenTexture.dispose();
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

        introBackgroundMusic = Gdx.audio.newMusic(
                Gdx.files.internal("sound/intro_game_bg_sound.mp3"));

        // TitleScreen // set animation leaf
        leafAnimation = AnimationUtils.newAnimation(LEAF_IMAGE_FILE_PATH, leafImageTexture,
                leafFrames, FRAME_COLS_LEAF, FRAME_ROWS_LEAF, 0.1f);
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

        mainMenuBackgroundMusic = Gdx.audio.newMusic(
                Gdx.files.internal("sound/main_menu_bg_sound.mp3"));
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

    private static void loadAssetsMonsterInfoScreen() {
        monsterInfoBackgroundTexture = new Texture(
                Gdx.files.internal("background/monster_info_background.png"));
        monsterInfoBackgroundSprite = new Sprite(monsterInfoBackgroundTexture);
        monsterInfoBackgroundSprite.flip(false, true);
        headerMonsterInfoTexture = new Texture(Gdx.files.internal("header_text_monster_info.png"));
        headerMonsterInfoSprite = new Sprite(headerMonsterInfoTexture);
        headerMonsterInfoSprite.flip(false, true);
        backIconTexture = new Texture(Gdx.files.internal("icon/back_icon.png"));
        backIconSprite = new Sprite(backIconTexture);
        forwardIconTexture = new Texture(Gdx.files.internal("icon/forward_icon.png"));
        forwardIconSprite = new Sprite(forwardIconTexture);
    }

    private static void disposeAssetsMonsterInfoScreen() {
        monsterInfoBackgroundTexture.dispose();
        headerMonsterInfoTexture.dispose();
        backIconTexture.dispose();
        forwardIconTexture.dispose();
    }

    private static void loadAssetsUpGradeScreen() {
        //background
        upGradeBackgroundTexture = new Texture(Gdx.files.internal("background/upgrade_background.png"));
        upGradeBackgroundSprite = new Sprite(upGradeBackgroundTexture);
        upGradeBackgroundSprite.flip(false, true);
        headerUpGradeTexture = new Texture(Gdx.files.internal("header_text_upgrade.png"));
        headerUpGradeSprite = new Sprite(headerUpGradeTexture);
        headerUpGradeSprite.flip(false, true);
        //icon
        coinIconTexture = new Texture(Gdx.files.internal("icon/coin_icon_demo.png"));
        coinIconSprite = new Sprite(coinIconTexture);
        coinIconSprite.flip(false, true);
        atkIconTexture = new Texture(Gdx.files.internal("icon/atk_icon.png"));
        atkIconSprite = new Sprite(atkIconTexture);
        atkIconSprite.flip(false, true);
        hpIconTexture = new Texture(Gdx.files.internal("icon/hp_icon.png"));
        hpIconSprite = new Sprite(hpIconTexture);
        hpIconSprite.flip(false, true);
        aspdIconTexture = new Texture(Gdx.files.internal("icon/aspd_icon.png"));
        aspdIconSprite = new Sprite(aspdIconTexture);
        aspdIconSprite.flip(false, true);
        plusIconTexture = new Texture(Gdx.files.internal("icon/plus_icon.png"));
        plusIconSprite = new Sprite(plusIconTexture);
        plusIconSprite.flip(false, true);
        coinUpgradeIconTexture = new Texture(Gdx.files.internal("icon/coin_upgrade_icon.png"));
        coinUpgradeIconSprite = new Sprite(coinUpgradeIconTexture);
        coinUpgradeIconSprite.flip(false, true);


    }

    private static void disposeAssetsUpGradeScreen() {
        upGradeBackgroundTexture.dispose();
        headerUpGradeTexture.dispose();
        coinIconTexture.dispose();
        atkIconTexture.dispose();
        hpIconTexture.dispose();
        aspdIconTexture.dispose();
        plusIconTexture.dispose();
        coinUpgradeIconTexture.dispose();
    }

}