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
    @Override
    public boolean equals(Object object) {
        if (this == object)
        {
            return true;
        }
        if (object == null || this.getClass() != object.getClass())
        {
            return false;
        }
        Goal goal = (Goal) object;
        return java.util.Objects.equals(_goal, goal._goal) && java.util.Objects.equals(_date, goal._date);
    }
    @Override
    public int hashCode() {
        return java.util.Objects.hash(_goal, _date);
    }
    public String toString()
    {
        String goalStr = "Goal: " + this._goal + " Date: " + this._date;
        return goalStr;
    }
}
