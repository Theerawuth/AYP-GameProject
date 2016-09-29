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
    private static final int FRAME_COLS = 7;
    private static final int FRAME_ROWS = 1;

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
//    public static TextureRegion leafOne, leafTwo, leafThree, leafFour, leafFive, leafSix, leafSeven;


    // MainMenuScreen
    public static Texture startImageTexture;
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
        introImageTexture = new Texture(Gdx.files.internal("startgame.png"));
        introImageSprite = new Sprite(introImageTexture);
        introImageSprite.flip(false, true);

        // TitleScreen // set animation leaf
        leafImageTexture = new Texture(Gdx.files.internal("anim/anim_leaf.png"));
        TextureRegion[][] leafs = TextureRegion.split(
                leafImageTexture,
                leafImageTexture.getWidth()/FRAME_COLS,
                leafImageTexture.getHeight()/FRAME_ROWS);
        leafFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                leafFrames[index++] = leafs[i][j];
            }
        }
        leafAnimation = new Animation(0.05f, leafFrames);


//        leafOne = new TextureRegion(leafImageTexture, 20, 0, 32, 32);
//        leafTwo = new TextureRegion(leafImageTexture, 40, 0, 32, 32);
//        leafThree = new TextureRegion(leafImageTexture, 60, 0, 32, 32);
//        leafFour = new TextureRegion(leafImageTexture, 80, 0, 32, 32);
//        leafFive = new TextureRegion(leafImageTexture, 100, 0, 32, 32);
//        leafSix = new TextureRegion(leafImageTexture, 120, 0, 32, 32);
//        leafSeven = new TextureRegion(leafImageTexture, 140, 0, 32, 32);

//        TextureRegion[] leafs = {leafOne,
//                leafTwo,
//                leafThree,
//                leafFour,
//                leafFive,
//                leafSix,
//                leafSeven};
//
//        leafAnimation = new Animation(0.06f, leafs);
//        leafAnimation.setPlayMode(Animation.PlayMode.LOOP_RANDOM);


        // MainMenuScreen
        startImageTexture = new Texture(Gdx.files.internal("start_button.png"));
        startImageSprite = new Sprite(startImageTexture);
        startImageSprite.flip(false, true);

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
