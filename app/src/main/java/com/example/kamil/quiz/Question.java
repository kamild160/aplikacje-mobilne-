package com.example.kamil.quiz;

public class Question {
    private int mTextResId ;
    private boolean mAnswerTrue;
   private boolean mIsAnswered ;
    public boolean mIsCheater;

    public  Question(int question, boolean trueQuestion){
        mTextResId=question;
        mAnswerTrue=trueQuestion;
        mIsAnswered=false;


    }
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


    public boolean getAnserwedQuestion() {
        return mIsAnswered;
    }

    public void setAnsweredQuestion(boolean isAnswered) {
        mIsAnswered = isAnswered;
    }
}


