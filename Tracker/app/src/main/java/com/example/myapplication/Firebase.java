package com.example.myapplication;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Firebase
{
    private static final FirebaseDatabase db = FirebaseDatabase.getInstance();

    public interface BoolCallback
    {
        void onResult(boolean result);
    }
    public static void doesUserExist(String username,BoolCallback callback)
    {
        db.getReference("Users").child(username).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                callback.onResult(snapshot.exists());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                callback.onResult(false);
            }
        });
    }
    public static void doesPasswordMatch(String username, String password, BoolCallback callback)
    {
        db.getReference("Users").child(username).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                callback.onResult(snapshot.getValue(User.class).getPassword().equals(password));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                callback.onResult(false);
            }
        });
    }
    public static void setUser(User user)
    {
        db.getReference("Users").child(user.getUsername()).setValue(user);
    }
    public static void removeFromUserData(String user, Subject subject)
    {

    }
}
