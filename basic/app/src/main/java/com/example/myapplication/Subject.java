package com.example.myapplication;

import java.util.ArrayList;

public class Subject
{

    private String _subject;
    private ArrayList<Goal> _goals;

    public Subject()
    {
        this._subject = "";
        this._goals = new ArrayList<>();
    }

    public Subject(String subject)
    {
        this._subject = subject;
        this._goals = new ArrayList<>();
    }

    public void setSubject(String _subject)
    {
        this._subject = _subject;
    }

    public String getSubject()
    {
        return this._subject;
    }
    public ArrayList<Goal> getGoals()
    {
        return this._goals;
    }
    public boolean addGoal(Goal goal)
    {
        if (!this._goals.contains(goal))
        {
            this._goals.add(goal);
            return true;
        }
        return false;
    }

    public boolean removeGoal(Goal goal)
    {
        return _goals.remove(goal);
    }
}
