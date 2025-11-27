package com.example.myapplication;

import com.google.firebase.database.FirebaseDatabase;
public class Firebase
{
    private static final FirebaseDatabase db = FirebaseDatabase.getInstance();

    public static void addToUserData(User user)
    {
        db.getReference("Users").child(user.getUsername()).setValue(user);
    }
    public static void removeFromUserData(String user, Subject subject)
    {

    }
}
