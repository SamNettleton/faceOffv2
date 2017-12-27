package nettleton.sam.faceoffv2;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.Arrays;

public class Achievements extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        SharedPreferences settingSave = this.getSharedPreferences("settingSave", MODE_PRIVATE);
        HomeScreen.backgroundMusic = settingSave.getBoolean("backgrondMusic", true);
        HomeScreen.easyStats[0] = settingSave.getInt("easyWins", 0);
        HomeScreen.easyStats[2] = settingSave.getInt("easyTies", 0);
        HomeScreen.easyStats[1] = settingSave.getInt("easyLosses", 0);
        HomeScreen.mediumStats[0] = settingSave.getInt("mediumWins", 0);
        HomeScreen.mediumStats[2] = settingSave.getInt("mediumTies", 0);
        HomeScreen.mediumStats[1] = settingSave.getInt("mediumLosses", 0);
        HomeScreen.hardStats[0] = settingSave.getInt("hardWins", 0);
        HomeScreen.hardStats[2] = settingSave.getInt("hardTies", 0);
        HomeScreen.hardStats[1] = settingSave.getInt("hardLosses", 0);
        HomeScreen.fastestWins[0] = settingSave.getInt("fastestEasy", 17);
        HomeScreen.fastestWins[1] = settingSave.getInt("fastestMedium", 17);
        HomeScreen.fastestWins[2] = settingSave.getInt("fastestHard", 17);

        String easyString = Arrays.toString(HomeScreen.easyStats);
        easyString = "Easy " + easyString.replaceAll(",", " -");
        String mediumString = Arrays.toString(HomeScreen.mediumStats);
        mediumString = "Medium " + mediumString.replaceAll(",", " -");
        String hardString = Arrays.toString(HomeScreen.hardStats);
        hardString = "Hard " + hardString.replaceAll(",", " -");
        String fastEString = "";
        if (HomeScreen.fastestWins[0] > 16) {
            fastEString = "Easy - N/A";
        } else {
            fastEString = Integer.toString(HomeScreen.fastestWins[0]);
            fastEString = "Easy - " + fastEString;
        }
        String fastMString = "";
        if (HomeScreen.fastestWins[1] > 16) {
            fastMString = "Medium - N/A";
        } else {
            fastMString = Integer.toString(HomeScreen.fastestWins[1]);
            fastMString = "Medium - " + fastMString;
        }
        String fastHString = "";
        if (HomeScreen.fastestWins[2] > 16) {
            fastHString = "Hard - N/A";
        } else {
            fastHString = Integer.toString(HomeScreen.fastestWins[2]);
            fastHString = "Hard - " + fastHString;
        }

        TextView easyStats = findViewById(R.id.easy_stats);
        TextView mediumStats = findViewById(R.id.medium_stats);
        TextView hardStats = findViewById(R.id.hard_stats);
        TextView fastestEast = findViewById(R.id.fastest_easy);
        TextView fastestMedium = findViewById(R.id.fastest_medium);
        TextView fastestHard = findViewById(R.id.fastest_hard);
        easyStats.setText(easyString);
        mediumStats.setText(mediumString);
        hardStats.setText(hardString);
        fastestEast.setText(fastEString);
        fastestMedium.setText(fastMString);
        fastestHard.setText(fastHString);
    }
}
