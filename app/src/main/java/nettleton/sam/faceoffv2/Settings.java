package nettleton.sam.faceoffv2;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AsyncPlayer;
import android.media.AudioManager;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        final SharedPreferences settingSave = this.getSharedPreferences("settingSave", MODE_PRIVATE);
        final SharedPreferences.Editor edit = settingSave.edit();

        final TextView contactLabel = findViewById(R.id.contact_label);
        contactLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailDeveloper();
            }
        });

        /*final Switch musicSwitch = findViewById(R.id.music_change);
        musicSwitch.setChecked(HomeScreen.backgroundMusic);
        musicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                HomeScreen.backgroundMusic = musicSwitch.isChecked();
                edit.putBoolean("backgroundMusic", HomeScreen.backgroundMusic);
                edit.apply();
                HomeScreen.recreate();
            }
        });*/

        Button tutorialButton = findViewById(R.id.tutorial_button);
        tutorialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeScreen.gameType = "TUTORIALGAME";
                Intent i = new Intent(getApplicationContext(),Tutorial.class);
                startActivity(i);
            }
        });

        Button statsButton = findViewById(R.id.stats_button);
        statsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder ab = new AlertDialog.Builder(Settings.this);
                ab.setTitle("Confirm Reset");
                ab.setMessage("Are you sure you want to reset all of your stats?");
                ab.setPositiveButton("Reset", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        edit.putInt("easyWins", 0);
                        edit.putInt("easyTies", 0);
                        edit.putInt("easyLosses", 0);
                        edit.putInt("mediumWins", 0);
                        edit.putInt("mediumTies", 0);
                        edit.putInt("mediumLosses", 0);
                        edit.putInt("hardWins", 0);
                        edit.putInt("hardTies", 0);
                        edit.putInt("hardLosses", 0);
                        edit.putInt("fastestEasy", 17);
                        edit.putInt("fastestMedium", 17);
                        edit.putInt("fastestHard", 17);
                        edit.apply();
                    }
                });
                ab.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                ab.show();
            }
        });

        final TextView cookAcknowledge = findViewById(R.id.cook_acknowledge);
        cookAcknowledge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://dicook.org/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        /*final TextView musicAcknowledge = findViewById(R.id.music_acknowledge);
        musicAcknowledge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.parachutemusic.com/free-stock-music?gclid=Cj0KCQiAg4jSBRCsARIsAB9ooauq4DN87F2kpir4xtRwiYA_wek6-i1NEIU8sWr_ilFCo_VgbNTDpJIaAuV1EALw_wcB";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });*/
    }

    public void emailDeveloper() {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"faceoffdevelopment@gmail.com"});
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Settings.this, "No email client found.",
                    Toast.LENGTH_SHORT).show();
        }
    }
/*
    @Override
    protected void onPause() {
        //musicPlayer.stop();
        Intent svc = new Intent(this, BackgroundSoundService.class);
        stopService(svc);
        super.onPause();
    }

    @Override
    protected void onResume() {
        if (HomeScreen.backgroundMusic) {
            Intent svc = new Intent(this, BackgroundSoundService.class);
            startService(svc);
            //Uri gameMusicUri = Uri.parse("android.resource://nettleton.sam.faceoffv2/raw/perth");
            //musicPlayer.play(this, gameMusicUri,true, AudioManager.STREAM_MUSIC);
        }
        super.onResume();
    }*/
}
