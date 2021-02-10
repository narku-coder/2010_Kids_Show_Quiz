package com.example.a2010kidsshowquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {
    private TextView mAppInfoTextView;

    private TextView mAppNameTextView;

    private Button mStartButton;

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_welcome);
        this.mAppNameTextView = (TextView)findViewById(R.id.app_name_text_view);
        this.mAppInfoTextView = (TextView)findViewById(R.id.app_info_text_view);
        this.mStartButton = (Button)findViewById(R.id.start_button);
        this.mStartButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                Intent intent = new Intent((Context)WelcomeActivity.this, QuizActivity.class);
                WelcomeActivity.this.startActivity(intent);
            }
        });
    }
}