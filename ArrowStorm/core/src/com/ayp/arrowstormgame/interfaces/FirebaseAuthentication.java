package com.ayp.arrowstormgame.interfaces;

import java.util.Map;

/**
 * Created by Tanaphon on 10/25/2016.
 */

public interface FirebaseAuthentication {
    public void signInGoogle();
    public void signOutGoogle();
    public boolean isSignInGoogle();
    public void saveGameData(int attackDamageLevel, int attackSpeedLevel, int healthPointLevel,
                             int highScore, int gold);
    public Map<String, Integer> retrieveUserData();
}
