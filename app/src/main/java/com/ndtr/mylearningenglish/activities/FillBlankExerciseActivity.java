package com.ndtr.mylearningenglish.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.StorageReference;
import com.ndtr.mylearningenglish.R;
import com.ndtr.mylearningenglish.firebase.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class FillBlankExerciseActivity extends AppCompatActivity {
    LinearLayout scoreLinearLayout;
    TextView scoreTextView;
    ImageView fillBlankImageView;

    TextView questionsTextView[] = new TextView[10];
    EditText userAnswersEditText[] = new EditText[10];
    TextView userAnswersTextView[] = new TextView[10];
    TextView keysTextView[] = new TextView[10];
    Button submitButton;
    List<String> userAnswers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_blank_exercise);

        scoreLinearLayout = findViewById(R.id.fillBlankScoreLL);
        scoreTextView = findViewById(R.id.fillBlankScoreTV);
        fillBlankImageView = findViewById(R.id.fillBlankImageIV);

        if(FirebaseAuth.exercise.getType() == 1){
            fillBlankImageView.setVisibility(View.GONE);
        }
        else if (FirebaseAuth.exercise.getType() == 2){
            fillBlankImageView.setVisibility(View.VISIBLE);
            Intent intent = getIntent();
            int pos = intent.getIntExtra("position", 3);
            String fileName = FirebaseAuth.topic.getTopicName() + pos + ".jpg";
            FirebaseAuth.setImageToImageView(this, fileName, fillBlankImageView );


        }

        for (int i =0; i< 10; i++){
            String resStringID = "fill_blank_exercise_" + i;
            int restID = FirebaseAuth.getResId(resStringID, R.id.class);
            View parent = findViewById(restID);
            questionsTextView[i] = parent.findViewById(R.id.questionFillBlankTV);
            userAnswersEditText[i] = parent.findViewById(R.id.answerFillBlankET);
            userAnswersTextView[i] = parent.findViewById(R.id.answerFillBlankTV);
            keysTextView[i] = parent.findViewById(R.id.keyFillBlankTV);
            int pos = i + 1;
            String question = pos + ". " + FirebaseAuth.exercise.getQuestions().get(i);
            questionsTextView[i].setText(question);
        }

        submitButton = findViewById(R.id.exerciseSubmitBTN);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                submit();
            }
        });

    }

    private void submit(){
        int score = 0;
        for (int i = 0; i < 10; i++){
            if (userAnswersEditText[i].getText().equals(null)){
                userAnswers.add(" ");
            }
            else {
                userAnswers.add(userAnswersEditText[i].getText().toString().trim());
            }

            userAnswersEditText[i].setVisibility(View.GONE);
            userAnswersTextView[i].setVisibility(View.VISIBLE);

            userAnswersTextView[i].setText(userAnswers.get(i));

            if (userAnswers.get(i).trim().toLowerCase().equals(FirebaseAuth.exercise.getKeys().get(i).trim().toLowerCase())){
                score++;
                userAnswersTextView[i].setTextColor(getResources().getColor(R.color.rightAnswer));
            }
            else {
                userAnswersTextView[i].setTextColor(getResources().getColor(R.color.wrongAnswer));
                keysTextView[i].setVisibility(View.VISIBLE);
                keysTextView[i].setText("Đáp án đúng: " + FirebaseAuth.exercise.getKeys().get(i));
                keysTextView[i].setTextColor(getResources().getColor(R.color.rightAnswer));
            }
        }

        scoreLinearLayout.setVisibility(View.VISIBLE);
        scoreTextView.setText(score+"/10");
        submitButton.setVisibility(View.GONE);
    }
}