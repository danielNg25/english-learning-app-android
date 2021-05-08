package com.ndtr.mylearningenglish.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ndtr.mylearningenglish.R;
import com.ndtr.mylearningenglish.firebase.FirebaseQuery;


public class WordActivity extends AppCompatActivity {

    private TextView wordNameTextView;
    private TextView categoryTextView;
    private TextView meaningTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        wordNameTextView = findViewById(R.id.wordName);
        categoryTextView = findViewById(R.id.wordCategory);
        meaningTextView = findViewById(R.id.meaning);

        wordNameTextView.setText(FirebaseQuery.word.getWordName());
        categoryTextView.setText(FirebaseQuery.word.getCategory().trim());
        meaningTextView.setText(FirebaseQuery.word.getMeaning());
    }
}
