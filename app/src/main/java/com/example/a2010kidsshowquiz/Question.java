package com.example.a2010kidsshowquiz;

public class Question {
    private String mCorrectAnswer;

    private int mQuestionText;

    private int mInfoText;

    private String mVidName;

    public Question(int paramQuestion, String paramString1, int paramInfo, String paramVideo) {
        this.mQuestionText = paramQuestion;
        this.mCorrectAnswer = paramString1;
        this.mInfoText = paramInfo;
        this.mVidName = paramVideo;
    }

    public String getCorrectAnswer() {
        return this.mCorrectAnswer;
    }

    public int getmQuestionText() {
        return this.mQuestionText;
    }

    public int getmInfoText() {
        return this.mInfoText;
    }

    public String getVid() {
        return this.mVidName;
    }

    public void setCorrectAnswer(String paramString) {
        this.mCorrectAnswer = paramString;
    }

    public void setmQuestionText(int paramInt) {
        this.mQuestionText = paramInt;
    }

    public void setmInfoText(int paramInt) {
        this.mInfoText = paramInt;
    }

    public void setVideoName(String paramVid) {
        this.mVidName = paramVid;
    }
}
