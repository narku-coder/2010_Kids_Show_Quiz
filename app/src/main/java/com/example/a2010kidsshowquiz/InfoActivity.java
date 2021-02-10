package com.example.a2010kidsshowquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class InfoActivity extends AppCompatActivity {
    private static final String TAG = "InfoActivity";
    private boolean active;
    private int infoID;
    private String infoString;

    private Button mCloseButton;

    private TextView mInfoTextView;

    private VideoView mVideoView;

    private MediaController mediaController;

    private int position = 0;

    private String vidName;

    public int getRawResIdByName(String paramString) {
        String str = getPackageName();
        int i = getResources().getIdentifier(paramString, "raw", str);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Res Name: ");
        stringBuilder.append(paramString);
        stringBuilder.append("==> Res ID = ");
        stringBuilder.append(i);
        Log.i("AndroidVideoView", stringBuilder.toString());
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        getWindow().getDecorView().setBackgroundColor(-65536);
        Intent intent = getIntent();
        this.infoID = intent.getIntExtra("info", this.infoID);
        this.infoString = getString(this.infoID);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Current infoString - ");
        stringBuilder.append(this.infoString);
        Log.i("InfoActivity", stringBuilder.toString());
        this.active = true;
        this.mInfoTextView = (TextView)findViewById(R.id.info_text_view);
        this.mInfoTextView.setText(this.infoString);
        this.mVideoView = (VideoView)findViewById(R.id.videoView);
        this.vidName = intent.getStringExtra("video");
        if (this.mediaController == null) {
            this.mediaController = new MediaController((Context)this);
            this.mediaController.setAnchorView((View)this.mVideoView);
            this.mVideoView.setMediaController(this.mediaController);
        }
        try {
            int i = getRawResIdByName(this.vidName);
            VideoView videoView = this.mVideoView;
            stringBuilder = new StringBuilder();
            stringBuilder.append("android.resource://");
            stringBuilder.append(getPackageName());
            stringBuilder.append("/");
            stringBuilder.append(i);
            videoView.setVideoURI(Uri.parse(stringBuilder.toString()));
        } catch (Exception exception) {
            Log.e("Error", exception.getMessage());
            exception.printStackTrace();
        }
        this.mVideoView.requestFocus();
        this.mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer param1MediaPlayer) {
                InfoActivity.this.mVideoView.seekTo(InfoActivity.this.position);
                if (InfoActivity.this.position == 0)
                    InfoActivity.this.mVideoView.start();
                param1MediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    public void onVideoSizeChanged(MediaPlayer param2MediaPlayer, int param2Int1, int param2Int2) {
                        InfoActivity.this.mediaController.setAnchorView((View)InfoActivity.this.mVideoView);
                    }
                });
            }
        });
        this.mCloseButton = (Button)findViewById(R.id.close_button);
        this.mCloseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                InfoActivity.this.finish();
            }
        });
    }

    public void onStop() {
        super.onStop();
        this.active = false;
        this.mVideoView.stopPlayback();
    }
}