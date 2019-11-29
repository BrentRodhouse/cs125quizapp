package com.example.cs125quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

/**
 * This activity is the actual quiz where players will be presented questions and will answer them
 */
public class QuizActivity extends AppCompatActivity {

    /** Number of Questions specified by the player in NewQuizActivity.*/
    private int numQuestions;

    /** Difficulty of quiz specified by player in NewQuizActivity.*/
    private String difficulty;

    //Brent: Sets up custom font in this activity
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Intent intent = getIntent();
        numQuestions = intent.getIntExtra("numQuestions", 5);
        difficulty = intent.getStringExtra("difficulty");
    }
}
