package com.example.fitflow.Water_Food_Exercise_Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
/*
Class stores individual Food entries with accompanying methods. Each entry is stored in
an ArrayList is the FoodLog class.
 */
public class FoodEntry implements Serializable {
    private String name;
    private int calories;
    private int count;
    private LocalTime time;
    public FoodEntry(String name, int calories, int count, LocalTime time){
        this.name = name;
        this.calories = calories;
        this.count = count;
        this.time = time;
    }

    public void updateTotalCalories(int calories){
        this.calories = calories;
    }

    public String getName(){
        return this.name;
    }
    public int getTotalCalories(){
        return this.calories;
    }

    public void updateCount(int count){
        this.count = count;
    }

    public void changeDate(LocalTime time){
        this.time = time;
    }
    public LocalTime getDate(){
        return this.time;
    }

}
