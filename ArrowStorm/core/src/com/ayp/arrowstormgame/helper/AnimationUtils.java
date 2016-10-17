package com.ayp.arrowstormgame.helper;

import com.ayp.arrowstormgame.ArrowStormGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Theerawuth on 10/17/2016.
 */

public class AnimationUtils {

    public static Animation newAnimation(
            String pathNameString,
            Texture imageTexture,
            TextureRegion[] perFramesAnimation,
            int frameColumns,
            int frameRows,
            float frameDuration
    ){
        imageTexture = new Texture(Gdx.files.internal(pathNameString));
        TextureRegion[][] leafs = TextureRegion.split(
                imageTexture,
                imageTexture.getWidth() / frameColumns,
                imageTexture.getHeight() / frameRows
        );
        perFramesAnimation = new TextureRegion[frameColumns * frameRows];
        int indexLeaf = 0;
        for (int i = 0; i < frameRows; i++) {
            for (int j = 0; j < frameColumns; j++) {
                perFramesAnimation[indexLeaf++] = leafs[i][j];
            }
        }

        return new Animation(frameDuration, perFramesAnimation);
    }
}
