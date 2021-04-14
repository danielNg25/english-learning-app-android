package com.ndtr.mylearningenglish.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ndtr.mylearningenglish.R;
import com.ndtr.mylearningenglish.models.User;
import com.ndtr.mylearningenglish.models.UserDatabaseHandler;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button signInButton;
    private UserDatabaseHandler userDBHandler;
    private TextView signUpTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.username_et);
        passwordEditText = findViewById(R.id.password_et);
        signInButton = findViewById(R.id.signIn_btn);
        signInButton.setVisibility(View.VISIBLE);
        signUpTextView = findViewById(R.id.didnt_have_account_tv);

        userDBHandler = new UserDatabaseHandler(this);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = signIn();
                if(user != null){
                    Intent intent = new Intent(LoginActivity.this, SignInSuccessfulActivity.class);
                    intent.putExtra("fullName", user.getFullName());
                    startActivity(intent);
                }
            }
        });

        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    public User signIn(){
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tên đăng nhập và mật khẩu!", Toast.LENGTH_SHORT).show();
            return null;
        }
        User user = userDBHandler.getUser(username);
        if (user == null) {
            Toast.makeText(this, "Sai tên đăng nhập hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
            return null;
        }
        else {
            if (!password.equals(user.getPassword())) {
                Toast.makeText(this, "Sai tên đăng nhập hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
                return null;
            }
        }

        return user;
    }

}