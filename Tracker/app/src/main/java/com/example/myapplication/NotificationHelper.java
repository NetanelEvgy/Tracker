package com.example.myapplication;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationManagerCompat;
import android.app.NotificationChannel;
import android.app.NotificationManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class NotificationHelper {
    public static void requestExactAlarmPermission(Activity activity)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
        {
            AlarmManager alarmManager = (AlarmManager) activity.getSystemService(Context.ALARM_SERVICE);
            if (alarmManager != null && !alarmManager.canScheduleExactAlarms())
            {
                Intent intent = new Intent(android.provider.Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM);
                activity.startActivity(intent);
            }
        }
    }
    public static void requestNotificationPermission(Activity activity)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        {
            activity.requestPermissions(new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 101);
        }
    }
    public static boolean hasExactAlarmPermission(Context context)
        {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
        {
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            return alarmManager != null && alarmManager.canScheduleExactAlarms();
        }
        return true;
    }
    public static void createNotificationChannel(Context context)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "alarmChannel";
            CharSequence name = "Goal Reminders";
            String description = "Channel for goal reminder notifications";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(channelId, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }
    public static int generateId(String username, String subject, String goal, String date)
    {
        return (username + subject + goal + date).hashCode();
    }
    public static void scheduleNotification(Context context, String username, String subject, String goal, String date)
    {
        int requestCode = generateId(username, subject, goal, date);
        LocalDate goalDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/uuuu"));
        LocalDateTime notifyTime = goalDate.minusDays(1).atStartOfDay();
        long triggerMillis = notifyTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long now = System.currentTimeMillis();
        if (triggerMillis <= now)
        {
            triggerMillis = now + 200;
        }
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, Notifications.class);
        intent.putExtra("message", goal);
        intent.putExtra("id", requestCode);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context,
                requestCode,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerMillis, pendingIntent);
    }
    public static void cancelNotification(Context context, String username, String subject, String goal, String date)
    {
        int requestCode = generateId(username, subject, goal, date);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, Notifications.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context,
                requestCode,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );
        alarmManager.cancel(pendingIntent);
        NotificationManagerCompat.from(context).cancel(requestCode);
    }
    public static void scheduleAll(Context context, String username, User user)
    {
        for (Subject s : user.getSubjects().values())
        {
            for (Goal g : s.getGoals().values())
            {
                scheduleNotification(context, username, s.getSubject(), g.getGoal(), g.getDate());
            }
        }
    }
    public static void cancelAll(Context context, String username, User user)
    {
        for (Subject s : user.getSubjects().values())
        {
            for (Goal g : s.getGoals().values())
            {
                cancelNotification(context, username, s.getSubject(), g.getGoal(), g.getDate());
            }
        }
    }

}
