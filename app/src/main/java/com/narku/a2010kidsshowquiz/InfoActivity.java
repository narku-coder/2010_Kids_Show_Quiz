package com.narku.a2010kidsshowquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.google.android.gms.tasks.*;
import com.google.firebase.auth.*;
import com.google.firebase.storage.*;

import java.io.File;

public class InfoActivity extends AppCompatActivity {
    private static final String TAG = "InfoActivity";
    private boolean active;
    private int infoID;
    private String infoString;
    private Button mCloseButton;
    private TextView mInfoTextView;
    private VideoView mVideoView;
    private MediaController mediaController;
    private final int position = 0;
    private String vidName;
    private File vidFile;
    private final FirebaseStorage storage = FirebaseStorage.getInstance("gs://tv-show-quiz-25060.appspot.com");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        getWindow().getDecorView().setBackgroundColor(-65536);
        Intent intent = getIntent();
        this.infoID = intent.getIntExtra("info", this.infoID);
        this.infoString = getString(this.infoID);
        this.active = true;
        this.mInfoTextView = findViewById(R.id.info_text_view);
        this.mInfoTextView.setText(this.infoString);
        this.mVideoView = findViewById(R.id.videoView);
        this.vidName = intent.getStringExtra("video");
        if (this.mediaController == null) {
            this.mediaController = new MediaController(this);
            this.mediaController.setAnchorView(this.mVideoView);
            this.mVideoView.setMediaController(this.mediaController);
        }
        try {
            StorageReference gsReference = storage.getReferenceFromUrl("gs://tv-show-quiz-25060.appspot.com/" + this.vidName + ".mp4");

            vidFile = File.createTempFile(this.vidName, "mp4");
            Log.i("vidFileName", vidFile.getName());
            gsReference.getFile(vidFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Log.i("Successful download", "The download was successful");
                    mVideoView.setVideoURI(Uri.fromFile(vidFile));
                    mVideoView.requestFocus();
                    mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        public void onPrepared(MediaPlayer param1MediaPlayer) {
                            InfoActivity.this.mVideoView.seekTo(InfoActivity.this.position);
                            if (InfoActivity.this.position == 0)
                                InfoActivity.this.mVideoView.start();
                            param1MediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                                public void onVideoSizeChanged(MediaPlayer param2MediaPlayer, int param2Int1, int param2Int2) {
                                    InfoActivity.this.mediaController.setAnchorView(InfoActivity.this.mVideoView);
                                }
                            });
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle any errors
                    Log.e("Download Error", exception.getMessage());
                    exception.printStackTrace();
                }
            });
        } catch (Exception exception) {
            Log.e("Error", exception.getMessage());
            exception.printStackTrace();
        }
        this.mCloseButton = findViewById(R.id.close_button);
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