package com.example.Quiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.Color;
import android.widget.ImageButton;

import java.util.*;

public class quiz1 extends AppCompatActivity {

    private TextView textViewQuestion;
    private TextView scoreText;
    private TextView time_txt;
    private TextView num_quiz;
    private Button buttonResponse1;
    private Button buttonResponse2;
    private Button buttonResponse3;
    private Button buttonResponse4;
    private  Button next_button;
    private long time_init = 2 * 60 * 1000;
    private CountDownTimer timer;
    private long time = time_init;
    List<Button> buttonList = new ArrayList<>();
    private int score=0;

    private String correctResponse ;
    private Map<String, String[]> quizDictionary;
    private List<String> shuffledQuestions;
    private int currentQuestionIndex;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);
        num_quiz=(TextView)findViewById(R.id.num_quiz);
        textViewQuestion= (TextView) findViewById(R.id.textViewQuestion);
        scoreText=(TextView)findViewById(R.id.textScore);
        time_txt=(TextView)findViewById(R.id.time_txt);
        buttonResponse1 = (Button) findViewById(R.id.buttonResponse1);
        buttonList.add(buttonResponse1);
        buttonResponse2 = (Button) findViewById(R.id.buttonResponse2);
        buttonList.add(buttonResponse2);
        buttonResponse3 = (Button) findViewById(R.id.buttonResponse3);
        buttonList.add(buttonResponse3);
        buttonResponse4 = (Button) findViewById(R.id.buttonResponse4);
        buttonList.add(buttonResponse4);
        next_button=(Button) findViewById(R.id.next_button);
        ImageButton home_button=(ImageButton) findViewById(R.id.home_but);
        quizDictionary = new HashMap<>();
        IntialeDic();


        shuffledQuestions = new ArrayList<>(quizDictionary.keySet());
        Collections.shuffle(shuffledQuestions);
        currentQuestionIndex = 0;


        displayNextQuestion();



        buttonResponse1.setOnClickListener(this::onButtonResponseClick);
        buttonResponse2.setOnClickListener(this::onButtonResponseClick);
        buttonResponse3.setOnClickListener(this::onButtonResponseClick);
        buttonResponse4.setOnClickListener(this::onButtonResponseClick);
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayNextQuestion();
            }
        });

        timer = new CountDownTimer(time_init, 1000) {
            public void onTick(long millisUntilFinished) {
                time = millisUntilFinished;
                timeUP();
            }

            public void onFinish() {
                Intent intent = new Intent(quiz1.this, scoreActivity.class);
                intent.putExtra("score", score);
                startActivity(intent);
            }
        }.start();
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I_home= new Intent(quiz1.this , Home_activity.class);
                startActivity(I_home);
                timer.cancel();
            }
        });


    }
    private void timeUP(){
        long mn = time / (60 * 1000);
        long sec = (time % (60 * 1000)) / 1000;

        // Update UI elements with remaining time
        time_txt.setText(String.format(Locale.getDefault(), "%02d:%02d", mn, sec));
    }
    private void displayNextQuestion() {
        if (currentQuestionIndex < 20) {

            String question = shuffledQuestions.get(currentQuestionIndex);
            String[] responses = quizDictionary.get(question);
            correctResponse= quizDictionary.get(shuffledQuestions.get(currentQuestionIndex))[0];
            textViewQuestion.setText(question);
            num_quiz.setText(String.format("%d/20",currentQuestionIndex+1));
            scoreText.setText("Score: "+score);


            List<String> shuffledResponses = new ArrayList<>(Arrays.asList(responses));
            Collections.shuffle(shuffledResponses);


            buttonResponse1.setText(shuffledResponses.get(0));
            buttonResponse2.setText(shuffledResponses.get(1));
            buttonResponse3.setText(shuffledResponses.get(2));
            buttonResponse4.setText(shuffledResponses.get(3));

            buttonResponse1.setBackgroundColor(getResources().getColor(R.color.color_of_button));
            buttonResponse2.setBackgroundColor(getResources().getColor(R.color.color_of_button));
            buttonResponse3.setBackgroundColor(getResources().getColor(R.color.color_of_button));
            buttonResponse4.setBackgroundColor(getResources().getColor(R.color.color_of_button));


            currentQuestionIndex++;
        } else {
            Intent intent = new Intent(quiz1.this, scoreActivity.class);
            intent.putExtra("score", score);
            startActivity(intent);
            timer.cancel();
        }
    }

    public void onButtonResponseClick(View view) {
        Button clickedButton = (Button) view;
        String selectedResponse = clickedButton.getText().toString();


        if (selectedResponse.equals(correctResponse)) {

            clickedButton.setBackgroundColor(Color.GREEN);
            score+=10;
        } else {
            clickedButton.setBackgroundColor(Color.RED);
            for (Button button : buttonList) {
                if (button.getText().toString().equals(correctResponse)) {
                    button.setBackgroundColor(Color.GREEN);
                    break;
                }
            }
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                displayNextQuestion();
            }
        }, 500);
    }

    public void IntialeDic(){
        quizDictionary.put("What is the output of System.out.println(5 + 3 * 2);?", new String[]{"11", "8", "13", "10"});
        quizDictionary.put("How do you declare an integer variable named \"num\" in Java?", new String[]{"int num;", "integer num;", "num int;", "Integer num;"});
        quizDictionary.put("Which keyword is used to define a class in Java?", new String[]{"class", "public", "void", "new"});
        quizDictionary.put("What does the method nextInt() of the Scanner class do in Java?", new String[]{"Reads the next integer input from the user", "Generates a random integer", "Returns the current system time", "Prints the next integer"});
        quizDictionary.put("How do you create an instance of a StringBuilder object in Java?", new String[]{"StringBuilder sb = new StringBuilder();", "StringBuilder sb();", "StringBuilder sb = new StringBuilder;", "StringBuilder.new();"});

        quizDictionary.put("What is the result of the expression Math.pow(2, 3)?", new String[]{"8.0", "5", "6", "9"});
        quizDictionary.put("Which data structure in Java follows the FIFO (First-In-First-Out) principle?", new String[]{"Queue", "Stack", "ArrayList", "HashMap"});
        quizDictionary.put("How do you initialize a multi-dimensional array in Java?", new String[]{"int[][] arr = new int[3][3];", "int[] arr = new int[3][3];", "int[][] arr = new int[3, 3];", "int[][] arr = { {1, 2}, {3, 4}, {5, 6} };"});
        quizDictionary.put("What is the default value of a boolean variable in Java?", new String[]{"false", "true", "0", "null"});
        quizDictionary.put("What is the output of System.out.println(\"Java\".substring(1, 3));?", new String[]{"av", "Jav", "Ja", "Java"});

        quizDictionary.put("What is the time complexity of binary search algorithm in the worst case scenario?", new String[]{"O(log n)", "O(n)", "O(n log n)", "O(n^2)"});
        quizDictionary.put("What is the result of the expression 10 / 0 in Java?", new String[]{"ArithmeticException: / by zero", "Infinity", "0", "10"});
        quizDictionary.put("Which sorting algorithm has the best time complexity in the average case?", new String[]{"Merge Sort", "Bubble Sort", "Insertion Sort", "Selection Sort"});
        quizDictionary.put("What is the purpose of the finally block in a try-catch-finally statement?", new String[]{"To execute code after try-catch regardless of exception", "To handle exceptions", "To define custom exceptions", "To skip exception handling"});
        quizDictionary.put("How do you declare an ArrayList named \"list\" that can store String objects in Java?", new String[]{"ArrayList<String> list = new ArrayList<>();", "ArrayList list = new ArrayList<>();", "ArrayList<String> list;", "List<String> list = new ArrayList<>();"});

        quizDictionary.put("What is the result of the following Python code snippet?\nx = [1, 2, 3]\ny = x\nx.append(4)\nprint(y)", new String[]{"[1, 2, 3, 4]", "[1, 2, 3]", "[1, 2, 3, 4, 4]", "Error"});
        quizDictionary.put("What does the `range()` function return in Python?", new String[]{"A generator object", "A list of integers", "A tuple of integers", "A dictionary of integers"});
        quizDictionary.put("Which keyword is used to define a function in Python?", new String[]{"def", "func", "define", "function"});
        quizDictionary.put("What is the output of the following Python code snippet?\nprint(\"Python\" * 3)", new String[]{"PythonPythonPython", "Python", "3", "Error"});

        quizDictionary.put("What is the value of π (pi) approximately equal to?", new String[]{"3.14159", "3.1415", "3.142", "3.141"});
        quizDictionary.put("What is the area of a rectangle with length 6 units and width 4 units?", new String[]{"24 square units", "20 square units", "30 square units", "36 square units"});
        quizDictionary.put("What is the square root of 64?", new String[]{"8", "6", "4", "10"});
        quizDictionary.put("What is the value of 2^4 (2 raised to the power of 4)?", new String[]{"16", "8", "32", "64"});

    }
}