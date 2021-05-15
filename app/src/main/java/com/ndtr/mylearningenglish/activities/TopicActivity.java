package com.ndtr.mylearningenglish.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ndtr.mylearningenglish.R;
import com.ndtr.mylearningenglish.firebase.FirebaseQuery;

public class TopicActivity extends AppCompatActivity {
    TextView newWordTV;
    TextView excerciseTV;
    TextView testTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        newWordTV = findViewById(R.id.newWordTopicAct);
        excerciseTV = findViewById(R.id.exerciseTopicAct);
        newWordTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewWordActivity();
            }
        });

        excerciseTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TopicActivity.this, ExercisesListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void startNewWordActivity(){
        Intent intent = new Intent(TopicActivity.this, WordsListActivity.class );
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(TopicActivity.this, MainScreenActivity.class));
        FirebaseQuery.topic = null;
    }
}