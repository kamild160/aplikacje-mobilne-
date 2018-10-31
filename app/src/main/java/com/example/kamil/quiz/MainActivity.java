package com.example.kamil.quiz;

import android.media.FaceDetector;
import android.media.Image;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;
    private TextView mQuestionTextView;
    private int mCurrentIndex=0;
    private int mCorrectanswers=0;
    private Question[] mQuestionsBank= new Question[]{
            //pobiera kolejno pytania ze stringów wraz z zadeklarowaną watorćią czy zdanie jest prawdziwe czy nie
            new Question(R.string.question1, true),
            new Question(R.string.question2, false),
            new Question(R.string.question3, false),
    };



    private void trueans(){
        if(mQuestionsBank[mCurrentIndex].getAnserwedQuestion()) {
            mFalseButton.setEnabled(false);
            mTrueButton.setEnabled(true);
        }else
        {
            mFalseButton.setEnabled(true);
            mTrueButton.setEnabled(true);
        }
    }

    private void falseans(){
        if(mQuestionsBank[mCurrentIndex].getAnserwedQuestion()) {
            mFalseButton.setEnabled(true);
            mTrueButton.setEnabled(false);
        }else
        {
            mFalseButton.setEnabled(true);
            mTrueButton.setEnabled(true);
        }
    }

    private void buttons(){
        if(mQuestionsBank[mCurrentIndex].getAnserwedQuestion()) {
            mFalseButton.setEnabled(false);
            mTrueButton.setEnabled(false);
        }else
        {
            mFalseButton.setEnabled(true);
            mTrueButton.setEnabled(true);
        }
    }


    private void updateNext(){
        mCurrentIndex=(mCurrentIndex+1) % mQuestionsBank.length;
        int question = mQuestionsBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
        updateQuestion();
    }

    private void updatePrevious(){
        if(mCurrentIndex>0)
        {
            mCurrentIndex = (mCurrentIndex - 1);

        }
       else {
            mCurrentIndex=mQuestionsBank.length-1;
        }
        updateQuestion();

    }




    private void updateQuestion(){

            int question = mQuestionsBank[mCurrentIndex].getTextResId();
            mQuestionTextView.setText(question);

    }

    //sprawdza czy wybrana odpoweidź jest prawdzwa
    private void checkAnswer(boolean userPressedTrue){

        int messageResId=0;
        boolean answerIsTrue= mQuestionsBank[mCurrentIndex].isAnswerTrue();


        if (userPressedTrue == answerIsTrue){
            messageResId=R.string.correct;
            mCorrectanswers+=1;


        } else {
            messageResId = R.string.incorrect;

        }

        Toast toast = Toast.makeText(this,messageResId, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP,0, 10);
        toast.show();

        mQuestionsBank[mCurrentIndex].setAnsweredQuestion(true);

    }

    private boolean Done(){
        int Count=0;
        for( Question q : mQuestionsBank){
            if(q.getAnserwedQuestion()){
                Count++;

            }
        }
       return Count == mQuestionsBank.length;
    }

    private void result(){
        if(mCorrectanswers==0) {
            String text = "Ukończyłeś quiz, zdobyłeś  " + mCorrectanswers + " punktow ";
            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else if ( mCorrectanswers==1){
            String text = "Ukończyłeś quiz, zdobyłeś  " + mCorrectanswers + " punkt ";
            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else if (mCorrectanswers>1) {
            String text = "Ukończyłeś quiz, zdobyłeś  " + mCorrectanswers + " punkty ";
            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
        else {
            String text = "Ukończyłeś quiz, zdobyłeś  " + mCorrectanswers + " punktow ";
            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mQuestionTextView=(TextView) findViewById(R.id.text);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                updateNext();


            }
        });


        mFalseButton=(Button)findViewById(R.id.false_button); //szuka przycisku false
        mFalseButton.setOnClickListener(new View.OnClickListener() {  //po wciśnięciu przycisku
            @Override
            public void onClick(View v) {
                if(!mQuestionsBank[mCurrentIndex].getAnserwedQuestion() ) {

                    checkAnswer(false);  //sprawdza czy odpoweidź jest poprawna
                    falseans();


                }
                    if(Done()){
                        result();
                    }
                }

            });

        mTrueButton=(Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mQuestionsBank[mCurrentIndex].getAnserwedQuestion()) {

                    checkAnswer(false);  //sprawdza czy odpoweidź jest poprawna
                    trueans();
                }

                if(Done()){
                    result();
                }
            }

        });


        mNextButton = (ImageButton)findViewById(R.id.next);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateNext();  //umożliwia zmianę ptania na następne
                buttons();


            }
        });
        mPreviousButton = (ImageButton)findViewById(R.id.previous);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePrevious();
                buttons();


            }
        });
        updateQuestion();
    }
}
