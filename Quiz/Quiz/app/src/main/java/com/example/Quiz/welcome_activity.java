package com.example.Quiz;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.Toast;


public class welcome_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Button B =(Button)findViewById(R.id.home_button);
        B.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent I_home= new Intent(welcome_activity.this , Home_activity.class);
            startActivity(I_home);
            }
        }));
        Toast.makeText(welcome_activity.this,"welcome to quiz time !!",Toast.LENGTH_LONG).show();
    }
}