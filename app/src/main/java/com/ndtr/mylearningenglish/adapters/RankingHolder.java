package com.ndtr.mylearningenglish.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ndtr.mylearningenglish.R;

public class RankingHolder extends RecyclerView.ViewHolder {
    public CardView rankingCardView;
    public TextView rankingFullNameTextView;
    public TextView rankingOrderTextView;
    public TextView rankingScoreTextView;

    public RankingHolder(@NonNull View itemView) {
        super(itemView);
        rankingCardView = itemView.findViewById(R.id.userRankingElementCardView);
        rankingFullNameTextView = itemView.findViewById(R.id.fullNameRankingElementTv);
        rankingOrderTextView = itemView.findViewById(R.id.orderRankingElementTV);
        rankingScoreTextView = itemView.findViewById(R.id.scoreRankingElementTV);
    }
}
