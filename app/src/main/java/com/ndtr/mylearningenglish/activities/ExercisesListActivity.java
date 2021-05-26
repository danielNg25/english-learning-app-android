package com.ndtr.mylearningenglish.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import androidx.appcompat.widget.ActionMenuView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.ndtr.mylearningenglish.R;
import com.ndtr.mylearningenglish.adapters.ExerciseListAdapter;
import com.ndtr.mylearningenglish.firebase.FirebaseAuth;
import com.ndtr.mylearningenglish.models.Exercise;
import com.ndtr.mylearningenglish.models.FillBlankExercise;
import com.ndtr.mylearningenglish.models.FourAnswerExercise;

import java.util.ArrayList;
import java.util.List;

public class ExercisesListActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private ActionMenuView actionMenuView;
    private TextView toolbarTitle;
    private RecyclerView recyclerView;
    private ExerciseListAdapter exerciseListAdapter;
    private List<Exercise> exerciseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_list);

        recyclerView = findViewById(R.id.exerciseListRV);

        toolbar = findViewById(R.id.exerciseListActToolBar);
        actionMenuView = findViewById(R.id.exerciseListAmvMenu);
        toolbarTitle = findViewById(R.id.exerciseListTitleTV);

        String title = "Chủ đề: " + FirebaseAuth.topic.getTopicName().toUpperCase();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText(title);

        FirebaseAuth.getAllExercisesByTopicName(FirebaseAuth.topic.getTopicName(), new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshotChild : dataSnapshot.getChildren()){
                    if((long) (dataSnapshotChild.child("type").getValue()) == 1 || (long) (dataSnapshotChild.child("type").getValue()) == 2){
                        FillBlankExercise newExercise = dataSnapshotChild.getValue(FillBlankExercise.class);
                        exerciseList.add(newExercise);
                    }
                    if((long) (dataSnapshotChild.child("type").getValue()) == 3 || (long) (dataSnapshotChild.child("type").getValue()) == 4){
                        FourAnswerExercise newExercise = dataSnapshotChild.getValue(FourAnswerExercise.class);
                        exerciseList.add(newExercise);
                    }
                }
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ExercisesListActivity.this);
                exerciseListAdapter = new ExerciseListAdapter(ExercisesListActivity.this, exerciseList);
                recyclerView.setAdapter(exerciseListAdapter);
                recyclerView.setLayoutManager(linearLayoutManager);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        NavUtils.navigateUpFromSameTask(this);
    }
}