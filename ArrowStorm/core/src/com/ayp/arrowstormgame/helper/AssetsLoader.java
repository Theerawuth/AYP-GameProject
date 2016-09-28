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

    // MainMenuScreen
    public static Texture startImageTexture;
    public static Sprite startImageSprite;

    public static void load() {
        // PlatStateScreen
        arrowImageTexture = new Texture(Gdx.files.internal("Arrows.png"));
        arrowImageSprite = new Sprite(arrowImageTexture);
        arrowImageSprite.flip(false, true);

        // TitleScreen
        introImageTexture = new Texture(Gdx.files.internal("startgame.png"));

        // MainMenuScreen
        startImageTexture = new Texture(Gdx.files.internal("start_button.png"));
        startImageSprite = new Sprite(startImageTexture);
        startImageSprite.flip(false, true);

    }

    public static void dispose() {
        arrowImageTexture.dispose();
        introImageTexture.dispose();
    }
}
