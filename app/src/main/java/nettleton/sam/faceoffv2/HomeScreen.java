package nettleton.sam.faceoffv2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class HomeScreen extends AppCompatActivity {

    public static String gameType = "LOCALOPTION";
    public static boolean tutorialCompleted = false;
    AnimationDrawable cubeAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences settingSave = this.getSharedPreferences("settingSave", MODE_PRIVATE);
        tutorialCompleted = settingSave.getBoolean("tutorialCompleted", false);
        setContentView(R.layout.activity_home_screen);

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
}
