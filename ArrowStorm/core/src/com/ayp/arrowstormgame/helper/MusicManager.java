package com.ayp.arrowstormgame.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * Created by Theerawuth on 10/10/2016.
 */

public class MusicManager {
    public Music backgroundMusic;

    public MusicManager(Music music) {

        backgroundMusic = music;
    }

    public void backgroundMusicPlay() {
            backgroundMusic.play();
    }

    public void setSwitchMusic() {
        // Switch : MusicOff
        Gdx.app.log("MUSICONOFF", "ONOFF: " + GdxPreference.getMusicSetting());
        if (GdxPreference.getMusicSetting()) {
            GdxPreference.putMusicSetting(false);
            GdxPreference.flushPreferences();
            backgroundMusicStop();
        } else {
            // Switch : MusicOn
            GdxPreference.putMusicSetting(true);
            GdxPreference.flushPreferences();
            backgroundMusicPlay();
        }
    }

    public void backgroundMusicStop() {
        backgroundMusic.stop();
    }

}
