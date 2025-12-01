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
}
