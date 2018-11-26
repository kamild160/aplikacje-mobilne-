package com.example.kamil.quiz;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class cheat extends AppCompatActivity {



    private Button cheatbutton;
    private TextView podpoweidz;
    private boolean mAnswerIsTrue;
    private TextView mAPITextView;
    private boolean ismAnswerShown = false;
    private final String CHANNEL_ID = "CHANNEL_IDCHANNEL_IDCHANNEL_ID";

    private static final String EXTRA_ANSWER_IS_TRUE =
            "quiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN   =
            "quiz.answer_shown";
    public static final String KEY_IS_CHEATER = "is_cheater";
    public static final int REQUEST_CODE_CHEAT = 0;





    public static Intent nowe (Context packageContext, boolean asnwerIstrue){
        Intent i= new Intent(packageContext, cheat.class);
        i.putExtra(EXTRA_ANSWER_IS_TRUE,asnwerIstrue);
        return i;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);


        mAnswerIsTrue=getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);
        mAPITextView = (TextView) findViewById(R.id.mApiTextView);
        mAPITextView.setText("Wersja Api: " + Build.VERSION.SDK_INT);


        podpoweidz=(TextView)findViewById(R.id.podpoweidź);

        ismAnswerShown = false;
        cheatbutton=(Button)findViewById(R.id.show);
        cheatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAnswer();

            }
        }
        );

        }

    private void showAnswer(){
        ismAnswerShown=true;
        Intent data = new Intent();
        data.putExtra(KEY_IS_CHEATER,true);
        setResult(RESULT_OK, data);
        podpoweidz.setText("Poprawna odpowiedź to: " + Boolean.toString(mAnswerIsTrue).toUpperCase());
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){

        savedInstanceState.putBoolean(KEY_IS_CHEATER, ismAnswerShown);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        ismAnswerShown = savedInstanceState.getBoolean(KEY_IS_CHEATER);
        if (ismAnswerShown){
            showAnswer();
        }
    super.onRestoreInstanceState(savedInstanceState);
    }

    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

}



