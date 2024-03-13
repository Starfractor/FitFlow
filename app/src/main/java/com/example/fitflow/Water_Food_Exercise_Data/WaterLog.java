package com.example.fitflow.Water_Food_Exercise_Data;

import android.content.Context;

import com.example.fitflow.MainActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
/*
Stores water entries with accompanying methods. New water log should be made daily.
 */
public class WaterLog implements Serializable {
    private ArrayList<WaterEntry> entries;
    private LocalDate date;

    public int totalOz;
    public WaterLog(LocalDate date){
        entries = new ArrayList<WaterEntry>();
        this.date = date;
        this.totalOz = 0;
    }
    public void saveLog(Context context){
        String ending = MainActivity.username + this.date.toString() + ".ser";
        File file = new File(context.getFilesDir(), "saved_data/water/" + ending);
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file))) {
            stream.writeObject(this);
            System.out.println("Saved waterLog");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addEntry(WaterEntry water){
        this.entries.add(water);
        this.totalOz += water.getAmount();
    }
    public boolean removeEntry(String name){
        for(int i = 0; i< entries.size(); i++){
            if(this.entries.get(i).getName().equals(name)){
                this.totalOz -= this.entries.get(i).getAmount();
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

    public ArrayList<WaterEntry> getWaterLog(){
        return entries;
    }
}