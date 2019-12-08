package com.example.cs125quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class EndActivity extends AppCompatActivity {
    //Brent: Sets up custom font in this activity
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    private int score;

    private int numQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        setTitle("Quiz Over!");
        Intent intent = getIntent();
        score = intent.getIntExtra("score", 0);
        numQuestions = intent.getIntExtra("numQuestions", 0);
        TextView finalScoreView = findViewById(R.id.finalScore);
        if (score*1.0/numQuestions < 0.75) {
            Toast.makeText(this, "You should study more", Toast.LENGTH_LONG).show();
        } else if (score*1.0/numQuestions >= 0.9) {
            Toast.makeText(this, "Excellent!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Good job.", Toast.LENGTH_LONG).show();
        }
        finalScoreView.setText(score + "/" + numQuestions);
        Button startQuiz = findViewById(R.id.newQuiz);
        startQuiz.setOnClickListener(unused -> newQuizClicked());
    }
    private void newQuizClicked() {
        Intent intent = new Intent(this, NewQuizActivity.class);
        startActivity(intent);
    }
}
