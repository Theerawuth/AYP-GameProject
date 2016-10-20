package com.ayp.arrowstormgame.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
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

    public void setSwitchSound() {
        // Switch : SoundOff
        Gdx.app.log("SOUNDONOFF", "ONOFF: " + GdxPreference.getSoundSetting());
        if (GdxPreference.getSoundSetting()) {
            GdxPreference.putSoundSetting(false);
            GdxPreference.flushPreferences();
            backgroundMusicStop();
        } else {
            // Switch : SoundOn
            GdxPreference.putSoundSetting(true);
            GdxPreference.flushPreferences();
            backgroundMusicPlay();
        }
    }

    public void backgroundMusicStop() {
        backgroundMusic.stop();
    }

}
