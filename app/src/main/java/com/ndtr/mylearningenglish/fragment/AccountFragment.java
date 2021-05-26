package com.ndtr.mylearningenglish.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ndtr.mylearningenglish.R;
import com.ndtr.mylearningenglish.activities.LoginActivity;
import com.ndtr.mylearningenglish.firebase.FirebaseAuth;

public class AccountFragment extends Fragment {

    private TextView fullNameTextView, userNameTextView, emailTextView, noteBookCountTextView, currentRankingTextView, exercisesScoreTextView;
    private Button logOutButton;

    public AccountFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_page, container, false);




        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logOutButton = view.findViewById(R.id.logOutBtn);
        fullNameTextView = view.findViewById(R.id.fullNameAccountTab_tv);
        userNameTextView = view.findViewById(R.id.usernameAccountTab_tv);
        emailTextView = view.findViewById(R.id.emailAccountTab_tv);

        noteBookCountTextView = view.findViewById(R.id.noteBookCountTV);
        currentRankingTextView = view.findViewById(R.id.curRankingTV);
        exercisesScoreTextView = view.findViewById(R.id.scoreCountTV);

        fullNameTextView.setText(FirebaseAuth.user.getFullName());
        userNameTextView.setText(FirebaseAuth.user.getUserName());
        emailTextView.setText(FirebaseAuth.user.getEmail());

        noteBookCountTextView.setText(String.valueOf(FirebaseAuth.user.getWordList().size()));
        exercisesScoreTextView.setText(String.valueOf(FirebaseAuth.user.countExercisesScore()));

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });
    }

    public void logOut(){
        FirebaseAuth.user = null;
        startActivity(new Intent(getContext(), LoginActivity.class));
    }
}
