package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Signup extends AppCompatActivity {

    EditText username, password;
    TextView errorMsg;
    Button signupButton, switchToLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        init();
        setButtonsEnabled(true);
        code();
    }
    private void setButtonsEnabled(boolean enabled) {
        signupButton.setEnabled(enabled);
        switchToLoginButton.setEnabled(enabled);
    }
    private void init()
    {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signupButton = findViewById(R.id.signupButton);
        errorMsg = findViewById(R.id.errorMsg);
        switchToLoginButton = findViewById(R.id.switchToLoginButton);
    }
    private void code()
    {
        signupButton.setOnClickListener(v -> {
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
                    if(!exists)
                    {
                        User user = new User(username.getText().toString(), password.getText().toString());
                        Firebase.setUser(user);
                        Intent intent = new Intent(Signup.this, Notes.class);
                        intent.putExtra("USERNAME_KEY", username.getText().toString());
                        startActivity(intent);
                    }
                    else
                    {
                        errorMsg.setText("User already exists");
                        setButtonsEnabled(true);
                    }
                }
            });
        });
        switchToLoginButton.setOnClickListener(v -> {
            setButtonsEnabled(false);
            Intent intent = new Intent(Signup.this, Login.class);
            startActivity(intent);
        });
    }
}