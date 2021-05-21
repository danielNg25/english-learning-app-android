package com.ndtr.mylearningenglish.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ndtr.mylearningenglish.R;
import com.ndtr.mylearningenglish.firebase.FirebaseAuth;
import com.ndtr.mylearningenglish.models.FourAnswerExercise;

import java.util.ArrayList;
import java.util.List;

public class FourAnswerExerciseActivity extends AppCompatActivity {
    LinearLayout scoreLinearLayout;
    TextView scoreTextView;
    ImageView fourAnswerImageView;

    TextView answerTextView[][] = new TextView[10][4];
    TextView questionsTextView[] = new TextView[10];
    EditText userAnswersEditText[] = new EditText[10];
    TextView userAnswersTextView[] = new TextView[10];
    TextView keysTextView[] = new TextView[10];
    Button submitButton;
    List<String> userAnswers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_answer_exercise);

        scoreLinearLayout = findViewById(R.id.fourAnswerScoreLL);
        scoreTextView = findViewById(R.id.fourAnswerScoreTV);
        fourAnswerImageView = findViewById(R.id.fourAnswerIM);
        submitButton = findViewById(R.id.fourAnswerExerciseSubmitBTN);

        if (FirebaseAuth.exercise.getType() == 3){
            fourAnswerImageView.setVisibility(View.GONE);
        }
        else if (FirebaseAuth.exercise.getType() ==4){
            fourAnswerImageView.setVisibility(View.VISIBLE);
            Intent intent = getIntent();
            int pos = intent.getIntExtra("position", 3);
            String fileName = FirebaseAuth.topic.getTopicName() + pos + ".jpg";
            FirebaseAuth.setImageToImageView(this, fileName, fourAnswerImageView);
        }

        for (int i = 0; i < 10; i++){
            String resStringID = "fourAnswerExercise_" + i;
            int resID = FirebaseAuth.getResId(resStringID, R.id.class);
            View parent = findViewById(resID);

            questionsTextView[i] = parent.findViewById(R.id.fourAnswerQuestionTV);
            userAnswersEditText[i] = parent.findViewById(R.id.answerFourAnswerET);
            userAnswersTextView[i] = parent.findViewById(R.id.answerFourAnswerTV);
            keysTextView[i] = parent.findViewById(R.id.keyFourAnswerTV);

            answerTextView[i][0] = parent.findViewById(R.id.firstAnswerTV);
            answerTextView[i][0].setText("A. "+ ((FourAnswerExercise)FirebaseAuth.exercise).getAnswers().get(i).get(0).toString());
            answerTextView[i][1] = parent.findViewById(R.id.secondAnswerTV);
            answerTextView[i][1].setText("B. "+ ((FourAnswerExercise)FirebaseAuth.exercise).getAnswers().get(i).get(1).toString());
            answerTextView[i][2] = parent.findViewById(R.id.thirdAnswerTV);
            answerTextView[i][2].setText("C. "+ ((FourAnswerExercise)FirebaseAuth.exercise).getAnswers().get(i).get(2).toString());
            answerTextView[i][3] = parent.findViewById(R.id.fourthAnswerTV);
            answerTextView[i][3].setText("D. "+ ((FourAnswerExercise)FirebaseAuth.exercise).getAnswers().get(i).get(3).toString());

            int pos = i + 1;
            String question = pos + ". " + FirebaseAuth.exercise.getQuestions().get(i);
            questionsTextView[i].setText(question);

            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    submit();
                }
            });
        }


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
                keysTextView[i].setText("Đáp án đúng: " + FirebaseAuth.exercise.getKeys().get(i).toUpperCase());
                keysTextView[i].setTextColor(getResources().getColor(R.color.rightAnswer));
            }
        }

        scoreLinearLayout.setVisibility(View.VISIBLE);
        scoreTextView.setText(score+"/10");
        submitButton.setVisibility(View.GONE);
    }
}



























