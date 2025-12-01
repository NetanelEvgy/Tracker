package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Notes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notes);
        init();
        code();
    }
    private void init()
    {

    }
    private void code()
    {
        Intent intent = getIntent();
        String username = intent.getStringExtra("USERNAME_KEY");
        Firebase.getUser(username, new Firebase.UserCallback() {
            @Override
            public void onResult(User user) {
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
            }
        });

    }
}