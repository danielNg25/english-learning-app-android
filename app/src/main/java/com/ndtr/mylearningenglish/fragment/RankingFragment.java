package com.ndtr.mylearningenglish.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.ndtr.mylearningenglish.R;
import com.ndtr.mylearningenglish.adapters.RankingAdapter;
import com.ndtr.mylearningenglish.firebase.FirebaseAuth;
import com.ndtr.mylearningenglish.models.User;

import java.util.ArrayList;
import java.util.List;


public class RankingFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<User> userList = new ArrayList<>();
    private RankingAdapter rankingAdapter;

    private CardView myRankingCardView;
    private TextView myRankingFullNameTextView;
    private TextView myRankingOrderTextView;
    private TextView myRankingScoreTextView;


    public RankingFragment() {

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View myRankingView = view.findViewById(R.id.myRankingCV);
        myRankingCardView = myRankingView.findViewById(R.id.userRankingElementCardView);
        myRankingFullNameTextView = myRankingView.findViewById(R.id.fullNameRankingElementTv);
        myRankingOrderTextView = myRankingView.findViewById(R.id.orderRankingElementTV);
        myRankingScoreTextView = myRankingView.findViewById(R.id.scoreRankingElementTV);

        myRankingFullNameTextView.setText("Bạn:");
        int score = 0;
        if (FirebaseAuth.user.getExercises() == null){
            score = 0;
        }
        else{
            score = FirebaseAuth.user.countExercisesScore();
        }
        myRankingScoreTextView.setText(String.valueOf(score));



        recyclerView = view.findViewById(R.id.rankingRV);

        userList = FirebaseAuth.userList;
        List<String> usernameList = new ArrayList<>();
        for (User user: userList){
            usernameList.add(user.getUserName());
        }
        //int pos = usernameList.indexOf(usernameList) + 1;
        myRankingOrderTextView.setText("No. 2");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rankingAdapter = new RankingAdapter(getActivity(), userList);
        recyclerView.setAdapter(rankingAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ranking, container, false);
    }
}