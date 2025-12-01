package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AI extends AppCompatActivity {

    TextView AIMessage;
    Button AIButton, backToNotesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ai);
        init();
        setButtonsEnabled(true);
        code();
    }
    private void setButtonsEnabled(boolean enabled) {
        AIButton.setEnabled(enabled);
        backToNotesButton.setEnabled(enabled);
    }
    private void init()
    {
        AIMessage = findViewById(R.id.AIMessage);
        AIButton = findViewById(R.id.AIButton);
        backToNotesButton = findViewById(R.id.backToNotesButton);
    }
    private void code()
    {
        Intent prevIntent = getIntent();
        String username = prevIntent.getStringExtra("USERNAME_KEY");
        AIButton.setOnClickListener(v -> {
            setButtonsEnabled(false);
           String msg = "get this msg from ai i dont wanne do this rn";
           AIMessage.setText(msg);
           setButtonsEnabled(true);
        });
        backToNotesButton.setOnClickListener(v -> {
            setButtonsEnabled(false);
            Intent newIntent = new Intent(AI.this, Notes.class);
            newIntent.putExtra("USERNAME_KEY", username);
            startActivity(newIntent);
        });
    }
}