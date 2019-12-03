package com.example.cs125quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.cs125quizapp.ui.main.MainFragment;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

/**
 * This is the activity that will be shown on startup
 */
public class MainActivity extends AppCompatActivity {

    //Brent: Sets up custom font in this activity
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
        //startActivity(new Intent(this, EndActivity.class));
        Button createGame = findViewById(R.id.createQuiz);
        createGame.setOnClickListener(v -> {
            startActivity(new Intent(this, NewQuizActivity.class));
        });
    }
}
