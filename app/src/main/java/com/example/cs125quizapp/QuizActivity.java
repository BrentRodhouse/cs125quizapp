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

    /** The total number of questions we end up writing*/
    private int totalQuestions;

    /** Difficulty of quiz specified by player in NewQuizActivity.*/
    private String difficulty;

    /* Arrays will always be incremented in 5's
    [Question, correct answer, wrong answer, wrong answer, wrong answer, Question, correct answer, wrong answer..., etc.]
    This way we will be able to take questions and correct answers by multiples of 5 */
    /** Array with easy questions and answers.*/
    private String[] easyQuestions;

    /** Array with medium questions and answers.*/
    private String[] mediumQuestions;

    /** Array with hard questions and answers.*/
    private String[] hardQuestions;

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
        //change totalQuestions as we write more
        totalQuestions = 5;
        if (difficulty.equals("easy")) {
            easyQuestions = new String[totalQuestions * 5];
            fillEasyQuestions();
        } else if (difficulty.equals("medium")) {
            mediumQuestions = new String[totalQuestions * 5];
            fillMediumQuestions();
        } else {
            hardQuestions = new String[totalQuestions * 5];
            fillHardQuestions();
        }
    }
    private void fillEasyQuestions() {

    }
    private void fillMediumQuestions() {

    }
    private void fillHardQuestions() {

    }
}
