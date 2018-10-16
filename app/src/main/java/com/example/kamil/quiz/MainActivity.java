package com.example.kamil.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Button mTrueButton;  /*klasy przycisków*/
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;


    private Question[] mQuestionsBank= new Question[]{

            //pobiera kolejno pytania ze stringów wraz z zadeklarowaną watorćią czy zdanie jest prawdziwe czy nie
            new Question(R.string.question1, true),
            new Question(R.string.question2, false),
            new Question(R.string.question3, false),
    };

    private int mCurrentIndex=0;

    private void updateQuestion(){
        int question = mQuestionsBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    //sprawdza czy wybrana odpoweidź jest prawdzwa
    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue= mQuestionsBank[mCurrentIndex].isAnswerTrue();

        int messageResId=0;

        if (userPressedTrue == answerIsTrue){
            messageResId=R.string.correct;
        } else {
            messageResId=R.string.incorrect;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show(); // wyświetla toast z informacją o poprawności odpowiedzi

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mQuestionTextView=(TextView) findViewById(R.id.text);


        mFalseButton=(Button)findViewById(R.id.false_button); //szuka przycisku false
        mFalseButton.setOnClickListener(new View.OnClickListener() {  //po wciśnięciu przycisku
            @Override
            public void onClick(View v) {
                checkAnswer(false);  //sprawdza czy odpoweidź jest poprawna
            }
        });
        mTrueButton=(Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });


        mNextButton = (Button) findViewById(R.id.next);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex=(mCurrentIndex+1) % mQuestionsBank.length;  //umożliwia zmianę ptania na następne
               updateQuestion();
            }
        });
        updateQuestion();
    }
}

