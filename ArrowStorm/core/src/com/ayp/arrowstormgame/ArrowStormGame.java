package com.ayp.arrowstormgame;

import com.ayp.arrowstormgame.helper.AssetsLoader;
import com.ayp.arrowstormgame.interfaces.FirebaseAuthentication;
import com.ayp.arrowstormgame.interfaces.PlayServices;
import com.ayp.arrowstormgame.helper.SoundManager;
import com.ayp.arrowstormgame.screen.TitleScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class ArrowStormGame extends Game {
    public static final float GAME_WIDTH = 480;
    public static final float GAME_HEIGHT = 800;
    public SpriteBatch spriteBatch;
    public OrthographicCamera camera;
    public ShapeRenderer shapeRenderer;
    public static PlayServices playServices;
    public static FirebaseAuthentication firebaseAuthentication;

    public ArrowStormGame(PlayServices playServices,
                          FirebaseAuthentication firebaseAuthentication) {
        this.playServices = playServices;
        this.firebaseAuthentication = firebaseAuthentication;
    }

    @Override
    public void create() {
        AssetsLoader.load();
        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(true, ArrowStormGame.GAME_WIDTH, ArrowStormGame.GAME_HEIGHT);
        new SoundManager();
        spriteBatch.setProjectionMatrix(camera.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
        setScreen(new TitleScreen(this));
    }

    @Override
    public void render() {
        super.render();
        camera.update();
    }

    @Override
    public void dispose() {
        AssetsLoader.dispose();
    }
}
