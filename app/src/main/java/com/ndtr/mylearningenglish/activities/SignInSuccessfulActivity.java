package com.ndtr.mylearningenglish.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import com.ndtr.mylearningenglish.R;

public class SignInSuccessfulActivity extends AppCompatActivity {
    private TextView fullNameTextView;
    private Button logOutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_successful);

        Intent intent = getIntent();
        String fullName = intent.getStringExtra("fullName");

        fullNameTextView = findViewById(R.id.fullNameSignInSuccessful_tv);
        fullNameTextView.setText(fullName);

        logOutBtn = findViewById(R.id.logOut_btn);
        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInSuccessfulActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}