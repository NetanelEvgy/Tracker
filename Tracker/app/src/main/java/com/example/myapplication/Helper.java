package com.example.myapplication;

import android.widget.Toast;

import androidx.activity.ComponentActivity;
import androidx.activity.OnBackPressedCallback;

public class Helper
{
    public static void disableBackButton(ComponentActivity activity)
    {
        activity.getOnBackPressedDispatcher().addCallback(activity, new OnBackPressedCallback(true)
        {
            @Override
            public void handleOnBackPressed()
            {
                Toast.makeText(
                        activity,
                        "Use the buttons to navigate i made them for a reason -_-",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}
