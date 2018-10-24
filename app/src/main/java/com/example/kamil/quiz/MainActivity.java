package com.example.kamil.quiz;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;
    private TextView mQuestionTextView;



    private void updateNext(){
        mCurrentIndex=(mCurrentIndex+1) % mQuestionsBank.length;
        int question = mQuestionsBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void updatePrevious(){
        if(mCurrentIndex>0)
        {
            mCurrentIndex = (mCurrentIndex - 1);
            ;
        }
       else {
            mCurrentIndex=mQuestionsBank.length-1;
        };
        updateQuestion();

    }
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


        Toast toast = Toast.makeText(this,messageResId, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP,0, 10);
        toast.show();
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
        updateQuestion();
    }
}
