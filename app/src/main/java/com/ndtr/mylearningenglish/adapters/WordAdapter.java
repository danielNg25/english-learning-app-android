package com.ndtr.mylearningenglish.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ndtr.mylearningenglish.R;
import com.ndtr.mylearningenglish.activities.WordActivity;
import com.ndtr.mylearningenglish.firebase.FirebaseQuery;
import com.ndtr.mylearningenglish.models.Word;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordHolder> {
    private List<Word> wordList;
    Context context;


    public WordAdapter(Context context, List<Word> wordList){
        this.wordList = wordList;
        this.context = context;
    }
    @NonNull
    @Override
    public WordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WordHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.word_element, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WordHolder holder, int position) {
        Word currentWord = wordList.get(position);
        holder.wordElementTextView.setText(currentWord.getWordName());

        holder.wordCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseQuery.word = wordList.get(position);
                context.startActivity(new Intent(context, WordActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }
}
