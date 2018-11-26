package com.example.kamil.quiz;

import android.app.Activity;
import android.content.Intent;
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
    private Button  cheat;


    private final String CHANNEL_ID = "CHANNEL_IDCHANNEL_IDCHANNEL_ID";

    private final String POINTS_STATE = "POINTS_STATE";
    private final String GIVEN_ANSWERS_STATE = "GIVEN_ANSWERS_STATE";
    private final String CURRENT_INDEX_STATE = "CURRENT_INDEX_STATE";
    private final String USED_CHEATS_STATE = "USED_CHEATS_STATE";

    public static final int REQUEST_CODE_CHEAT = 0;
    private boolean[] givenAnswers;
    private boolean[] usedCheats;


    private Question[] mQuestionsBank= new Question[]{
            //pobiera kolejno pytania ze stringów wraz z zadeklarowaną watorćią czy zdanie jest prawdziwe czy nie
            new Question(R.string.question1, true),
            new Question(R.string.question2, false),
            new Question(R.string.question3, false),
    };

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

    private void updateQuestion(){

        int question = mQuestionsBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
        if(usedCheats[mCurrentIndex] || givenAnswers[mCurrentIndex]){
            disableButtons();
        }
        else{
            enableButtons();
        }

    }
    private void disableButtons(){
        mTrueButton.setEnabled(false);
        mFalseButton.setEnabled(false);
    }

    private void enableButtons(){
        mTrueButton.setEnabled(true);
        mFalseButton.setEnabled(true);
    }

    //sprawdza czy wybrana odpoweidź jest prawdzwa

    private boolean Done(){

        for( boolean q : givenAnswers){
            if(!q){
                return false;

            }
        }
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        givenAnswers = new boolean[mQuestionsBank.length];
        usedCheats = new boolean[mQuestionsBank.length];

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
                if(!mQuestionsBank[mCurrentIndex].getAnserwedQuestion()) {
                    checkAnswer(false);  //sprawdza czy odpoweidź jest poprawna
                }

            }

        });

        mTrueButton=(Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mQuestionsBank[mCurrentIndex].getAnserwedQuestion()) {
                    checkAnswer(true);  //sprawdza czy odpoweidź jest poprawna
                }



            }

        });


        mNextButton = (ImageButton)findViewById(R.id.next);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateNext();  //umożliwia zmianę ptania na następne



            }
        });
        mPreviousButton = (ImageButton)findViewById(R.id.previous);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePrevious();



            }
        });
        cheat=(Button)findViewById(R.id.cheatbutton);
        cheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean answeredIsTrue=mQuestionsBank[mCurrentIndex].isAnswerTrue();
                Intent i = com.example.kamil.quiz.cheat.nowe(MainActivity.this,answeredIsTrue);
                startActivityForResult(i,REQUEST_CODE_CHEAT);


            }
        });


        updateQuestion();
    }

    private void checkAnswer(boolean userPressedTrue){

        disableButtons();
        boolean isAnswergiven= givenAnswers[mCurrentIndex];
        boolean answerIsTrue= mQuestionsBank[mCurrentIndex].isAnswerTrue();
        int messageResId=0;
        if(!isAnswergiven) {


            if (answerIsTrue==userPressedTrue ) {
                mCorrectanswers += 1;
                messageResId = R.string.correct;




            } else {
                messageResId = R.string.incorrect;

            }
            Toast toast = Toast.makeText(this, messageResId, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 0, 10);
            toast.show();
        }

        givenAnswers[mCurrentIndex]=true;
        if (Done()){
            result();
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultcode, Intent data) {
        if (resultcode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;
            }
            usedCheats[mCurrentIndex] = com.example.kamil.quiz.cheat.wasAnswerShown(data);
        }

        updateQuestion();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putInt(CURRENT_INDEX_STATE, mCurrentIndex);
        outState.putInt(POINTS_STATE,mCorrectanswers);
        outState.putBooleanArray(GIVEN_ANSWERS_STATE,givenAnswers);
        outState.putBooleanArray(USED_CHEATS_STATE,usedCheats);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);


        mCurrentIndex = savedInstanceState.getInt(CURRENT_INDEX_STATE);
        mCorrectanswers = savedInstanceState.getInt(POINTS_STATE);
        givenAnswers = savedInstanceState.getBooleanArray(GIVEN_ANSWERS_STATE);
        usedCheats = savedInstanceState.getBooleanArray(USED_CHEATS_STATE);
        updateQuestion();
    }}
