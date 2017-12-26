package nettleton.sam.faceoffv2;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Multiplayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        Button localGame = (Button)findViewById(R.id.start_local_button);
        localGame.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!HomeScreen.tutorialCompleted) {
                    Intent i = new Intent(getApplicationContext(),Tutorial.class);
                    HomeScreen.gameType = "TUTORIALGAME";
                    startActivity(i);
                } else {
                    Intent i = new Intent(getApplicationContext(),Game.class);
                    HomeScreen.gameType = "LOCALGAME";
                    startActivity(i);
                }
            }
        });

        Button onlineGame = (Button)findViewById(R.id.start_online_button);
        onlineGame.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!HomeScreen.tutorialCompleted) {
                    Intent i = new Intent(getApplicationContext(),Tutorial.class);
                    HomeScreen.gameType = "TUTORIALGAME";
                    startActivity(i);
                } else {
                    //Intent i = new Intent(getApplicationContext(),Game.class);
                    HomeScreen.gameType = "ONLINEGAME";
                    //startActivity(i);
                }
            }
        });
    }
}
