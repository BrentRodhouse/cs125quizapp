package com.example.cs125quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.view.View;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

import static java.lang.Integer.parseInt;

/**
 * This is the activity where players will set up the parameters of their quiz.
 */
public class NewQuizActivity extends AppCompatActivity {

    /** The difficulty specified by the selected radio button.*/
    private String difficulty;

    //Brent: Sets up custom font in this activity
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_quiz);
        setTitle("Create Quiz");
        RadioGroup diffGroup = findViewById(R.id.diffGroup);
        /*RadioButton diffEasy = findViewById(R.id.diffEasy);
        RadioButton diffMedium = findViewById(R.id.diffMedium);
        RadioButton diffHard = findViewById(R.id.diffHard);*/
        diffGroup.setOnCheckedChangeListener((unused, checkedId) -> {
            hideKeyboard(this);
            if (checkedId == R.id.diffEasy) {
                difficulty = "easy";
            } else if (checkedId == R.id.diffMedium) {
                difficulty = "medium";
            } else if (checkedId == R.id.diffHard) {
                difficulty = "hard";
            }
        });
        //Reference to start quiz button
        Button startQuiz = findViewById(R.id.startQuiz);
        startQuiz.setOnClickListener(unused -> startQuizClicked());
    }
    /** Code to be run when "Start Quiz" is clicked.*/
    private void startQuizClicked() {
        hideKeyboard(this);
        Intent intent = new Intent(this, QuizActivity.class);
        EditText numQuestions = findViewById(R.id.numQuestions);
        //Making sure all the fields have been filled out
        if (!numQuestions.getText().toString().equals("")) {
            intent.putExtra("numQuestions", Integer.parseInt(numQuestions.getText().toString()));
            if (difficulty != null) {
                if (Integer.parseInt(numQuestions.getText().toString()) <= 0) {
                    Toast.makeText(this, "Invalid number of questions", Toast.LENGTH_LONG).show();
                } else if (Integer.parseInt(numQuestions.getText().toString()) > 10) {
                    Toast.makeText(this, "That's too many questions!", Toast.LENGTH_LONG).show();
                } else {
                    intent.putExtra("difficulty", difficulty);
                    startActivity(intent);
                }
            } else {
                Toast.makeText(this,"You didn't specify a difficulty!", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this,"You didn't specify the number of questions!", Toast.LENGTH_LONG).show();
        }
        //Toast.makeText(this, num, Toast.LENGTH_LONG).show();
        //put extras here
    }
    /** Hides the soft keyboard in the current activity.*/
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
