package com.example.Quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;

public class RulesActivity extends AppCompatActivity {
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        Button back_But=(Button) findViewById(R.id.Back_but);
        back_But.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I_home= new Intent(RulesActivity.this , Home_activity.class);
                startActivity(I_home);
            }
        });


    }
}