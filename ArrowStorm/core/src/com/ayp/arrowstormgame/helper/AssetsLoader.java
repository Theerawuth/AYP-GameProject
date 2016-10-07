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


    // PlatStateScreen
    public static Texture arrowImageTexture;
    public static Sprite arrowImageSprite;

    // TitleScreen
    public static Texture introImageTexture;
    public static Sprite introImageSprite;
    public static Texture touchToStartTexture;
    public static Sprite touchToStartSprite;

    // TitleScreen // set animation leaf
    public static Texture leafImageTexture;
    public static Animation leafAnimation;
    public static TextureRegion[] leafFrames;

    // MainMenuScreen
    public static Texture mainMenuImageTexture;
    public static Sprite mainMenuImageSprite;
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


    public static void load() {
        // PlatStateScreen
        arrowImageTexture = new Texture(Gdx.files.internal("Arrows.png"));
        arrowImageSprite = new Sprite(arrowImageTexture);
        arrowImageSprite.flip(false, true);

        // TitleScreen
        introImageTexture = new Texture(Gdx.files.internal("background/intro_title_background.png"));
        introImageSprite = new Sprite(introImageTexture);
        introImageSprite.flip(false, true);

        touchToStartTexture = new Texture(Gdx.files.internal("start_game_title.png"));
        touchToStartSprite = new Sprite(touchToStartTexture);
        touchToStartSprite.flip(false, true);


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

        // MainMenuScreen
        mainMenuImageTexture = new Texture(Gdx.files.internal("background/main_menu_background.png"));
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
    }


    public static void dispose() {
        // PlatStateScreen
        arrowImageTexture.dispose();

        // TitleScreen
        introImageTexture.dispose();
        touchToStartTexture.dispose();
        leafImageTexture.dispose();

        // MainMenuScreen
        mainMenuImageTexture.dispose();
        highScoreImageTexture.dispose();
        upGradeImageTexture.dispose();
        battleImageTexture.dispose();
        monsterImageTexture.dispose();
        facebookImageTexture.dispose();

    }
}
