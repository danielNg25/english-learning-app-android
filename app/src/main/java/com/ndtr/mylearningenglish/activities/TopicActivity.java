package com.ndtr.mylearningenglish.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ndtr.mylearningenglish.R;
import com.ndtr.mylearningenglish.firebase.FirebaseAuth;

public class TopicActivity extends AppCompatActivity {
    TextView newWordTV;
    TextView exerciseTV;
    TextView topicNameTV;
    TextView topicDescriptionTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        topicNameTV = findViewById(R.id.topicNameTopicActTV);
        topicDescriptionTV = findViewById(R.id.topicDescriptionTopicActTV);
        newWordTV = findViewById(R.id.newWordTopicAct);
        exerciseTV = findViewById(R.id.exerciseTopicAct);

        topicNameTV.setText(FirebaseAuth.topic.getTopicName().toUpperCase());
        topicDescriptionTV.setText(FirebaseAuth.topic.getLongDescription());

        newWordTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewWordActivity();
            }
        });

        exerciseTV.setOnClickListener(new View.OnClickListener() {
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