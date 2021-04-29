package com.ndtr.mylearningenglish.adapters;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ndtr.mylearningenglish.R;


public class TopicHolder extends RecyclerView.ViewHolder {


    public TextView topicNameTextView;
    public CardView topicFragLinearLayout;

    public TopicHolder(@NonNull View itemView) {
        super(itemView);
        topicNameTextView = itemView.findViewById(R.id.topicNameElementTv);
        topicFragLinearLayout = itemView.findViewById(R.id.topicCardView);
    }


}
