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
import com.badlogic.gdx.graphics.GL20;
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
    private static final float RESUME_ICON_POSITION_X = 112;
    private static final float RESUME_ICON_POSITION_Y = 300;
    private static final String GAME_OVER_TEXT = "GAME OVER";
    private static final int NORMAL_SCALE_ARROW = 1;
    private static final float ANIMATION_FRAME_DURATION_DlIVIDER = 2000000000f;
    final private ArrowStormGame game;
    private HashMap<String, Animation> enemyAnimationMap;
    private GlyphLayout glyphLayout;
    private Animation playerAnimation;
    private Vector3 touchPosition;
    private Sprite arrowSprite;
    private Sprite goldIconSprite;
    private Sprite heartIconSprite;
    private Sprite pauseSprite;
    private Sprite resumeSprite;
    private Sprite quitSprite;
    private BitmapFont scoreFont;
    private BitmapFont scoreShadow;
    private BitmapFont goldFont;
    private BitmapFont goldShadow;
    private BitmapFont heartFont;
    private BitmapFont heartShadow;
    private BitmapFont font;
    private String score;
    private String gold;
    private String heart;
    private TextureRegion background;
    private TextureRegion playerStandBy;

    public GamePlayRenderer(final ArrowStormGame game) {
        this.game = game;
        arrowSprite = AssetsLoader.arrowImageSprite;
        goldIconSprite = AssetsLoader.goldIconSprite;
        pauseSprite = AssetsLoader.pauseSprite;
        resumeSprite = AssetsLoader.resumeSprite;
        quitSprite = AssetsLoader.quitSprite;
        scoreFont = AssetsLoader.scoreFont;
        scoreShadow = AssetsLoader.scoreShadow;
        goldFont = AssetsLoader.goldFont;
        goldShadow = AssetsLoader.goldShadow;
        heartFont = AssetsLoader.hearthFont;
        heartShadow = AssetsLoader.hearthShadow;
        glyphLayout = new GlyphLayout();
        enemyAnimationMap = AssetsLoader.enemyAnimationMap;
        background = AssetsLoader.playStateBackgroundOne;
        playerAnimation = AssetsLoader.playerAnimation;
        playerAnimation.setFrameDuration(Player.attackSpeed / ANIMATION_FRAME_DURATION_DlIVIDER);
        playerStandBy = AssetsLoader.playerStandBy;
        heartIconSprite = AssetsLoader.hearthIconSprite;
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

    public void drawEnemy(Array<Enemy> enemies, float runtime) {
        for (Enemy enemy : enemies) {
            if (enemy instanceof Bug) {
                game.spriteBatch.draw(
                        enemyAnimationMap.get(AssetsLoader.BUG_ANIMATION)
                                .getKeyFrame(runtime, true),
                        enemy.getPosition().x, enemy.getPosition().y, Enemy.WIDTH, Enemy.HEIGHT
                );
            } else if (enemy instanceof Worm) {
                game.spriteBatch.draw(
                        enemyAnimationMap.get(AssetsLoader.WORM_ANIMATION)
                                .getKeyFrame(runtime, true),
                        enemy.getPosition().x, enemy.getPosition().y, Enemy.WIDTH, Enemy.HEIGHT
                );
            } else if (enemy instanceof Guardian) {
                game.spriteBatch.draw(
                        enemyAnimationMap.get(AssetsLoader.GUARDIAN_ANIMATION)
                                .getKeyFrame(runtime, true),
                        enemy.getPosition().x, enemy.getPosition().y, Enemy.WIDTH, Enemy.HEIGHT
                );
            }
        }
    }

    public void drawResumeMessage() {
        game.spriteBatch.draw(resumeSprite, RESUME_ICON_POSITION_X, RESUME_ICON_POSITION_Y);
    }

    public void drawQuitMessage() {
        game.spriteBatch.draw(quitSprite, 112, 500);
    }

    public void drawPauseButton() {
        game.spriteBatch.draw(pauseSprite, 416, 0);
    }

    public void drawGold(GamePlayManager gamePlayManager) {
        gold = gamePlayManager.getGold();
        game.spriteBatch.draw(goldIconSprite, 10, 40, 32, 32);
        goldShadow.draw(game.spriteBatch, gold, 48 + 2, 48 + 2);
        goldFont.draw(game.spriteBatch, gold, 48, 48);
    }

    public void drawHeart() {
        heart = Player.getHealthPoint();
        game.spriteBatch.draw(heartIconSprite, 10, 560, 32, 32);
        heartShadow.draw(game.spriteBatch, heart, 48 + 2, 560 + 2);
        heartFont.draw(game.spriteBatch, heart, 48, 560);
    }

    public void drawScore(GamePlayManager gamePlayManager) {
        score = gamePlayManager.getScore();
        glyphLayout.setText(scoreFont, score);
        scoreShadow.draw(game.spriteBatch, score,
                ((game.GAME_WIDTH / 2) + 2) - glyphLayout.width / 2, (game.GAME_HEIGHT / 10) + 2);
        scoreFont.draw(game.spriteBatch, score, (game.GAME_WIDTH / 2) - glyphLayout.width / 2,
                game.GAME_HEIGHT / 10);
    }

    public void drawBackground() {
        game.spriteBatch.draw(background, 0, 0);
    }

    public void drawPlayer(float runtime) {
        if (Gdx.input.isTouched()) {
            touchPosition = new Vector3();
            touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(touchPosition);
            if (touchPosition.y < Player.SHOOTING_POINT_Y) {
                game.spriteBatch.draw(playerStandBy, Player.SHOOTING_POINT_X - 48,
                        Player.POSITION_Y, Player.PLAYER_WIDTH / 2, Player.PLAYER_HEIGHT / 2,
                        Player.PLAYER_WIDTH, Player.PLAYER_HEIGHT, Player.SCALE_X, Player.SCALE_Y,
                        Player.angle);
                return;
            }
            game.spriteBatch.draw(playerAnimation.getKeyFrame(runtime, true),
                    Player.SHOOTING_POINT_X - 48, Player.POSITION_Y, Player.PLAYER_WIDTH / 2,
                    Player.PLAYER_HEIGHT / 2, Player.PLAYER_WIDTH, Player.PLAYER_HEIGHT,
                    Player.SCALE_X, Player.SCALE_Y, Player.angle);
        } else {
            game.spriteBatch.draw(playerStandBy, Player.SHOOTING_POINT_X - 48, Player.POSITION_Y,
                    Player.PLAYER_WIDTH / 2, Player.PLAYER_HEIGHT / 2, Player.PLAYER_WIDTH,
                    Player.PLAYER_HEIGHT, Player.SCALE_X, Player.SCALE_Y, Player.angle);
        }
    }

    public void drawGameOver() {
        Gdx.gl.glClearColor(0, 0, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, GAME_OVER_TEXT);
        font.draw(game.spriteBatch, GAME_OVER_TEXT,
                ArrowStormGame.GAME_WIDTH / 2 - glyphLayout.width / 2,
                ArrowStormGame.GAME_HEIGHT / 2 - glyphLayout.height / 2);
    }
}