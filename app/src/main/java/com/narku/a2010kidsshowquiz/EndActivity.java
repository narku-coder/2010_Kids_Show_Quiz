package com.narku.a2010kidsshowquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {
    public int gameScore;
    private TextView mScore2TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        startService(new Intent(this, SoundServiceToo.class));
        getIntent().getIntExtra("score1", this.gameScore);
        this.mScore2TextView = findViewById(R.id.score2_text_view);
        this.mScore2TextView.setText(this.gameScore);
    }

    protected void onDestroy() {
        stopService(new Intent(this, SoundServiceToo.class));
        super.onDestroy();
    }
}