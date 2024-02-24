package com.example.fitflow.Water_Food_Exercise_Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
/*
Stores exercise entries with accompanying methods. New exercise log should be made daily.
 */
public class ExerciseLog implements Serializable {
    private ArrayList<ExerciseEntry> entries;
    private LocalDate date;

    public ExerciseLog(LocalDate date){
        entries = new ArrayList<ExerciseEntry>();
        this.date = date;
    }

    public void addEntry(ExerciseEntry exercise){
        this.entries.add(exercise);
    }
    public boolean removeEntry(String name){
        for(int i = 0; i< entries.size(); i++){
            if(this.entries.get(i).getName().equals(name)){
                this.entries.remove(i);
                return true;
            }
        }
        return false;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }
    public LocalDate getDate(){
        return this.date;
    }

    public ArrayList<ExerciseEntry> getExerciseLog(){
        return entries;
    }
}
