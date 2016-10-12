package com.ayp.arrowstormgame.game_manager;

import com.ayp.arrowstormgame.ArrowStormGame;
import com.ayp.arrowstormgame.helper.AssetsLoader;
import com.ayp.arrowstormgame.model.Arrow;
import com.ayp.arrowstormgame.model.Enemy;
import com.ayp.arrowstormgame.model.Player;
import com.ayp.arrowstormgame.model.enemiespack.Boar;
import com.ayp.arrowstormgame.model.enemiespack.Tiger;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

/**
 * Created by Tanaphon on 10/10/2016.
 */

public class GamePlayRenderer {

    final private ArrowStormGame game;
    private Sprite arrowSprite;
    private HashMap<String, Sprite> enemySprites;
    private static final int NORMAL_SCALE_ARROW = 1;
    private BitmapFont font;
    private String score;
    private GamePlayManager gamePlayManager;


    public GamePlayRenderer(final ArrowStormGame game) {
        this.game = game;
        arrowSprite = AssetsLoader.arrowImageSprite;
        enemySprites = AssetsLoader.enemiesSprite;
        font = AssetsLoader.font;
    }

    public void drawArrow(Array<Arrow> arrows) {
        for (Arrow arrow : arrows) {
            game.spriteBatch.draw(
                    arrowSprite,
                    arrow.getArrowPosition().x - arrow.getWidth() / 2,
                    arrow.getArrowPosition().y,
                    arrow.getWidth() / 2,
                    arrow.getHeight() / 2,
                    arrow.getWidth(),
                    arrow.getHeight(),
                    NORMAL_SCALE_ARROW,
                    NORMAL_SCALE_ARROW,
                    arrow.getArrowSpriteAngle()
            );
        }
    }

    public void drawEnemy(Array<Enemy> enemies) {
        for (Enemy enemy : enemies) {
            if (enemy instanceof Boar) {
                game.spriteBatch.draw(enemySprites.get("Boar"), enemy.getPosition().x, enemy.getPosition().y);
            } else if (enemy instanceof Tiger) {
                game.spriteBatch.draw(enemySprites.get("Tiger"), enemy.getPosition().x, enemy.getPosition().y);
            }
        }
    }

    public void drawBackground() {
        game.shapeRenderer.setColor(1, 0, 0, 1);
        game.shapeRenderer.rect(
                Player.POSITION_X,
                Player.POSITION_Y,
                Player.PLAYER_WIDTH,
                Player.PLAYER_HEIGHT
        );
        game.shapeRenderer.setColor(1, 1, 0, 1);
        game.shapeRenderer.rect(
                Player.POSITION_X + Player.PLAYER_WIDTH / 2,
                Player.POSITION_Y,
                Player.PLAYER_WIDTH / 2,
                Player.PLAYER_HEIGHT
        );
    }

    public void drawScore(GamePlayManager gamePlayManager) {
        score = gamePlayManager.getScore();
        font.draw(game.spriteBatch, score, game.GAME_WIDTH /2, game.GAME_HEIGHT / 8);
    }

}