package com.example.fitflow.Water_Food_Exercise_Data;

import java.io.Serializable;
import java.time.LocalTime;
/*

Class stores individual water entries with accompanying methods. Each entry is stored in
an ArrayList is the WaterLog class.
 */
public class WaterEntry implements Serializable {
    private int Oz;
    private LocalTime time;
    private String name;
    public WaterEntry(String name, int Oz, LocalTime time){
        this.name = name;
        this.Oz = Oz;
        this.time = time;
    }
    public String getName(){
        return this.name;
    }
    public int getAmount(){
        return this.Oz;
    }
    public void changeAmount(int Oz){
        this.Oz = Oz;
    }
    public void changeDate(LocalTime time){
        this.time = time;
    }
    public LocalTime getDate(){
        return this.time;
    }
}
