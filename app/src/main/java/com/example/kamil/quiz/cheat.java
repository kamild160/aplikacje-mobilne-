package com.example.kamil.quiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class cheat extends AppCompatActivity {

    private Button cheatbutton;
    private TextView podpoweidz;
    private boolean mAnswerIsTrue;

    private void setAnswerShownResult(boolean isAnswershown){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN,isAnswershown);
        setResult(RESULT_OK,data);
    }


    private static final String EXTRA_ANSWER_IS_TRUE =
            "quiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN   =
            "quiz.answer_shown";
    public static Intent nowe (Context packageContext, boolean asnwerIstrue){
        Intent i= new Intent(packageContext, cheat.class);
        i.putExtra(EXTRA_ANSWER_IS_TRUE,asnwerIstrue);
        return i;
    }
    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue=getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);

        podpoweidz=(TextView)findViewById(R.id.podpoweidź);


        cheatbutton=(Button)findViewById(R.id.show);
        cheatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerIsTrue){
                    podpoweidz.setText(R.string.true_button);

                }
                else{
                    podpoweidz.setText(R.string.false_button);
                }
                setAnswerShownResult(true);


            }
        });

    }


}
