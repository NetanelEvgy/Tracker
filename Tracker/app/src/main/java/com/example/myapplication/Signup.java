package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Signup extends AppCompatActivity {

    EditText username, password;
    TextView errorMsg;
    Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        init();
        code();
    }
    private void init()
    {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signupButton = findViewById(R.id.signupButton);
        errorMsg = findViewById(R.id.errorMsg);
    }
    private void code()
    {
        signupButton.setOnClickListener(v -> {
            // send to db. dont really wanne do that rn.
        });

    }
}