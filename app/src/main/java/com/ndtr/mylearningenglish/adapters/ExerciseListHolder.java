package com.ndtr.mylearningenglish.adapters;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ndtr.mylearningenglish.R;

public class ExerciseListHolder extends RecyclerView.ViewHolder {

    public CardView exerciseCardView;
    public TextView exerciseNumberTextView;


    public ExerciseListHolder(@NonNull View itemView) {
        super(itemView);

        exerciseNumberTextView = itemView.findViewById(R.id.exerciseNumberElementTv);
        exerciseCardView = itemView.findViewById(R.id.exerciseCardView);
    }
}
