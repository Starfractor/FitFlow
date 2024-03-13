package com.example.fitflow.Water_Food_Exercise_Data;

import android.content.Context;

import com.example.fitflow.MainActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
/*
Stores food entries with accompanying methods. New food log should be made daily.
 */
public class FoodLog implements Serializable {
    private ArrayList<FoodEntry> entries;
    private LocalDate date;
    public int totalCals;

    public int steps;

    public FoodLog(LocalDate date){
        entries = new ArrayList<FoodEntry>();
        this.date = date;
        this.totalCals = 0;
        this.steps = 0;
    }

    public void addEntry(FoodEntry food){
        this.entries.add(food);
        this.totalCals += food.getTotalCalories();
    }
    public boolean removeEntry(String name){
        for(int i = 0; i< entries.size(); i++){
            if(this.entries.get(i).getName().equals(name)){
                this.totalCals -= this.entries.get(i).getTotalCalories();
                this.entries.remove(i);
                return true;
            }
        }
        return false;
    }

    public void saveLog(Context context){
        String ending = MainActivity.username + this.date.toString() + ".ser";
        File file = new File(context.getFilesDir(), "saved_data/food/" + ending);
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file))) {
            stream.writeObject(this);
            System.out.println("Saved foodLog");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDate(LocalDate date){
        this.date = date;
    }
    public LocalDate getDate(){
        return this.date;
    }

    public ArrayList<FoodEntry> getFoodLog(){
        return entries;
    }
}
