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
    public void removeGoal(Goal goal)
    {
        this._goals.remove(goal);
    }
    public boolean doesGoalExist(Goal goal)
    {
        return this._goals.contains(goal);
    }
    public String toString()
    {
        String subjectStr = "Subject: " + this._subject + "\n";
        for (int i = 0; i < this._goals.size(); i++)
        {
            subjectStr += this._goals.get(i).toString() + "\n";
        }
        return subjectStr;
    }
}
