package com.ndtr.mylearningenglish.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ndtr.mylearningenglish.R;
import com.ndtr.mylearningenglish.models.User;

import java.util.List;

public class RankingAdapter extends RecyclerView.Adapter<RankingHolder> {
    private Context context;
    private List<User> userList;

    public RankingAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public RankingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RankingHolder(LayoutInflater.from(context).inflate(R.layout.ranking_element, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RankingHolder holder, int position) {
        User currentUser = userList.get(position);
        int index = position + 1;
        holder.rankingOrderTextView.setText("No. " + index);
        holder.rankingFullNameTextView.setText(currentUser.getFullName() + ":");
        holder.rankingCardView.setCardBackgroundColor(context.getResources().getColor(R.color.accountFragment2));
        int score = 0;
        if (currentUser.getExercises() == null){
            score = 0;
        }
        else{
            score = currentUser.countExercisesScore();
        }
        holder.rankingScoreTextView.setText(String.valueOf(score));

        holder.rankingCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
