package com.ayp.arrowstormgame.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;

/**
 * Created by Theerawuth on 10/10/2016.
 */

public class MusicManager {
    public Music backgroundMusic;
    Preferences musicPref;

    public MusicManager(Music music) {
        backgroundMusic = music;
        musicPref = Gdx.app.getPreferences("MyPreference");
        musicPref.putBoolean("soundOn", true);
    }

    public void backgroundMusicPlay() {
        if (musicPref.getBoolean("soundOn")) {
            //Play Sound
            backgroundMusic.play();
        }
    }

    public void setSwitchSound() {
        // Switch : SoundOff
        if (musicPref.getBoolean("soundOn")) {
            musicPref.putBoolean("soundOn", false);
            backgroundMusicStop();
        } else {
            // Switch : SoundOn
            musicPref.putBoolean("soundOn", true);
            musicPref.flush();
            backgroundMusicPlay();
        }
    }

    public void backgroundMusicStop() {
        musicPref.flush();
        backgroundMusic.stop();
    }

}
