package com.ndtr.mylearningenglish.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
    private ImageView wordImageView;
    private Button preWord;
    private Button nextWord;

    private ImageView backButton;
    private TextView titleTextView;

    String wordID;
    String preAct;

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
        wordImageView = findViewById(R.id.wordActIV);
        preWord = findViewById(R.id.previousItem);
        nextWord = findViewById(R.id.nextItem);
        backButton = findViewById(R.id.wordActBackBTN);
        titleTextView = findViewById(R.id.wordActTitleTV);




        titleTextView.setText("Từ: " + FirebaseAuth.word.getWordName().toUpperCase());

        if (FirebaseAuth.word.getImageFileName() == null){
            wordImageView.setVisibility(View.GONE);
        }
        else{
            wordImageView.setVisibility(View.VISIBLE);
            FirebaseAuth.setImageToImageView(this, FirebaseAuth.word.getImageFileName(), wordImageView );
        }
        String wordName = FirebaseAuth.word.getWordName();

        wordName = wordName.substring(0,1).toUpperCase() + wordName.substring(1).toLowerCase();

        wordNameTextView.setText(wordName);
        categoryTextView.setText(FirebaseAuth.word.getCategory());

        meaningTextView.setText(FirebaseAuth.word.getMeaning());

        Intent intent = getIntent();
        wordID = intent.getStringExtra("wordID");
        preAct = intent.getStringExtra("preAct");

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

        nextWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNextWord();
            }
        });

        preWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPreviousWord();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (preAct.equals("WordList")){
            Intent intent = new Intent(this, WordsListActivity.class);
            startActivity(intent);
        }
        else{

        }
        FirebaseAuth.word = null;
    }

    public void startNextWord(){
        List<String> wordIDList;
        Intent intent = new Intent(WordActivity.this, WordActivity.class);
        if (preAct.equals("WordList")){
            wordIDList = FirebaseAuth.topic.getWordList();
            intent.putExtra("preAct", "WordList");
        }
        else{
            wordIDList = FirebaseAuth.user.getWordList();
            intent.putExtra("preAct", "NoteBook");
        }
        int index = wordIDList.indexOf(wordID);
        if (index == wordIDList.size()-1) index = -1;
        FirebaseAuth.word = FirebaseAuth.wordList.get(index+1);

        intent.putExtra("wordID", wordIDList.get(index+1));

        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void startPreviousWord(){
        List<String> wordIDList;
        Intent intent = new Intent(WordActivity.this, WordActivity.class);
        if (preAct.equals("WordList")){
            wordIDList = FirebaseAuth.topic.getWordList();
            intent.putExtra("preAct", "WordList");
        }
        else{
            wordIDList = FirebaseAuth.user.getWordList();
            intent.putExtra("preAct", "NoteBook");
        }
        int index = wordIDList.indexOf(wordID);
        if (index == 0) index = wordIDList.size();
        FirebaseAuth.word = FirebaseAuth.wordList.get(index-1);

        intent.putExtra("wordID", wordIDList.get(index-1));

        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }



}
