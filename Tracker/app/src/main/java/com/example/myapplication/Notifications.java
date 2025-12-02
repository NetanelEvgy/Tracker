package com.example.myapplication;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class Notifications extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent) {
        String goalName = intent.getStringExtra("GOAL_NAME");
        if (goalName == null || goalName.isEmpty()) {
            goalName = "Your goal is due tomorrow!";
        }
        int goalId = intent.getIntExtra("GOAL_ID", 0);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent contentIntent = new Intent(context, Login.class);
        contentIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent contentPendingIntent = PendingIntent.getActivity(
                context,
                goalId,
                contentIntent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "GOAL_REMINDERS_CHANNEL")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Goal Reminder")
                .setContentText("Due tomorrow: " + goalName)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(contentPendingIntent)
                .setAutoCancel(true);

        notificationManager.notify(goalId, builder.build());
    }
}
