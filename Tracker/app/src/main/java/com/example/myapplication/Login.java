package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    EditText username, password;
    TextView errorMsg;
    Button loginButton, switchToSignupButton;

    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        setButtonsEnabled(true);
        code();

    }
    private void setButtonsEnabled(boolean enabled) {
        loginButton.setEnabled(enabled);
        switchToSignupButton.setEnabled(enabled);
    }
    private void init()
    {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        errorMsg = findViewById(R.id.errorMsg);
        switchToSignupButton = findViewById(R.id.switchToSignupButton);

    }
    private void code()
    {
        loginButton.setOnClickListener(v -> {
            setButtonsEnabled(false);
            if(username.getText().toString().isEmpty())
            {
                errorMsg.setText("Username cannot be empty");
                setButtonsEnabled(true);
                return;
            }
            if(password.getText().toString().isEmpty())
            {
                errorMsg.setText("Password cannot be empty");
                setButtonsEnabled(true);
                return;
            }
            Firebase.doesUserExist(username.getText().toString(), new Firebase.BoolCallback() {
                 @Override
                 public void onResult(boolean exists)
                 {
                     if(exists) {
                         Firebase.doesPasswordMatch(username.getText().toString(), password.getText().toString(), new Firebase.BoolCallback() {
                             @Override
                             public void onResult(boolean passwordsMatch) {
                                 if (passwordsMatch) {
                                     Intent intent = new Intent(Login.this, Notes.class);
                                     intent.putExtra("USERNAME_KEY", username.getText().toString());
                                     startActivity(intent);
                                 }
                                 else
                                 {
                                     errorMsg.setText("Incorrect Password");
                                     setButtonsEnabled(true);
                                 }
                             }
                         });
                     }
                     else
                     {
                         errorMsg.setText("User does not exists");
                         setButtonsEnabled(true);
                     }
                 }
             });
        });
        switchToSignupButton.setOnClickListener(v -> {
            setButtonsEnabled(false);
            Intent intent = new Intent(Login.this, Signup.class);
            startActivity(intent);
        });
    }
}