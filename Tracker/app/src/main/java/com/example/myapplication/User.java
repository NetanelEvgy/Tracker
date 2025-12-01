package com.example.myapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import kotlinx.coroutines.selects.SelectUnbiasedKt;

public class User
{
    private String _username;
    private Map<String, Subject> _subjects;
    private String _password;
    public User()
    {
        this._username = "";
        this._password = "";
        this._subjects = new HashMap<>();
    }
    public User(String username, String password)
    {
        this._username = username;
        this._password = password;
        this._subjects = new HashMap<>();
    }

    public void setUsername(String username)
    {
        this._username = username;
    }
    public void setPassword(String password)
    {
        this._password = password;
    }
    public void setSubjects(Map<String, Subject> subjects)
    {
        this._subjects = subjects;
    }
    public String getUsername()
    {
        return this._username;
    }
    public String getPassword()
    {
        return this._password;
    }
    public Map<String, Subject> getSubjects()
    {
        return this._subjects;
    }
    public void addSubject(Subject subject)
    {
        this._subjects.put(subject.getSubject(), subject);
    }
    public void removeSubject(String subject)
    {
        this._subjects.remove(subject);
    }
    public void addGoal(String subject, Goal goal)
    {
        this._subjects.get(subject).addGoal(goal);
    }
    public void removeGoal(String subject, Goal goal)
    {
        this._subjects.get(subject).removeGoal(goal);
    }
    public boolean doesGoalExist(String subject, Goal goal)
    {
        return this._subjects.get(subject).doesGoalExist(goal);
    }
    public boolean doesSubjectExist(String subject)
    {
        return this._subjects.containsKey(subject);

    }
    public void setFromOtherUser(User user)
    {
        this._username = user._username;
        this._password = user._password;
        this._subjects = user._subjects;
    }
    public String toString()
    {
        String userStr = "Username: " + this._username + "\n\n";
        for (Subject subject : this._subjects.values())
        {
            userStr += subject.toString() + "\n";
        }
        return userStr;
    }
}
