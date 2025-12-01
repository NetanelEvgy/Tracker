package com.example.myapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Subject
{

    private String _subject;
    private Map<String, Goal> _goals;

    public Subject()
    {
        this._subject = "";
        this._goals = new HashMap<>();
    }
    public Subject(String subject)
    {
        this._subject = subject;
        this._goals = new HashMap<>();
    }

    public void setSubject(String _subject)
    {
        this._subject = _subject;
    }
    public void setGoals(Map<String, Goal> goals)
    {
        this._goals = goals;
    }
    public String getSubject()
    {
        return this._subject;
    }
    public Map<String, Goal> getGoals()
    {
        return this._goals;
    }
    public void addGoal(Goal goal)
    {
        this._goals.put(goal.getGoal(), goal);
    }
    public void removeGoal(Goal goal)
    {
        this._goals.remove(goal.getGoal());
    }
    public boolean doesGoalExist(Goal goal)
    {
        return this._goals.containsKey(goal.getGoal());
    }
    public String toString()
    {
        String subjectStr = "Subject: " + this._subject + "\n";
        for (Goal goal : this._goals.values())
        {
            subjectStr += goal.toString() + "\n";
        }
        return subjectStr;
    }
}
