package com.example.myapplication;

import java.util.ArrayList;

public class User
{
    private String _username;
    private ArrayList<Subject> _subjects;
    private String _password;
    public User()
    {
        this._username = "";
        this._password = "";
        this._subjects = new ArrayList<>();
    }
    public User(String username, String password)
    {
        this._username = username;
        this._password = password;
        this._subjects = new ArrayList<>();
    }

    public void setUsername(String username)
    {
        this._username = username;
    }
    public void setPassword(String password)
    {
        this._password = password;
    }
    public String getUsername()
    {
        return this._username;
    }
    public String getPassword()
    {
        return this._password;
    }
    public ArrayList<Subject> getSubjects()
    {
        return this._subjects;
    }
    public void addSubject(Subject subject)
    {
        this._subjects.add(subject);
    }
    public void removeSubject(String subject)
    {
        for (int i = 0; i < this._subjects.size(); i++)
        {
            if (this._subjects.get(i).getSubject().equals(subject))
            {
                this._subjects.remove(i);
                return;
            }
        }
    }
    public void addGoal(String subject, Goal goal)
    {
        for (int i = 0; i < this._subjects.size(); i++)
        {
            if (this._subjects.get(i).getSubject().equals(subject))
            {
                this._subjects.get(i).addGoal(goal);
                return;
            }
        }
    }
    public void removeGoal(String subject, Goal goal)
    {
        for (int i = 0; i < this._subjects.size(); i++)
        {
            if (this._subjects.get(i).getSubject().equals(subject))
            {
                this._subjects.get(i).removeGoal(goal);
                return;
            }
        }
    }
    public boolean doesGoalExist(String subject, Goal goal)
    {
        for (int i = 0; i < this._subjects.size(); i++)
        {
            if (this._subjects.get(i).getSubject().equals(subject))
            {
                return this._subjects.get(i).doesGoalExist(goal);
            }
        }
        return false;
    }
    public boolean doesSubjectExist(String subject)
    {
        for(int i = 0; i < this._subjects.size(); i++)
        {
            if (this._subjects.get(i).getSubject().equals(subject))
            {
                return true;
            }
        }
        return false;
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
        for(int i = 0; i < this._subjects.size(); i++)
        {
            userStr += this._subjects.get(i).toString() + "\n";
        }
        return userStr;
    }
}
