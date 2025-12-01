package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Notes extends AppCompatActivity {
    TextView data;
    Button switchToAddOrRemoveButton, switchToAIButton, logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notes);
        init();
        setButtonsEnabled(false);
        code();
    }
    private void setButtonsEnabled(boolean enabled) {
        switchToAddOrRemoveButton.setEnabled(enabled);
        switchToAIButton.setEnabled(enabled);
        logoutButton.setEnabled(enabled);
    }
    private void init()
    {
        data = findViewById(R.id.data);
        switchToAddOrRemoveButton = findViewById(R.id.switchToAddOrRemoveButton);
        switchToAIButton = findViewById(R.id.switchToAIButton);
        logoutButton = findViewById(R.id.logoutButton);
    }
    private void code()
    {
        Intent prevIntent = getIntent();
        String username = prevIntent.getStringExtra("USERNAME_KEY");
        Firebase.getUser(username, new Firebase.UserCallback() {
            @Override
            public void onResult(User user) {
                data.setText(user.toString());
                setButtonsEnabled(true);
            }
        });
        switchToAddOrRemoveButton.setOnClickListener(v -> {
            setButtonsEnabled(false);
            Intent newIntent = new Intent(Notes.this, AddOrRemove.class);
            newIntent.putExtra("USERNAME_KEY", username);
            startActivity(newIntent);
        });
        switchToAIButton.setOnClickListener(v -> {
            setButtonsEnabled(false);
            Intent newIntent = new Intent(Notes.this, AI.class);
            newIntent.putExtra("USERNAME_KEY", username);
            startActivity(newIntent);
        });
        logoutButton.setOnClickListener(v -> {
            setButtonsEnabled(false);
            Intent newIntent = new Intent(Notes.this, Login.class);
            startActivity(newIntent);
        });
    }
}