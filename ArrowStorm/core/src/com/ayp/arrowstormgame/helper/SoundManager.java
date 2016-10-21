package com.ayp.arrowstormgame.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Theerawuth on 10/21/2016.
 */

public class SoundManager {

    public static Sound bossEnemySound;
    public static Sound monsterDeadSound;
    public static Sound shootingSound;
    public static Sound toBattleSound;
    public static Sound bugDeadSound;
    public static Sound bossGoldenBugDeadSound;
    public static Sound wormDeadSound;
    public static Sound bossScorpionDeadSound;
    public static Sound guardianDeadSound;
    public static Sound bossKrakenDeadSound;

    public SoundManager() {
        bossEnemySound = AssetsLoader.bossEnemySound;
        monsterDeadSound = AssetsLoader.monsterDeadSound;
        shootingSound = AssetsLoader.shootingSound;
        toBattleSound = AssetsLoader.toBattleSound;
        bugDeadSound = AssetsLoader.bugDeadSound;
        bossGoldenBugDeadSound = AssetsLoader.bossGoldenBugDeadSound;
        wormDeadSound = AssetsLoader.wormDeadSound;
        bossScorpionDeadSound = AssetsLoader.bossScorpionDeadSound;
        guardianDeadSound = AssetsLoader.guardianDeadSound;
        bossKrakenDeadSound = AssetsLoader.bossKrakenDeadSound;
    }

    public static void playShootingSound(){
        shootingSound.play();
    }

    public static void playToBattleSound(){
        toBattleSound.play();
    }

    public static void playMonsterDead() {
        monsterDeadSound.play();
    }

    public static void playBugDead() {
        bugDeadSound.play();
    }

    public static void playBossGoldenBugDead() {
        bossGoldenBugDeadSound.play();
    }

    public static void playWormDead() {
        wormDeadSound.play();
    }

    public static void playBossScorpionDead() {
        bossScorpionDeadSound.play();
    }

    public static void playGuardianDead() {
        guardianDeadSound.play();
    }

    public static void playBossKrakenDead() {
        bossKrakenDeadSound.play();
    }

    public static void setSwitchSound() {
        // Switch : SoundOff
        Gdx.app.log("SOUNDONOFF", "ONOFF: " + GdxPreference.getSoundSetting());
        if (GdxPreference.getSoundSetting()) {
            GdxPreference.putSoundSetting(false);
            GdxPreference.flushPreferences();
        } else {
            // Switch : SoundOn
            GdxPreference.putSoundSetting(true);
            GdxPreference.flushPreferences();
        }
    }
}