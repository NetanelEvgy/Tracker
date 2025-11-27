package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText name;
    TextView countText;
    Button countBtn;
    Button resetBtn;
    Button exitBtn;

    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        code();

    }
    private void code()
    {
        Subject subject1 = new Subject("Cookie Clicker");
        Subject subject2 = new Subject("Clicker Cookie");
        Goal goal1 = new Goal("Click Cookies", "1/3/2026");
        Goal goal2 = new Goal("Cookies Click", "2/3/2026");
        Goal goal3 = new Goal("Defeat Vera", "3/3/2026");
        subject1.addGoal(goal1);
        subject1.addGoal(goal3);
        subject2.addGoal(goal2);
        subject2.addGoal(goal3);
        User user1 = new User("Patrick", "Spongebob");
        user1.addSubject(subject1);
        user1.addSubject(subject2);
        Firebase.addToUserData(user1);
    }
}