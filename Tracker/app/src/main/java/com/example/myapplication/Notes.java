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
                //all the things here are worthless its just so i have a user, would be gone after im done.
                Subject subject1 = new Subject("Cookie Clicker");
                Subject subject2 = new Subject("Clicker Cookie");
                Goal goal1 = new Goal("Click Cookies", "1/3/2026");
                Goal goal2 = new Goal("Cookies Click", "2/3/2026");
                Goal goal3 = new Goal("Defeat Vera", "3/3/2026");
                subject1.addGoal(goal1);
                subject1.addGoal(goal3);
                subject2.addGoal(goal2);
                subject2.addGoal(goal3);
                user.addSubject(subject1);
                user.addSubject(subject2);
                Firebase.setUser(user);
                // from here the code stays
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