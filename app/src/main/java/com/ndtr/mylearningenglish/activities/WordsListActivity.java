package com.ndtr.mylearningenglish.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ndtr.mylearningenglish.R;
import com.ndtr.mylearningenglish.adapters.WordAdapter;
import com.ndtr.mylearningenglish.firebase.FirebaseQuery;
import com.ndtr.mylearningenglish.models.Word;

import java.util.ArrayList;
import java.util.List;

public class WordsListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Word> wordList = new ArrayList<>();
    private WordAdapter wordAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_list);
        recyclerView = findViewById(R.id.wordsListRV);




//        FirebaseQuery.getAllWordsFromList(FirebaseQuery.topic.getWordList(),new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(dataSnapshot.getValue() != null){
//                    Word newWord = dataSnapshot.getValue(Word.class);
//                    wordList.add(newWord);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
        List<String> wordsIDList = FirebaseQuery.topic.getWordList();

        for (String wordID: wordsIDList){
            FirebaseQuery.getWordsFromID(wordID, new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (dataSnapshot.getValue() != null){
               
                        Word newWord = dataSnapshot.getValue(Word.class);
                        wordList.add(newWord);
                    }
                    if (wordID == wordsIDList.get(wordsIDList.size()-1)){
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