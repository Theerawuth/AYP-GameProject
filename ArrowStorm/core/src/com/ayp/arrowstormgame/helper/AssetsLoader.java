package com.ayp.arrowstormgame.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Tanaphon on 9/28/2016.
 */

public class AssetsLoader {
    // TODO : load all assets here !

    // PlatStateScreen
    public static Texture arrowImageTexture;
    public static Sprite arrowImageSprite;

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
        // PlatStateScreen
        arrowImageTexture = new Texture(Gdx.files.internal("Arrows.png"));
        arrowImageSprite = new Sprite(arrowImageTexture);
        arrowImageSprite.flip(false, true);

        // TitleScreen
        introImageTexture = new Texture(Gdx.files.internal("startgame.png"));
        introImageSprite = new Sprite(introImageTexture);
        introImageSprite.flip(false, true);

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

        // MainMenuScreen
        startImageTexture.dispose();

        // SelectStageScreen
        battleImageTexture.dispose();
        stageImageTexture.dispose();
    }
}
