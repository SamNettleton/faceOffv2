package nettleton.sam.faceoffv2;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;

public class BackgroundSoundService extends Service {
    private static final String TAG = null;
    MediaPlayer player;
    public IBinder onBind(Intent arg0) {

        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();

        Uri gameMusicUri = Uri.parse("android.resource://nettleton.sam.faceoffv2/raw/perth");
        player = MediaPlayer.create(this, R.raw.perth);
        player.setLooping(true); // Set looping
        player.setVolume(100,100);

    }
    public int onStartCommand(Intent intent, int flags, int startId) {


        player.start();

        return START_NOT_STICKY;
    }

    public void onStart(Intent intent, int startId) {
        // TODO



    }
    public IBinder onUnBind(Intent arg0) {
        // TODO Auto-generated method stub

        return null;
    }

    public void onStop() {

    }
    public void onPause() {
        player.pause();
    }
    @Override
    public void onDestroy() {

        player.stop();
        player.release();
    }

    @Override
    public void onLowMemory() {

    }

    public int currentPosition() {
        return player.getCurrentPosition();
    }
}