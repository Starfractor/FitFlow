package com.example.fitflow.Water_Food_Exercise_Data;

import android.content.Context;

import com.example.fitflow.MainActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/*
TBD. Stores information like weight, height, etc.
 */
public class userInfo implements Serializable {

    public int height;
    public int weight;
    public String sex;
    public int age;

    public int recommendedCalories;
    public double recommendedLiters;
    public int recommendedSteps;

    public ArrayList<String> foodPreferences;


    public userInfo(int height, int weight, String sex, int age){
        this.height = height;
        this.weight = weight;
        this.sex = sex;
        this.age = age;
        this.recommendedCalories = 0; //init value
        this.foodPreferences = new ArrayList<String>();
    }
    public void saveInfo(Context context) {
        File file = new File(context.getFilesDir(), "saved_data/user/" + MainActivity.username + "userInfo.ser");
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file))) {
            stream.writeObject(this);
            System.out.println("Saved userInfo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void calculateAndSetRecommendedGoals() {
        double height_centimeters = this.height * 2.54;
        double weight_kilograms = this.weight * 0.453592;
        
        int calories = 0;
        double liters = 0;
        int steps = 0;

        if (this.sex.equals("Male")){
            calories = (int) (8.362 + (13.397 * weight_kilograms) + (4.799 * height_centimeters) - (5.677 * 20));
            liters = (double) ((weight_kilograms * 0.03) / 1.2);
            steps = 10000;
        }
        else if(this.sex.equals("Female")){
            calories = (int) (447.593 + (9.247 * weight_kilograms) + (3.098 * height_centimeters) - (4.330 * 20));
            liters = (double) ((weight_kilograms * 0.025) / 1.2);
            steps = 10000;
        }
        else{
            calories = (int) (267.9775 + (11.322 * weight_kilograms) + (3.9485 * height_centimeters) - (5.0035 * 20));
            liters = (double) ((weight_kilograms * 0.0275) / 1.2);
            steps = 10000;
        }

        this.setRecommendedGoals(calories, liters, steps);
    }
    public void setRecommendedGoals(int calories, double liters, int steps) {
        this.recommendedCalories = calories;
        this.recommendedLiters = liters;
        this.recommendedSteps = steps;
    }
}
