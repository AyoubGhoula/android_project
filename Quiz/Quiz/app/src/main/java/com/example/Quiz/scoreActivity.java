package com.example.Quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageButton;

import android.content.Intent;
import android.os.Bundle;

public class scoreActivity extends AppCompatActivity {
    @SuppressLint({"MissingInflatedId", "DefaultLocale"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Intent intent_score=getIntent();
        int score =intent_score.getIntExtra("score",0);
        TextView score_text=(TextView) findViewById(R.id.score_txt);
        score_text.setText(String.format("%d", score));

       GlobalVariables globalVariables = GlobalVariables.getInstance();
        int bestscore = globalVariables.getScore();
        if (score > bestscore){
            bestscore=score;
            globalVariables.setScore(bestscore);
        }
        TextView bestscore_text=(TextView) findViewById(R.id.bestscore_txt);
        bestscore_text.setText(String.format("%d",bestscore));

        ImageButton home_but=(ImageButton) findViewById(R.id.home_button_sc);
        ImageButton quiz_but=(ImageButton) findViewById(R.id.quize_but);

        home_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_home = new Intent(scoreActivity.this, Home_activity.class);
                startActivity(intent_home);
            }
        });
        quiz_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_quiz = new Intent(scoreActivity.this,quiz1.class);
                startActivity(intent_quiz);
            }
        });
    }
}