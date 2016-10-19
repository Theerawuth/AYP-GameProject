package com.ayp.arrowstormgame.game_manager;

import com.ayp.arrowstormgame.ArrowStormGame;
import com.ayp.arrowstormgame.helper.AssetsLoader;
import com.ayp.arrowstormgame.model.Arrow;
import com.ayp.arrowstormgame.model.Enemy;
import com.ayp.arrowstormgame.model.Player;
import com.ayp.arrowstormgame.model.enemiespack.Bug;
import com.ayp.arrowstormgame.model.enemiespack.Guardian;
import com.ayp.arrowstormgame.model.enemiespack.Worm;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

/**
 * Created by Tanaphon on 10/10/2016.
 */

public class GamePlayRenderer {

    final private ArrowStormGame game;
    private Sprite arrowSprite;
    private Sprite goldIconSprite;
    private static final int NORMAL_SCALE_ARROW = 1;
    private BitmapFont scoreFont;
    private BitmapFont scoreShadow;
    private BitmapFont goldFont;
    private BitmapFont goldShadow;
    private String score;
    private String gold;
    private GlyphLayout glyphLayout;
    private HashMap<String, Animation> enemyAnimationMap;
    private TextureRegion backgroundStageOne;
    private TextureRegion backgroundStageTwo;
    private TextureRegion backgroundStageThree;
    private Animation playerAnimation;
    private TextureRegion playerStandBy;
    private Vector3 touchPosition;


    public GamePlayRenderer(final ArrowStormGame game) {
        this.game = game;
        arrowSprite = AssetsLoader.arrowImageSprite;
        goldIconSprite = AssetsLoader.goldIconSprite;
        scoreFont = AssetsLoader.scoreFont;
        scoreShadow = AssetsLoader.scoreShadow;
        goldFont = AssetsLoader.goldFont;
        goldShadow = AssetsLoader.goldShadow;
        glyphLayout = new GlyphLayout();
        enemyAnimationMap = AssetsLoader.enemyAnimationMap;
        backgroundStageOne = AssetsLoader.playStateBackgroundOne;
        backgroundStageTwo = AssetsLoader.playStateBackgroundTwo;
        backgroundStageThree = AssetsLoader.playStateBackgroundThree;
        playerAnimation = AssetsLoader.playerAnimation;
        playerAnimation.setFrameDuration(Player.attackSpeed / 2000000000f);
        playerStandBy = AssetsLoader.playerStandBy;
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

    public void drawEnemy(Array<Enemy> enemies, float runtime) {
        for (Enemy enemy : enemies) {
            if (enemy instanceof Bug) {
                game.spriteBatch.draw(
                        enemyAnimationMap
                                .get(AssetsLoader.BUG_ANIMATION)
                                .getKeyFrame(runtime, true),
                        enemy.getPosition().x,
                        enemy.getPosition().y,
                        Enemy.ENEMY_WIDTH,
                        Enemy.ENEMY_HEIGHT
                );
            } else if (enemy instanceof Worm) {
                game.spriteBatch.draw(
                        enemyAnimationMap
                                .get(AssetsLoader.WORM_ANIMATION)
                                .getKeyFrame(runtime, true),
                        enemy.getPosition().x,
                        enemy.getPosition().y,
                        Enemy.ENEMY_WIDTH,
                        Enemy.ENEMY_HEIGHT
                );
            } else if (enemy instanceof Guardian) {
                game.spriteBatch.draw(
                        enemyAnimationMap
                                .get(AssetsLoader.GUARDIAN_ANIMATION)
                                .getKeyFrame(runtime, true),
                        enemy.getPosition().x,
                        enemy.getPosition().y,
                        Enemy.ENEMY_WIDTH,
                        Enemy.ENEMY_HEIGHT
                );
            }
        }
    }

    public void drawBackgroundPlayer() {
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

    public void drawGold(GamePlayManager gamePlayManager) {
        gold = gamePlayManager.getGold();
        game.spriteBatch.draw(
                goldIconSprite,
                10,
                40,
                32,
                32
        );
        goldShadow.draw(
                game.spriteBatch,
                gold,
                48 + 2,
                48 + 2
        );
        goldFont.draw(
                game.spriteBatch,
                gold,
                48,
                48
        );
    }

    public void drawScore(GamePlayManager gamePlayManager) {
        score = gamePlayManager.getScore();
        glyphLayout.setText(scoreFont, score);
        scoreShadow.draw(
                game.spriteBatch,
                score,
                ((game.GAME_WIDTH / 2) + 2) + glyphLayout.height / 2,
                (game.GAME_HEIGHT / 10) + 2
        );
        scoreFont.draw(
                game.spriteBatch,
                score,
                (game.GAME_WIDTH / 2) + glyphLayout.height / 2,
                game.GAME_HEIGHT / 10
        );
    }

    public void drawBackground() {
        game.spriteBatch.draw(backgroundStageOne, 0, 0);
    }

    public void drawPlayer(float runtime) {
        if (Gdx.input.isTouched()) {
            touchPosition = new Vector3();
            touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(touchPosition);
            if (touchPosition.y < Player.SHOOTING_POINT_Y) {
                game.spriteBatch.draw(
                        playerStandBy,
                        Player.SHOOTING_POINT_X - 48,
                        Player.POSITION_Y,
                        Player.PLAYER_WIDTH / 2,
                        Player.PLAYER_HEIGHT / 2,
                        Player.PLAYER_WIDTH,
                        Player.PLAYER_HEIGHT,
                        Player.SCALE_X,
                        Player.SCALE_Y,
                        Player.angle
                );
                return;
            }
            game.spriteBatch.draw(
                    playerAnimation.getKeyFrame(runtime, true),
                    Player.SHOOTING_POINT_X - 48,
                    Player.POSITION_Y,
                    Player.PLAYER_WIDTH / 2,
                    Player.PLAYER_HEIGHT / 2,
                    Player.PLAYER_WIDTH,
                    Player.PLAYER_HEIGHT,
                    Player.SCALE_X,
                    Player.SCALE_Y,
                    Player.angle
            );
        } else {
            game.spriteBatch.draw(
                    playerStandBy,
                    Player.SHOOTING_POINT_X - 48,
                    Player.POSITION_Y,
                    Player.PLAYER_WIDTH / 2,
                    Player.PLAYER_HEIGHT / 2,
                    Player.PLAYER_WIDTH,
                    Player.PLAYER_HEIGHT,
                    Player.SCALE_X,
                    Player.SCALE_Y,
                    Player.angle
            );
        }
    }
}