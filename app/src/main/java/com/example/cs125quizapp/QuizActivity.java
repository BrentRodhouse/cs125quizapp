package com.example.cs125quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

   private Question[] questions;

    /** Keeps track of which answer is selected by the user.*/
    private int answerSelected;

    /** Keeps track of the actual answer to the question.*/
    private int correctAnswer;

    private int counter = 0;

    //Brent: Sets up custom font in this activity
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
    class Question {
        private String question;
        private List<String> list;
        private String correct;
        Question(String setQuestion, String setFirst, String setSecond, String setThird, String setFourth) {
            question = setQuestion;
            correct = setFirst;
            list = new ArrayList();
            list.add(setFirst);
            list.add(setSecond);
            list.add(setThird);
            list.add(setFourth);
            Collections.shuffle(list);
        }
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
        //fillQuestions(difficulty);
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
        //displayQuestion();
        //Reference to submit answer button
        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(unused -> submitButtonClicked());
    }

    /** Shows the question at the top of the page.*/
    private void displayQuestion() {
        TextView question = findViewById(R.id.QuestionView);
        RadioButton a = findViewById(R.id.answerA);
        RadioButton b = findViewById(R.id.answerB);
        RadioButton c = findViewById(R.id.answerC);
        RadioButton d = findViewById(R.id.answerD);
        question.setText(questions[counter].question);
        a.setText(questions[counter].list.get(0));
        b.setText(questions[counter].list.get(1));
        c.setText(questions[counter].list.get(2));
        d.setText(questions[counter].list.get(3));
        //Reference to submit answer button
        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(unused -> submitButtonClicked());
    }
    private void submitButtonClicked() {
        RadioGroup group = findViewById(R.id.answerRadioGroup);
        group.setOnCheckedChangeListener((unused, checkedId) -> {
            RadioButton chosen = findViewById(checkedId);
            //if chosen.getText().equals()
        });
    }

    private void fillQuestions(String diff) {
        String str = "";
        StringBuffer buf = new StringBuffer();
        InputStream is = null;
        ArrayList<String> lines = new ArrayList<String>();
        try {
            is = this.getApplicationContext().getAssets().open(difficulty + "Questions.txt");
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
            questions[i] = new Question(lines.get(5 * i), lines.get(1 + 5 * i), lines.get(2 + 5 * i), lines.get(3 + 5 * i), lines.get(4 + 5 * i));

        }
    }

}
