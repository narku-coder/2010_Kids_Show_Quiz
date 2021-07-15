package com.narku.a2010kidsshowquiz;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class SoundServiceToo extends Service {
    MediaPlayer player;

    public IBinder onBind(Intent paramIntent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        this.player = MediaPlayer.create(this, 2131427358);
        this.player.setLooping(true);
        this.player.setVolume(100.0F, 100.0F);
    }

    public void onDestroy() {
        this.player.stop();
        this.player.release();
        stopSelf();
        super.onDestroy();
    }

    public int onStart(Intent paramIntent, int paramInt1, int paramInt2) {
        this.player.start();
        return 2;
    }
}
