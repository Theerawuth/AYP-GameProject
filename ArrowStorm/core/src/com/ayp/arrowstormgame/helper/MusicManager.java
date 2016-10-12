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

    public MusicManager(){

        musicPref = Gdx.app.getPreferences("MyPreference");
        musicPref.putBoolean("soundOn", true);
    }
    public MusicManager(Music music){
        backgroundMusic = music;
        musicPref = Gdx.app.getPreferences("MyPreference");
        musicPref.putBoolean("soundOn", true);
    }

    public void backgroundMusicPlay() {
        Gdx.app.log("MusicManager", "Sound : "+ musicPref.getBoolean("soundOn") );
        if(musicPref.getBoolean("soundOn"))
        {
            Gdx.app.log("MusicManager", "Play Sound");
            backgroundMusic.play();
        }
    }

    public void setSwitchSound(){
        if(musicPref.getBoolean("soundOn")){
            musicPref.putBoolean("soundOn", false);
            backgroundMusicStop();
            Gdx.app.log("MusicManager", "Switch : SoundOff");
        }
        else
        {
            musicPref.putBoolean("soundOn", true);
            musicPref.flush();
            Gdx.app.log("MusicManager", "Switch : SoundOn");
            backgroundMusicPlay();
        }
    }

    public void backgroundMusicStop() {
        musicPref.flush();
        backgroundMusic.stop();
    }

}
