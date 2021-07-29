package com.narku.a2010kidsshowquiz;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {
    public int gameScore;
    private TextView mScore2TextView;
    private MediaPlayer player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        startService(new Intent(this, SoundServiceToo.class));
        getIntent().getIntExtra("score1", this.gameScore);
        this.player2 = MediaPlayer.create(this, R.raw.victory);
        this.player2.setLooping(true);
        this.player2.setVolume(100.0F, 100.0F);
        this.mScore2TextView = findViewById(R.id.score2_text_view);
        this.mScore2TextView.setText(new StringBuilder().append("Final Score: ").append(this.gameScore).toString());
    }

    protected void onDestroy() {
        stopService(new Intent(this, SoundServiceToo.class));
        this.player2.stop();
        this.player2.release();
        super.onDestroy();
    }
}