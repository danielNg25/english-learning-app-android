package com.ndtr.mylearningenglish.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ndtr.mylearningenglish.R;

public class TopicActivity extends AppCompatActivity {
    TextView newWordTV;
    TextView excerciseTV;
    TextView testTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        newWordTV = findViewById(R.id.newWordTopicAct);
        newWordTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewWordActivity();
            }
        });
    }

    private void startNewWordActivity(){
        Intent intent = new Intent(TopicActivity.this, WordsListActivity.class );
        startActivity(intent);
    }


}