package com.ndtr.mylearningenglish.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ndtr.mylearningenglish.R;

public class ExampleHolder extends RecyclerView.ViewHolder {

    public TextView exampleTextView;
    public TextView exampleNumberTextView;
    public ExampleHolder(@NonNull View itemView) {
        super(itemView);
        exampleTextView = itemView.findViewById(R.id.exampleTV);
        exampleNumberTextView = itemView.findViewById(R.id.exampleNumberTV);
    }
}
