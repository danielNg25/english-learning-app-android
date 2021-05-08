package com.ndtr.mylearningenglish.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.ndtr.mylearningenglish.R;
public class WordHolder extends RecyclerView.ViewHolder {
    CardView wordCardView;
    TextView wordElementTextView;


    public WordHolder(@NonNull View itemView) {
        super(itemView);
        wordCardView = itemView.findViewById(R.id.wordCardView);
        wordElementTextView = itemView.findViewById(R.id.wordNameElementTv);
    }

}
