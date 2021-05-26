package com.ndtr.mylearningenglish.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActionMenuView;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.ndtr.mylearningenglish.R;
import com.ndtr.mylearningenglish.adapters.WordAdapter;
import com.ndtr.mylearningenglish.firebase.FirebaseAuth;
import com.ndtr.mylearningenglish.models.Word;

import java.util.ArrayList;
import java.util.List;

public class WordsListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Word> wordList = new ArrayList<>();
    private WordAdapter wordAdapter;
    private Toolbar wordListToolBar;
    private ActionMenuView actionMenuView;
    private TextView titleTextView;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        NavUtils.navigateUpFromSameTask(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_list);
        recyclerView = findViewById(R.id.wordsListRV);


        titleTextView = findViewById(R.id.wordListTitleTV);
        wordListToolBar = findViewById(R.id.wordListActToolBar);
        actionMenuView = findViewById(R.id.wordListAmvMenu);

        setSupportActionBar(wordListToolBar);

        String title = "Chủ đề: " + FirebaseAuth.topic.getTopicName().toUpperCase();

        titleTextView.setText(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        List<String> wordsIDList = FirebaseAuth.topic.getWordList();

        for (String wordID: wordsIDList){
            FirebaseAuth.getWordsFromID(wordID, new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (dataSnapshot.getValue() != null){
               
                        Word newWord = dataSnapshot.getValue(Word.class);
                        wordList.add(newWord);
                    }
                    if (wordID == wordsIDList.get(wordsIDList.size()-1)){
                        FirebaseAuth.wordList = wordList;
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(WordsListActivity.this);
                        wordAdapter = new WordAdapter(WordsListActivity.this, wordList);
                        recyclerView.setAdapter(wordAdapter);
                        recyclerView.setLayoutManager(linearLayoutManager);
                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }







    }


}