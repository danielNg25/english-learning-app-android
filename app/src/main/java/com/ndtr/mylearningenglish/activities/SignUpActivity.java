package com.ndtr.mylearningenglish.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ndtr.mylearningenglish.R;
import com.ndtr.mylearningenglish.models.User;
import com.ndtr.mylearningenglish.models.UserDatabaseHandler;

public class SignUpActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button addButton;
    private EditText emailEditText;
    private EditText fullNameEditText;
    private TextView hadAccountTextView;


    private UserDatabaseHandler userDBHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usernameEditText = findViewById(R.id.usernameSignUp_et);
        passwordEditText = findViewById(R.id.passwordSignUp_et);
        emailEditText = findViewById(R.id.emailSignUp_et);
        fullNameEditText = findViewById(R.id.fullNameSignUp_et);
        hadAccountTextView = findViewById(R.id.hadAccount_tv);

        addButton = findViewById(R.id.add_btn);


        userDBHandler = new UserDatabaseHandler(this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(signUp()){
                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(intent);
                }

            }
        });

        hadAccountTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean signUp() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String fullName = fullNameEditText.getText().toString();
        String email = emailEditText.getText().toString();

        if (username.isEmpty() || password.isEmpty() || fullName.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return false;
        }

        User user = userDBHandler.getUser(username);
        if ((user!= null)) {
            Toast.makeText(this, "Tên đăng nhập đã tồn tại!", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            User newUser = new User(username, password, fullName, email);
            userDBHandler.addUser(newUser);
            Toast.makeText(this, "Tạo tài khoản thành công!", Toast.LENGTH_SHORT).show();
            return true;
        }
    }
}