package nettleton.sam.faceoffv2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.media.AsyncPlayer;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.os.AsyncTask;

import static android.provider.ContactsContract.Directory.PACKAGE_NAME;

public class HomeScreen extends AppCompatActivity {

    public static String gameType = "LOCALOPTION";
    public static boolean tutorialCompleted = false;
    public static boolean backgroundMusic = true;
    public static int[] easyStats = {0, 0, 0};
    public static int[] mediumStats = {0, 0, 0};
    public static int[] hardStats = {0, 0, 0};
    public static int[] fastestWins = {17, 17, 17};
    private static final String TAG = "MainActivity";
    AsyncPlayer musicPlayer = new AsyncPlayer(TAG);
    AnimationDrawable cubeAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        SharedPreferences settingSave = this.getSharedPreferences("settingSave", MODE_PRIVATE);
        HomeScreen.tutorialCompleted = settingSave.getBoolean("tutorialCompleted", false);
        HomeScreen.backgroundMusic = settingSave.getBoolean("backgroundMusic", true);

        if (backgroundMusic) {
            Uri gameMusicUri = Uri.parse("android.resource://nettleton.sam.faceoffv2/raw/perth");
            musicPlayer.play(this, gameMusicUri,true, AudioManager.STREAM_MUSIC);
        }

        ImageView cubeImage = (ImageView) findViewById(R.id.cube_animation);
        cubeImage.setBackgroundResource(R.drawable.cube_animation);
        cubeAnimation = (AnimationDrawable) cubeImage.getBackground();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        Button singlePlayerGame = (Button)findViewById(R.id.start_single_button);
        singlePlayerGame.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),SinglePlayer.class);
                startActivity(i);
            }
        });

        Button multiplayerGame = (Button)findViewById(R.id.start_multi_button);
        multiplayerGame.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Multiplayer.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if(hasFocus){
            cubeAnimation.start();
        }
    }

    public void musicToggle(View v) {
        if (backgroundMusic) {
            musicPlayer.stop();
            backgroundMusic = false;
        } else {
            Uri gameMusicUri = Uri.parse("android.resource://nettleton.sam.faceoffv2/raw/perth");
            musicPlayer.play(this, gameMusicUri,true, AudioManager.STREAM_MUSIC);
            backgroundMusic = true;
        }
        SharedPreferences settingSave = this.getSharedPreferences("settingSave", MODE_PRIVATE);
        SharedPreferences.Editor edit = settingSave.edit();
        edit.putBoolean("backgroundMusic", backgroundMusic);
        edit.apply();
    }

    public void achievementsPage(View v) {
        Intent i = new Intent(getApplicationContext(),Achievements.class);
        startActivity(i);
    }

    public void settingsPage(View v) {
        Intent i = new Intent(getApplicationContext(),Settings.class);
        startActivity(i);
    }

    /*@Override
    protected void onPause() {
        musicPlayer.stop();
        super.onPause();
    }

    @Override
    protected void onResume() {
        if (backgroundMusic) {
            Uri gameMusicUri = Uri.parse("android.resource://nettleton.sam.faceoffv2/raw/perth");
            musicPlayer.play(this, gameMusicUri,true, AudioManager.STREAM_MUSIC);
            backgroundMusic = true;
        }
        super.onResume();
    }*/
}
