package nettleton.sam.faceoffv2;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SinglePlayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        Button easyGame = (Button)findViewById(R.id.start_easy_button);
        easyGame.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Game.class);
                HomeScreen.gameType = "EASYGAME";
                startActivity(i);
            }
        });
        Button mediumGame = (Button)findViewById(R.id.start_med_button);
        mediumGame.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Game.class);
                HomeScreen.gameType = "MEDIUMGAME";
                startActivity(i);
            }
        });
        Button hardGame = (Button)findViewById(R.id.start_hard_button);
        hardGame.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Game.class);
                HomeScreen.gameType = "HARDGAME";
                startActivity(i);
            }
        });
    }
}
