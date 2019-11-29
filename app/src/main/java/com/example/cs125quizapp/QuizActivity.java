package com.example.cs125quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

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

    /** Keeps track of which answer is selected by the user.*/
    private int answerSelected;

    /** Keeps track of the actual answer to the question.*/
    private int correctAnswer;

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
        RadioGroup answerGroup = findViewById(R.id.answerRadioGroup);
        answerGroup.setOnCheckedChangeListener((unused, checkedId) -> {
            if (checkedId == R.id.answerA) {
                answerSelected = 1;
            } else if (checkedId == R.id.answerB) {
                answerSelected = 2;
            } else if (checkedId == R.id.answerC) {
                answerSelected = 3;
            } else if (checkedId == R.id.answerD) {
                answerSelected = 4;
            }
        });
        displayQuestion();
        //Reference to submit answer button
        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(unused -> submitButtonClicked());
    }

    /** Shows the question at the top of the page.*/
    private void displayQuestion() {
        TextView question = findViewById(R.id.QuestionView);
        question.setText(mediumQuestions[0]);
    }
    private void submitButtonClicked() {

    }
    private void fillEasyQuestions() {

    }
    private void fillMediumQuestions() {
        String str = "";
        StringBuffer buf = new StringBuffer();
        InputStream is = null;
        ArrayList<String> lines = new ArrayList<String>();
        try {
            is = this.getApplicationContext().getAssets().open("mediumQuestions.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            if (is != null) {
                while ((str = reader.readLine()) != null) {
                    buf.append(str + "\n" );
                    lines.add(str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { is.close(); } catch (Throwable ignore) {}
        }
        for (int i = 0; i < totalQuestions; i++) {
            mediumQuestions[0 + 5 * i] = lines.get(0 + 5 * i);
            mediumQuestions[1 + 5 * i] = lines.get(1 + 5 * i);
            mediumQuestions[2 + 5 * i] = lines.get(2 + 5 * i);
            mediumQuestions[3 + 5 * i] = lines.get(3 + 5 * i);
            mediumQuestions[4 + 5 * i] = lines.get(4 + 5 * i);
        }
    }
    private void fillHardQuestions() {

    }
}
