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
    private static final float FONT_SCALE_X = 0.6f;
    private static final float FONT_SCALE_Y = -0.6f;

    // PlayStateScreen
    public static Texture arrowImageTexture;
    public static Sprite arrowImageSprite;
    public static BitmapFont font;
    public static BitmapFont shadow;

    // // Enemy
    public static HashMap<String, Sprite> enemiesSprite;
    public static String BOAR_SPRITE = "Boar";
    public static Texture boarTexture;
    public static Sprite boarSprite;
    public static String TIGER_SPRITE = "Tiger";
    public static Texture tigerTexture;
    public static Sprite tigerSprite;

    // TitleScreen
    public static Texture introImageTexture;
    public static Sprite introImageSprite;
    public static Texture touchToStartTexture;
    public static Sprite touchToStartSprite;
    public static Music introMusic;

    // TitleScreen // set animation leaf
    public static Texture leafImageTexture;
    public static Animation leafAnimation;
    public static TextureRegion[] leafFrames;

    // MainMenuScreen
    public static Texture mainMenuImageTexture;
    public static Sprite mainMenuImageSprite;
    public static Music mainMenuMusic;

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

        introMusic = Gdx.audio.newMusic(Gdx.files.internal("music/intro_bg_music.mp3"));

        // TitleScreen // set animation leaf
        leafImageTexture = new Texture(Gdx.files.internal("anim/anim_leaf.png"));
        TextureRegion[][] leafs = TextureRegion.split(
                leafImageTexture,
                leafImageTexture.getWidth() / FRAME_COLS_LEAF,
                leafImageTexture.getHeight() / FRAME_ROWS_LEAF
        );
        leafFrames = new TextureRegion[FRAME_COLS_LEAF * FRAME_ROWS_LEAF];
        int indexLeaf = 0;
        for (int i = 0; i < FRAME_ROWS_LEAF; i++) {
            for (int j = 0; j < FRAME_COLS_LEAF; j++) {
                leafFrames[indexLeaf++] = leafs[i][j];
            }
        }
        leafAnimation = new Animation(0.1f, leafFrames);
    }

    private static void disposeAssetsTitleScreen() {
        introImageTexture.dispose();
        touchToStartTexture.dispose();
        leafImageTexture.dispose();
        introMusic.dispose();
    }

    private static void loadAssetsMainMenuScreen() {
        mainMenuMusic = Gdx.audio.newMusic(Gdx.files.internal("music/main_menu_bg_music.mp3"));

        // MainMenuScreen
        mainMenuImageTexture =
                new Texture(Gdx.files.internal("background/main_menu_background.png"));
        mainMenuImageSprite = new Sprite(mainMenuImageTexture);
        mainMenuImageSprite.flip(false, true);

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
        mainMenuMusic.dispose();
    }
}