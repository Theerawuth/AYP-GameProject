package com.ayp.arrowstormgame.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.HashMap;

/**
 * Created by Tanaphon on 9/28/2016.
 */

public class AssetsLoader {
    // TODO : load all assets here !

    // PlatStateScreen
    public static Texture arrowImageTexture;
    public static Sprite arrowImageSprite;
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

    // MainMenuScreen
    public static Texture startImageTexture;
    public static Sprite startImageSprite;

    // SelectStageScreen
    public static Texture battleImageTexture;
    public static Texture stageImageTexture;
    public static Sprite battleImageSprite;
    public static Sprite stageImageSprite;


    public static void load() {
        enemiesSprite = new HashMap<String, Sprite>();

        // PlayStateScreen
        loadAssetsPlayStateScreen();

        // TitleScreen
        loadAssetsTitleScreen();

        // MainMenuScreen
        loadAssetsMainMenuScreen();

        // SelectStageScreen
        loadAssetsSelectStageScreen();
    }

    public static void dispose() {
        // PlatStateScreen
        disposeAssetsPlayStateScreen();

        // TitleScreen
        disposeAssetsTitleScreen();

        // MainMenuScreen
        disposeAssetsMainMenuScreen();

        // SelectStageScreen
        disposeAssetsSelectStageScreen();
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
    }

    private static void disposeAssetsPlayStateScreen(){
        arrowImageTexture.dispose();
        boarTexture.dispose();
        tigerTexture.dispose();
        enemiesSprite.clear();
    }

    private static void loadAssetsTitleScreen() {
        introImageTexture = new Texture(Gdx.files.internal("startgame.png"));
        introImageSprite = new Sprite(introImageTexture);
        introImageSprite.flip(false, true);
    }

    private static void disposeAssetsTitleScreen(){
        introImageTexture.dispose();
    }

    private static void loadAssetsMainMenuScreen() {
        startImageTexture = new Texture(Gdx.files.internal("start_button.png"));
        startImageSprite = new Sprite(startImageTexture);
        startImageSprite.flip(false, true);
    }

    private static void disposeAssetsMainMenuScreen(){
        startImageTexture.dispose();
    }

    private static void loadAssetsSelectStageScreen() {
        battleImageTexture = new Texture(Gdx.files.internal("battle_button.png"));
        battleImageSprite = new Sprite(battleImageTexture);
        battleImageSprite.flip(false, true);
        stageImageTexture = new Texture(Gdx.files.internal("stage_button.png"));
        stageImageSprite = new Sprite(stageImageTexture);
        stageImageSprite.flip(false, true);
    }

    private static void disposeAssetsSelectStageScreen(){
        battleImageTexture.dispose();
        stageImageTexture.dispose();
    }
}