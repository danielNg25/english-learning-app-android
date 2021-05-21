package com.ndtr.mylearningenglish.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ndtr.mylearningenglish.R;
import com.ndtr.mylearningenglish.activities.FillBlankExerciseActivity;
import com.ndtr.mylearningenglish.activities.FourAnswerExerciseActivity;
import com.ndtr.mylearningenglish.firebase.FirebaseAuth;
import com.ndtr.mylearningenglish.models.Exercise;
import com.ndtr.mylearningenglish.models.FourAnswerExercise;

import java.util.List;

public class ExerciseListAdapter extends RecyclerView.Adapter<ExerciseListHolder> {

    private List<Exercise> exerciseList;
    Context context;

    public ExerciseListAdapter(Context context, List<Exercise> exerciseList){
        this.context = context;
        this.exerciseList = exerciseList;
    }
    @NonNull
    @Override
    public ExerciseListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ExerciseListHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_list_element, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseListHolder holder, int position) {
        int pos = position + 1;
        holder.exerciseNumberTextView.setText("Bài số " + pos);

        holder.exerciseCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.exercise = exerciseList.get(position);
                if (FirebaseAuth.exercise.getType() == 1 || FirebaseAuth.exercise.getType() == 2){
                    Intent intent = new Intent(context, FillBlankExerciseActivity.class);
                    intent.putExtra("position", position);
                    context.startActivity(intent);
                }

                if (FirebaseAuth.exercise.getType() == 3 || FirebaseAuth.exercise.getType() == 4){
                    Intent intent = new Intent(context, FourAnswerExerciseActivity.class);
                    intent.putExtra("position", position);
                    context.startActivity(intent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }
}
