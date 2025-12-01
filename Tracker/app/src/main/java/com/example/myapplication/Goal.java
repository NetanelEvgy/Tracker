package com.example.myapplication;

public class Goal
{
    private String _goal;
    private String _date;

    public Goal()
    {
        this._goal = "";
        this._date = "";
    }
    public Goal(String goal, String date)
    {
        this._goal = goal;
        this._date = date;
    }
    public void setGoal(String goal)
    {
        this._goal = goal;
    }
    public void setDate(String date)
    {
        this._date = date;
    }
    public String getGoal()
    {
        return this._goal;
    }
    public String getDate()
    {
        return this._date;
    }
    public String toString()
    {
        String goalStr = "Goal: " + this._goal + " Date: " + this._date;
        return goalStr;
    }
}
