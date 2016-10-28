package com.ayp.arrowstormgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.ayp.arrowstormgame.interfaces.FirebaseAuthentication;
import com.ayp.arrowstormgame.interfaces.PlayServices;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.firebase.client.Firebase;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesActivityResultCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.example.games.basegameutils.GameHelper;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class AndroidLauncher extends AndroidApplication implements PlayServices,
        FirebaseAuthentication, GoogleApiClient.OnConnectionFailedListener {
    public static final String TAG = "AndroidLauncher";
    private static final int RC_SIGN_IN = 1023;
    private final static int requestCode = 1;

    private GameHelper gameHelper;
    private FirebaseAuth mAuth;
    private User mUser;
    private DatabaseReference mDatabaseReference;
    private ValueEventListener mUserListener;
    private DatabaseReference mUserReference;
    private Firebase arrowStormGameFirebaseRef;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        Firebase.setAndroidContext(this);
        arrowStormGameFirebaseRef = new Firebase(Config.FIREBASE_URL);
        initialize(new ArrowStormGame(this, this), config);
        mUser = new User();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES | GameHelper.CLIENT_PLUS);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        gameHelper.enableDebugLog(false);
        GameHelper.GameHelperListener gameHelperListener = new GameHelper.GameHelperListener() {
            @Override
            public void onSignInFailed() {
                Gdx.app.log(TAG, "onSignInFailed");
            }

            @Override
            public void onSignInSucceeded() {
                Gdx.app.log(TAG, "onSignInSucceeded");
            }
        };
        gameHelper.setup(gameHelperListener);

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Log.d(TAG, "onCreate: uid: " + uid);
        mAuth = FirebaseAuth.getInstance();
        mUserReference = FirebaseDatabase.getInstance().getReference();
//        mUserReference.child("test");//.getRef();//.child(uid);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.i(TAG, "onAuthStateChanged: firebase user is signed in");
                } else {
                    // User is signed out
                    Log.i(TAG, "onAuthStateChanged: firebase user is not signed in");
                }
            }
        };
    }

    @Override
    public void signInGoogle() {
        signInUsingGoogle();
    }

    @Override
    public void signOutGoogle() {
        FirebaseAuth.getInstance().signOut();
    }

    @Override
    public boolean isSignInGoogle() {
        return (mAuth.getCurrentUser() != null);
    }

    @Override
    public void saveGameData(int attackDamageLevel, int attackSpeedLevel, int healthPointLevel,
                             int highScore, int gold) {
        User user = new User();
        user.setGooglePlayId(Games.Players.getCurrentPlayerId(gameHelper.getApiClient()));
        user.setAttackDamageLevel(attackDamageLevel);
        user.setAttackSpeedLevel(attackSpeedLevel);
        user.setHealthPointLevel(healthPointLevel);
        user.setHighScore(highScore);
        user.setGold(gold);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mDatabaseReference.child("users").child(uid).setValue(user);
    }

    @Override
    public Map<String, Integer> retrieveUserData() {
        Map<String, Integer> user = new HashMap<>();
        user.put("AttackDamageLevel", mUser.getAttackDamageLevel());
        user.put("HealthPointLevel", mUser.getHealthPointLevel());
        user.put("AttackSpeedLevel", mUser.getAttackSpeedLevel());
        user.put("Gold", mUser.getGold());
        return user;
    }

    private class Config {
        private static final String FIREBASE_URL = "https://arrow-storm-18250632.firebaseio.com/";

    }

    private void signInUsingGoogle() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }


    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                        }
                        // ...
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        gameHelper.onStart(this);
        mAuth.addAuthStateListener(mAuthListener);

        ValueEventListener userListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                mUser.setHighScore(user.getHighScore());
                mUser.setGooglePlayId(user.getGooglePlayId());
                mUser.setAttackDamageLevel(user.getAttackDamageLevel());
                mUser.setHealthPointLevel(user.getHealthPointLevel());
                mUser.setAttackSpeedLevel(user.getAttackSpeedLevel());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mUserReference.child("users").child(uid).addValueEventListener(userListener);

        mUserListener = userListener;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        gameHelper.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
        if (mUserListener != null) {
            mUserReference.removeEventListener(mUserListener);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        gameHelper.onActivityResult(requestCode, resultCode, data);

        // [Google Play Service]
        // check for "inconsistent state"
        if (resultCode == GamesActivityResultCodes.RESULT_RECONNECT_REQUIRED && requestCode == 1) {

            // force a disconnect to sync up state, ensuring that mClient reports "not connected"
            gameHelper.disconnect();
        }

        // [Firebase Auth Using Google]
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                // Google Sign In failed, update UI appropriately
                // ...
            }
        }
    }

    @Override
    public void signIn() {
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    gameHelper.beginUserInitiatedSignIn();
                }
            });
        } catch (Exception e) {
            Gdx.app.log("MainActivity", "Log in failed: " + e.getMessage() + ".");
        }
    }

    @Override
    public void signOut() {
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    gameHelper.signOut();
                }
            });
        } catch (Exception e) {
            Gdx.app.log("MainActivity", "Log out failed: " + e.getMessage() + ".");
        }
    }

    @Override
    public void rateGame() {
//        String str = "Your PlayStore Link";
//        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));
    }

    @Override
    public void unlockAchievement() {
//        Games.Achievements.unlock(gameHelper.getApiClient(),
//                getString(R.string.achievement_dum_dum));
    }

    @Override
    public void submitScore(int highScore) {
        if (isSignedIn() == true) {
            Games.Leaderboards.submitScore(gameHelper.getApiClient(),
                    getString(R.string.leaderboard_highest), highScore);
        }
    }

    @Override
    public void showAchievement() {
//        if (isSignedIn() == true)
//        {
//            startActivityForResult(Games.Achievements.getAchievementsIntent(gameHelper.getApiClient(),
//                    getString(R.string.achievement_dum_dum)), requestCode);
//        }
//        else
//        {
//            signIn()
//        }
    }

    @Override
    public void showScore() {
        if (isSignedIn() == true) {
            Gdx.app.log(TAG, Games.Players.getCurrentPlayerId(gameHelper.getApiClient()));
            startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(),
                    getString(R.string.leaderboard_highest)), requestCode);
        } else {
            signIn();
        }
    }

    @Override
    public boolean isSignedIn() {
        return gameHelper.isSignedIn();
    }
}
