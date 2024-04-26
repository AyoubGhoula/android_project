package com.example.Quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;
import android.os.Bundle;
import android.widget.TextView;

public class Home_activity extends AppCompatActivity {
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ImageButton IB_back=(ImageButton) findViewById(R.id.home_but);
        IB_back.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I_back=new Intent(Home_activity.this, welcome_activity.class);
                startActivity(I_back);
            }
        }));
        Button start=(Button)findViewById(R.id.button);
        start.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent I_quiz=new Intent(Home_activity.this, quiz1.class);
            startActivity(I_quiz);
            }
        }));
         Button rule = (Button) findViewById(R.id.rule_but);
        rule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I_rule= new Intent(Home_activity.this , RulesActivity.class);
                startActivity(I_rule);
            }
        });
        TextView score=(TextView)findViewById(R.id.score) ;
        GlobalVariables globalVariables = GlobalVariables.getInstance();
        int bestscore = globalVariables.getScore();
        score.setText(String.format("%d",bestscore));
        ImageButton git_but=(ImageButton) findViewById(R.id.git_but);
        git_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://github.com/AyoubGhoula");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        ImageButton mail_but=(ImageButton) findViewById(R.id.mail_but);
        mail_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:ayoubghoula40@gmail.com"));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback or Inquiry");
                startActivity(intent);
            }
        });


    }
}