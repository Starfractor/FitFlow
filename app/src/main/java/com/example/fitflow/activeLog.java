package com.example.fitflow;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.fitflow.Water_Food_Exercise_Data.ExerciseEntry;
import com.example.fitflow.Water_Food_Exercise_Data.ExerciseLog;
import com.example.fitflow.Water_Food_Exercise_Data.FoodEntry;
import com.example.fitflow.Water_Food_Exercise_Data.FoodLog;
import com.example.fitflow.Water_Food_Exercise_Data.WaterLog;
import com.example.fitflow.Water_Food_Exercise_Data.userInfo;
import android.content.Context;
import android.os.Build;
import android.util.Log;

public class activeLog {
    public static ExerciseLog exerciseLog;
    public static FoodLog foodLog;

    public static WaterLog waterLog;
    public static userInfo userInfo;

    public static boolean init = false;

    public static void init_active(){
        init = true;
    }
    public static void food_load_data(Context context){
        String today_date = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            today_date = LocalDate.now().toString();
        }
        File internal = context.getFilesDir();
        File directory_food = new File(internal, "saved_data/food");
        if (!directory_food.exists()) {
            directory_food.mkdirs();
        }

        boolean found = false;

        File[] food_files = directory_food.listFiles();
        if(food_files != null){
            for(File file : food_files){
                if(file.getName().equals(MainActivity.username + today_date + ".ser")){
                    found = true;
                    //Load in new file
                    try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file))) {
                        foodLog = (FoodLog) stream.readObject();
                        System.out.println(foodLog.totalCals);
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if(!found){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                foodLog = new FoodLog(LocalDate.now());
            }
        }

    }

    public static void water_load_data(Context context){
        String today_date = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            today_date = LocalDate.now().toString();
        }
        File internal = context.getFilesDir();
        File directory_water= new File(internal, "saved_data/water");
        if (!directory_water.exists()) {
            directory_water.mkdirs();
        }
        boolean found = false;
        File[] water_files = directory_water.listFiles();
        if(water_files != null){
            for(File file : water_files){
                if(file.getName().equals(MainActivity.username + today_date + ".ser")){
                    found = true;
                    //Load in new file
                    try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file))) {
                        waterLog = (WaterLog) stream.readObject();
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if(!found){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                waterLog = new WaterLog(LocalDate.now());
            }
        }

    }

    public static void user_info_load_data(Context context){
        File internal = context.getFilesDir();
        File directory_user = new File(internal, "saved_data/user");
        if (!directory_user.exists()) {
            directory_user.mkdirs();
        }
        File user_file = new File(directory_user, MainActivity.username + "userInfo.ser");
        if(user_file.exists()){
            try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(user_file))) {
                userInfo = (userInfo) stream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else{
            userInfo = new userInfo(0,0,"",0);
            userInfo.calculateAndSetRecommendedGoals();
        }
    }
}
