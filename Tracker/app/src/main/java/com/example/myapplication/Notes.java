package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Notes extends AppCompatActivity {
    TextView data;
    Button switchToAddOrRemoveButton, addAllNotificationsButton, removeAllNotificationsButton, switchToAIButton, logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notes);
        init();
        setButtonsEnabled(false);
        Helper.disableBackButton(Notes.this);
        NotificationHelper.createNotificationChannel(this);
        NotificationHelper.requestNotificationPermission(this);
        code();
    }
    private void setButtonsEnabled(boolean enabled)
    {
        switchToAddOrRemoveButton.setEnabled(enabled);
        addAllNotificationsButton.setEnabled(enabled);
        removeAllNotificationsButton.setEnabled(enabled);
        switchToAIButton.setEnabled(enabled);
        logoutButton.setEnabled(enabled);
    }
    private void init()
    {
        data = findViewById(R.id.data);
        switchToAddOrRemoveButton = findViewById(R.id.switchToAddOrRemoveButton);
        addAllNotificationsButton = findViewById(R.id.addAllNotificationsButton);
        removeAllNotificationsButton = findViewById(R.id.removeAllNotificationsButton);
        switchToAIButton = findViewById(R.id.switchToAIButton);
        logoutButton = findViewById(R.id.logoutButton);
    }
    private void code()
    {
        Intent prevIntent = getIntent();
        String username = prevIntent.getStringExtra("USERNAME_KEY");
        User user = new User();
        Firebase.getUser(username, new Firebase.UserCallback() {
            @Override
            public void onResult(User userFromFirebase) {
                user.setFromOtherUser(userFromFirebase);
                data.setText(user.toString());
                setButtonsEnabled(true);
            }
        });
        addAllNotificationsButton.setOnClickListener(v ->{
            setButtonsEnabled(false);
            NotificationHelper.requestExactAlarmPermission(Notes.this);
            if (NotificationHelper.hasExactAlarmPermission(Notes.this))
            {
                NotificationHelper.scheduleAll(Notes.this, username, user);
            }
            else
            {
                Toast.makeText(Notes.this, "Permissions Not Granted.", Toast.LENGTH_LONG).show();
            }
            setButtonsEnabled(true);
        });
        removeAllNotificationsButton.setOnClickListener(v ->{
            setButtonsEnabled(false);
            NotificationHelper.requestExactAlarmPermission(Notes.this);
            if (NotificationHelper.hasExactAlarmPermission(Notes.this))
            {
                NotificationHelper.cancelAll(Notes.this, username, user);
            }
            else
            {
                Toast.makeText(Notes.this, "Permissions Not Granted.", Toast.LENGTH_LONG).show();
            }
            setButtonsEnabled(true);
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