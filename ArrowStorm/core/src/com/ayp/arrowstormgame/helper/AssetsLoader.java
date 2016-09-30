package com.ayp.arrowstormgame.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Tanaphon on 9/28/2016.
 */

public class AssetsLoader {
    // TODO : load all assets here !
    private static final int FRAME_COLS_LEAF = 7;
    private static final int FRAME_ROWS_LEAF = 1;
    private static final int FRAME_COLS_TITLE = 1;
    private static final int FRAME_ROWS_TITLE = 9;

    // PlatStateScreen
    public static Texture arrowImageTexture;
    public static Sprite arrowImageSprite;

    // TitleScreen
    public static Texture introImageTexture;
    public static Sprite introImageSprite;

    // TitleScreen // set animation leaf
    public static Texture leafImageTexture;
    public static Animation leafAnimation;
    public static TextureRegion[] leafFrames;

    // TitleScreen // set animation title
    public static Texture titleImageTexture;
    public static Animation titleAnimation;
    public static TextureRegion[] titleFrames;


    // MainMenuScreen
    public static Texture mainMenuImageTexture;
    public static Texture startImageTexture;
    public static Sprite mainMenuImageSprite;
    public static Sprite startImageSprite;

    // SelectStageScreen
    public static Texture battleImageTexture;
    public static Texture stageImageTexture;
    public static Sprite battleImageSprite;
    public static Sprite stageImageSprite;


    public static void load() {
        // PlatStateScreen
        arrowImageTexture = new Texture(Gdx.files.internal("Arrows.png"));
        arrowImageSprite = new Sprite(arrowImageTexture);
        arrowImageSprite.flip(false, true);

        // TitleScreen
        introImageTexture = new Texture(Gdx.files.internal("intro_background.png"));
        introImageSprite = new Sprite(introImageTexture);
        introImageSprite.flip(false, true);

        // TitleScreen // set animation leaf
        leafImageTexture = new Texture(Gdx.files.internal("anim/anim_leaf.png"));
        TextureRegion[][] leafs = TextureRegion.split(
                leafImageTexture,
                leafImageTexture.getWidth()/FRAME_COLS_LEAF,
                leafImageTexture.getHeight()/FRAME_ROWS_LEAF
        );
        leafFrames = new TextureRegion[FRAME_COLS_LEAF * FRAME_ROWS_LEAF];
        int indexLeaf = 0;
        for (int i = 0; i < FRAME_ROWS_LEAF; i++) {
            for (int j = 0; j < FRAME_COLS_LEAF; j++) {
                leafFrames[indexLeaf++] = leafs[i][j];
            }
        }
        leafAnimation = new Animation(0.1f, leafFrames);

        // TitleScreen // set animation title
        titleImageTexture = new Texture(Gdx.files.internal("anim/anim_title.png"));
        TextureRegion[][] titles = TextureRegion.split(
                titleImageTexture,
                titleImageTexture.getWidth()/FRAME_COLS_TITLE,
                titleImageTexture.getHeight()/FRAME_ROWS_TITLE
        );
        titleFrames = new TextureRegion[FRAME_COLS_TITLE * FRAME_ROWS_TITLE];
        int indexTitle = 0;
        for (int i = 0; i < FRAME_ROWS_TITLE; i++) {
            for (int j = 0; j < FRAME_COLS_TITLE; j++) {
                titleFrames[indexTitle++] = titles[i][j];
            }
        }
        titleAnimation = new Animation(0.2f, titleFrames);


        // MainMenuScreen
        startImageTexture = new Texture(Gdx.files.internal("start_button.png"));
        startImageSprite = new Sprite(startImageTexture);
        startImageSprite.flip(false, true);
        mainMenuImageTexture = new Texture(Gdx.files.internal("main_menu_background.png"));
        mainMenuImageSprite = new Sprite(mainMenuImageTexture);
        mainMenuImageSprite.flip(false, true);

        // SelectStageScreen
        battleImageTexture= new Texture(Gdx.files.internal("battle_button.png"));
        battleImageSprite = new Sprite(battleImageTexture);
        battleImageSprite.flip(false, true);
        stageImageTexture = new Texture(Gdx.files.internal("stage_button.png"));
        stageImageSprite = new Sprite(stageImageTexture);
        stageImageSprite.flip(false, true);

    }

    public static void dispose() {
        // PlatStateScreen
        arrowImageTexture.dispose();

        // TitleScreen
        introImageTexture.dispose();

        //TitleScreen //set animation leaf
        leafImageTexture.dispose();

        // MainMenuScreen
        startImageTexture.dispose();

        // SelectStageScreen
        battleImageTexture.dispose();
        stageImageTexture.dispose();
    }
}
