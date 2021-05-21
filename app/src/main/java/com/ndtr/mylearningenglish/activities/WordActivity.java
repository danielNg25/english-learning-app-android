package com.ndtr.mylearningenglish.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ndtr.mylearningenglish.R;
import com.ndtr.mylearningenglish.adapters.ExampleHolder;
import com.ndtr.mylearningenglish.firebase.FirebaseAuth;
import com.ndtr.mylearningenglish.fragment.NoteBookFragment;

import java.util.ArrayList;
import java.util.List;


public class WordActivity extends AppCompatActivity {

    private TextView wordNameTextView;
    private TextView categoryTextView;
    private TextView meaningTextView;
    private TextView alreadyAddedTextView;
    private Button addToNoteBookButton;
    private RecyclerView exampleRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        wordNameTextView = findViewById(R.id.wordName);
        categoryTextView = findViewById(R.id.wordCategory);
        meaningTextView = findViewById(R.id.meaning);
        alreadyAddedTextView = findViewById(R.id.alreadyAddedTv);
        addToNoteBookButton = findViewById(R.id.addToNoteBookBtn);
        exampleRecyclerView = findViewById(R.id.exampleRV);

        String wordName = FirebaseAuth.word.getWordName();

        wordName = wordName.substring(0,1).toUpperCase() + wordName.substring(1).toLowerCase();

        wordNameTextView.setText(wordName);
        categoryTextView.setText(FirebaseAuth.word.getCategory());

        meaningTextView.setText(FirebaseAuth.word.getMeaning());

        Intent intent = getIntent();
        String wordID = intent.getStringExtra("wordID");

        if(FirebaseAuth.user.getWordList() == null||!FirebaseAuth.user.getWordList().contains(wordID)){
            addToNoteBookButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(FirebaseAuth.user.checkIfNotExistWord(wordID)){
                        if (NoteBookFragment.wordList==null){
                            NoteBookFragment.wordList = new ArrayList<>();
                        }
                        NoteBookFragment.wordList.add(FirebaseAuth.word);

                    }
                    alreadyAddedTextView.setVisibility(View.VISIBLE);
                    addToNoteBookButton.setVisibility(View.GONE);
                }
            });
        }
        else{
            alreadyAddedTextView.setVisibility(View.VISIBLE);
            addToNoteBookButton.setVisibility(View.GONE);


        }

        List<String> exampleList = FirebaseAuth.word.getExample();

        RecyclerView.Adapter adapter = new RecyclerView.Adapter<ExampleHolder>() {
            @NonNull
            @Override
            public ExampleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new ExampleHolder(LayoutInflater.from(WordActivity.this).inflate(R.layout.example_element, parent, false)) {

                };
            }

            @Override
            public void onBindViewHolder(@NonNull ExampleHolder holder, int position) {
                holder.exampleTextView.setText(exampleList.get(position));
                int pos = position+1;
                holder.exampleNumberTextView.setText("Ví dụ " + pos +": ");
            }

            @Override
            public int getItemCount() {
                return exampleList.size();
            }
        };

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(WordActivity.this);

        exampleRecyclerView.setAdapter(adapter);
        exampleRecyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FirebaseAuth.word = null;
    }
}
