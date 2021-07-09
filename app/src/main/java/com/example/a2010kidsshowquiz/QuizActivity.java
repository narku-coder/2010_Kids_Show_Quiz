package com.example.a2010kidsshowquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity
{
    private static final String KEY_INDEX = "index";
    private static final String TAG = "QuizActivity";
    private String answer;
    private ImageView mSoundButton;
    private ImageView mCartoonButton;
    public int mCurrentIndex = 0;
    private ImageView mDisneyButton;
    private ImageView mFreeformButton;
    private ImageView mNickButton;
    private final Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_huge, "Freeform", R.string.info_huge, "huge"), new Question(R.string.question_symbionic, "Cartoon Network", R.string.info_symbionic, "symbionic"), new Question(R.string.question_sheen, "Nickelodeon", R.string.info_sheen, "sheen"), new Question(R.string.question_georgia, "Freeform", R.string.info_georgia, "georgia"), new Question(R.string.question_robotomy, "Cartoon Network", R.string.info_robotomy, "robotomy"), new Question(R.string.question_diddo, "Disney", R.string.info_diddo, "diddo"), new Question(R.string.question_jane, "Freeform", R.string.info_jane, "jane"), new Question(R.string.question_friends, "Disney", R.string.info_friends, "whenever"), new Question(R.string.question_plank, "Disney", R.string.info_plank, "prank"), new Question(R.string.question_problem, "Cartoon Network", R.string.info_problem, "problem"),
            new Question(R.string.question_mixels, "Cartoon Network", R.string.info_mixels, "mixel"), new Question(R.string.question_bug, "Disney", R.string.info_bug, "bug"), new Question(R.string.question_random, "Disney", R.string.info_random, "random"), new Question(R.string.question_breadwinner, "Nickelodeon", R.string.info_breadwinner, "breadwinner"), new Question(R.string.question_bunsen, "Nickelodeon", R.string.info_bunsen, "bunsen"), new Question(R.string.question_twisted, "Freeform", R.string.info_twisted, "twisted"), new Question(R.string.question_rex, "Cartoon Network", R.string.info_rex, "rex"), new Question(R.string.question_tower, "Cartoon Network", R.string.info_tower, "tower"), new Question(R.string.question_wayne, "Nickelodeon", R.string.info_wayne, "wayne"), new Question(R.string.question_moguls, "Nickelodeon", R.string.info_moguls, "moguls"),
            new Question(R.string.question_ravenwood, "Freeform", R.string.info_ravenwood, "ravenswood"), new Question(R.string.question_freak, "Freeform", R.string.info_freak, "freak"), new Question(R.string.question_epic, "Nickelodeon", R.string.info_epic, "epic"), new Question(R.string.question_buckets, "Disney", R.string.info_buckets, "buckets"), new Question(R.string.question_kidding, "Disney", R.string.info_kidding, "kidding"), new Question(R.string.question_motor, "Disney", R.string.info_motor, "motorcity"), new Question(R.string.question_becoming, "Freeform", R.string.info_becoming, "becoming"), new Question(R.string.question_dwarf, "Disney", R.string.info_dwarf, "dwarves"), new Question(R.string.question_marvin, "Nickelodeon", R.string.info_marvin, "marvin"), new Question(R.string.question_gamer, "Disney", R.string.info_gamer, "gamer"),
            new Question(R.string.question_level, "Cartoon Network", R.string.info_level, "level") };
    public List<Question> l = Arrays.asList(this.mQuestionBank);
    private TextView mQuestionTextView;
    private TextView mScoreView;
    private TextView mTimerView;
    private MediaPlayer player;
    private int pos;
    public int score = 0;
    public CountDownTimer ct;

    private void checkAnswer(String paramString) {
        int i;
        String stringBuilder = "";
        stringBuilder += "Current index - ";
        stringBuilder += this.mCurrentIndex;
        stringBuilder += ".";
        Log.i("QuizActivity", stringBuilder);
        if (paramString == ((Question)this.l.get(this.mCurrentIndex)).getCorrectAnswer()) {
            i = R.string.correct_toast;
            this.score++;
        } else {
            i = R.string.incorrect_toast;
        }
        Toast.makeText((Context)this, i, Toast.LENGTH_SHORT).show();
        this.mScoreView.setText("Score: " + this.score);
        Intent intent = new Intent((Context)this, InfoActivity.class);
        intent.putExtra("info", ((Question)this.l.get(this.mCurrentIndex)).getmInfoText());
        intent.putExtra("video", ((Question)this.l.get(this.mCurrentIndex)).getVid());
        startActivity(intent);
    }

    private void endGame(int paramInt) {
        if (paramInt == this.mQuestionBank.length) {
            Intent intent = new Intent((Context)this, EndActivity.class);
            intent.putExtra("score1", this.score);
            startActivity(intent);
        }
    }

    private void updateQuestion() {
        this.mCurrentIndex++;
        Log.i("QuizActivity", "Index has updated.");
        int i = ((Question)this.l.get(this.mCurrentIndex)).getmQuestionText();
        this.mQuestionTextView.setText(i);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Collections.shuffle(this.l);
        if (savedInstanceState != null)
            this.mCurrentIndex = savedInstanceState.getInt("index", 0);
        this.player = MediaPlayer.create((Context)this, R.raw.smooth_jazz);
        this.player.setLooping(false);
        this.player.setVolume(100.0F, 100.0F);
        this.mTimerView = (TextView)findViewById(R.id.timer_text_view);
        ct = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimerView.setText(String.valueOf(millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                QuizActivity.this.updateQuestion();
                ct.start();
            }
        };
        this.mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        int i = ((Question)this.l.get(this.mCurrentIndex)).getmQuestionText();
        this.mQuestionTextView.setText(i);
        this.player.start();
        this.mSoundButton = (ImageView)findViewById(R.id.sound_button);
        this.mSoundButton.setOnClickListener(param1View -> {
           if(player.isPlaying())
           {
               player.pause();
               mSoundButton.setImageResource(R.drawable.sound_off);
           }
           else if(!(player.isPlaying()))
           {
               player.start();
               mSoundButton.setImageResource(R.drawable.sound_on);
           }
        });
        this.mDisneyButton = (ImageView)findViewById(R.id.disney_button);
        this.mDisneyButton.setOnClickListener(param1View -> {
            answer = "Disney";
            QuizActivity quizActivity = QuizActivity.this;
            quizActivity.checkAnswer(quizActivity.answer);
            QuizActivity.this.updateQuestion();
        });
        this.mNickButton = (ImageView)findViewById(R.id.nick_button);
        this.mNickButton.setOnClickListener(param1View -> {
            answer = "Nickelodeon";
            QuizActivity quizActivity = QuizActivity.this;
            quizActivity.checkAnswer(quizActivity.answer);
            QuizActivity.this.updateQuestion();
        });
        this.mCartoonButton = (ImageView)findViewById(R.id.cartoon_button);
        this.mCartoonButton.setOnClickListener(param1View -> {
            answer = "Cartoon Network";
            QuizActivity quizActivity = QuizActivity.this;
            quizActivity.checkAnswer(quizActivity.answer);
            QuizActivity.this.updateQuestion();
        });
        this.mFreeformButton = (ImageView)findViewById(R.id.freeform_button);
        this.mFreeformButton.setOnClickListener(param1View -> {
            answer = "Freeform";
            QuizActivity quizActivity = QuizActivity.this;
            quizActivity.checkAnswer(quizActivity.answer);
            QuizActivity.this.updateQuestion();
        });
        this.mScoreView = (TextView)findViewById(R.id.score_text_view);
        this.mScoreView.setText("Score: " + this.score);
        endGame(this.mCurrentIndex);
    }

    protected void onDestroy() {
        this.player.stop();
        this.player.release();
        super.onDestroy();
    }

    protected void onPause() {
        this.player.pause();
        this.pos = this.player.getCurrentPosition();
        super.onPause();
    }

    protected void onResume() {
        this.player.seekTo(this.pos);
        this.player.start();
        this.ct.start();
        super.onResume();
    }

    public void onSaveInstanceState(Bundle paramBundle) {
        super.onSaveInstanceState(paramBundle);
        Log.i("QuizActivity", "onSaveInstanceState");
        paramBundle.putInt("index", this.mCurrentIndex);
    }
}