package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class AddOrRemove extends AppCompatActivity {
    EditText subjectToAddOrRemove, subjectForGoal, goalText, dateText;
    TextView errorMsg;
    Button addSubjectButton, removeSubjectButton, addGoalButton, removeGoalButton, backToNotesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_or_remove);
        init();
        setButtonsEnabled(false);
        code();
    }
    private void setButtonsEnabled(boolean enabled) {
        addSubjectButton.setEnabled(enabled);
        removeSubjectButton.setEnabled(enabled);
        addGoalButton.setEnabled(enabled);
        removeGoalButton.setEnabled(enabled);
        backToNotesButton.setEnabled(enabled);
    }
    private void init()
    {
        subjectToAddOrRemove = findViewById(R.id.subjectToAddOrRemove);
        subjectForGoal = findViewById(R.id.subjectForGoal);
        goalText = findViewById(R.id.goalText);
        dateText = findViewById(R.id.dateText);
        errorMsg = findViewById(R.id.errorMsg);
        addSubjectButton = findViewById(R.id.addSubjectButton);
        removeSubjectButton = findViewById(R.id.removeSubjectButton);
        addGoalButton = findViewById(R.id.addGoalButton);
        removeGoalButton = findViewById(R.id.removeGoalButton);
        backToNotesButton = findViewById(R.id.backToNotesButton);
    }
    private boolean validDate(String date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
        try
        {
            LocalDate.parse(date, formatter);
            return true;
        }
        catch (DateTimeParseException e)
        {
            return false;
        }
    }
    private void code()
    {
        Intent prevIntent = getIntent();
        String username = prevIntent.getStringExtra("USERNAME_KEY");
        User user = new User();
        Firebase.getUser(username, new Firebase.UserCallback() {
            @Override
            public void onResult(User userFromFirebase)
            {
                user.setFromOtherUser(userFromFirebase);
                setButtonsEnabled(true);
            }
        });
        addSubjectButton.setOnClickListener(v -> {
            setButtonsEnabled(false);
            if(!user.doesSubjectExist(subjectToAddOrRemove.getText().toString()))
            {
                Subject subject = new Subject(subjectToAddOrRemove.getText().toString());
                user.addSubject(subject);
                Firebase.setUser(user);
            }
            else
            {
                errorMsg.setText("Subject already exists");
            }
            setButtonsEnabled(true);
        });
        removeSubjectButton.setOnClickListener(v -> {
            setButtonsEnabled(false);
            if(user.doesSubjectExist(subjectToAddOrRemove.getText().toString()))
            {
                user.removeSubject(subjectToAddOrRemove.getText().toString());
                Firebase.setUser(user);
            }
            else
            {
                errorMsg.setText("Subject does not exist");
            }
            setButtonsEnabled(true);
        });
        addGoalButton.setOnClickListener(v-> {
            setButtonsEnabled(false);
            if(user.doesSubjectExist(subjectForGoal.getText().toString()))
            {
                if(!validDate(dateText.getText().toString()))
                {
                    errorMsg.setText("Invalid date");
                    setButtonsEnabled(true);
                    return;
                }
                Goal goal = new Goal(goalText.getText().toString(), dateText.getText().toString());
                if(!user.doesGoalExist(subjectForGoal.getText().toString(), goal))
                {
                    user.addGoal(subjectForGoal.getText().toString(), goal);
                    Firebase.setUser(user);
                    setButtonsEnabled(true);
                }
                else
                {
                    errorMsg.setText("Goal already exists");
                    setButtonsEnabled(true);
                }
            }
            else
            {
                errorMsg.setText("Subject does not exist");
                setButtonsEnabled(true);
            }
        });
        removeGoalButton.setOnClickListener(v -> {
            setButtonsEnabled(false);
            if(user.doesSubjectExist(subjectForGoal.getText().toString()))
            {
                Goal goal = new Goal(goalText.getText().toString(), dateText.getText().toString());
                if(user.doesGoalExist(subjectForGoal.getText().toString(), goal))
                {
                    user.removeGoal(subjectForGoal.getText().toString(), goal);
                    Firebase.setUser(user);
                    setButtonsEnabled(true);
                }
                else
                {
                    errorMsg.setText("Goal does not exist");
                    setButtonsEnabled(true);
                }
            }
            else
            {
                errorMsg.setText("Subject does not exist");
                setButtonsEnabled(true);
            }
        });
        backToNotesButton.setOnClickListener(v -> {
            setButtonsEnabled(false);
            Intent newIntent = new Intent(AddOrRemove.this, Notes.class);
            newIntent.putExtra("USERNAME_KEY", username);
            startActivity(newIntent);
        });
    }
}