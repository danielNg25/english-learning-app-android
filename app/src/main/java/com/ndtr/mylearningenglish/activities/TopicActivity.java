package com.ndtr.mylearningenglish.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ndtr.mylearningenglish.R;
import com.ndtr.mylearningenglish.firebase.FirebaseAuth;

public class TopicActivity extends AppCompatActivity {
    Button newWordBTN;
    Button exerciseBTN;
    TextView topicNameTV;
    TextView topicDescriptionTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        topicNameTV = findViewById(R.id.topicNameTopicActTV);
        topicDescriptionTV = findViewById(R.id.topicDescriptionTopicActTV);
        newWordBTN = findViewById(R.id.newWordTopicAct);
        exerciseBTN = findViewById(R.id.exerciseTopicAct);

        topicNameTV.setText(FirebaseAuth.topic.getTopicName().toUpperCase());
        topicDescriptionTV.setText(FirebaseAuth.topic.getLongDescription());

        newWordBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewWordActivity();
            }
        });

        exerciseBTN.setOnClickListener(new View.OnClickListener() {
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
        FirebaseAuth.topic = null;
    }
}