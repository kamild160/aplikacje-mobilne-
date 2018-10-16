package com.example.kamil.quiz;

public class Question {
    private int mTextResId ;

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    private boolean mAnswerTrue;
    public  Question(int question, boolean trueQuestion){
        mTextResId=question;
        mAnswerTrue=trueQuestion;
    }
}
