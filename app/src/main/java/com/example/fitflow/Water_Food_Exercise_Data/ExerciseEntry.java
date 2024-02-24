package com.example.fitflow.Water_Food_Exercise_Data;

import java.io.Serializable;
import java.util.Date;
/*
Class stores individual Exercise entries with accompanying methods. Each entry is stored in
an ArrayList is the ExerciseLog class.
 */
public class ExerciseEntry implements Serializable {
    private int caloriesBurned;
    private Date date;
    private String name;

    public ExerciseEntry(String name, int cals, Date date){
        this.name = name;
        this.caloriesBurned = cals;
        this.date = date;
    }
    public void changeDate(Date date){
        this.date = date;
    }
    public Date getDate(){
        return this.date;
    }
    public String getName(){
        return this.name;
    }
}
