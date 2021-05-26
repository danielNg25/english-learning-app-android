package com.ndtr.mylearningenglish.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ndtr.mylearningenglish.R;
import com.ndtr.mylearningenglish.activities.MainScreenActivity;
import com.ndtr.mylearningenglish.activities.WordActivity;
import com.ndtr.mylearningenglish.activities.WordsListActivity;
import com.ndtr.mylearningenglish.firebase.FirebaseAuth;
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
        int pos = position+1;
        holder.wordElementTextView.setText(currentWord.getWordName());

        if(position%2 == 0){

            holder.wordCardView.setCardBackgroundColor(context.getResources().getColor(R.color.even_color));
        }
        else{
            holder.wordCardView.setCardBackgroundColor(context.getResources().getColor(R.color.odd_color));
        }

        holder.wordCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.word = wordList.get(position);
                Intent intent = new Intent(context, WordActivity.class);
                if (context instanceof WordsListActivity){
                    intent.putExtra("wordID", FirebaseAuth.topic.getWordList().get(position));
                    intent.putExtra("preAct", "WordList");
                }
                else if (context instanceof MainScreenActivity){
                    intent.putExtra("wordID", FirebaseAuth.user.getWordList().get(position));
                    intent.putExtra("preAct", "NoteBook");
                }
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }
}
