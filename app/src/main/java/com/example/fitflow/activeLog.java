package com.example.fitflow;
import java.io.File;
import java.io.IOException;
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
                if(file.getName().equals(today_date + ".json")){
                    found = true;
                    //Load in new file
                }
            }
        }
        if(!found){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                activeLog.foodLog = new FoodLog(LocalDate.now());
            }
        }

    }

//    public static void exercise_load_data(Context context){
//        String today_date = "";
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            today_date = LocalDate.now().toString();
//        }
//        boolean found = false;
//        List<Path> saved_data_paths = new ArrayList<>();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            Path path = Paths.get("saved_data/exercise");
//            try(Stream<Path> paths = Files.walk(path)){
//                saved_data_paths = paths.collect(Collectors.toList());
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        if(saved_data_paths.size() > 0){
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                for(Path file : saved_data_paths) {
//                    if(file.getFileName().toString().equals(today_date)) {
//                        found = true;
//                    }
//                }
//            }
//        }
//        if(!found){
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                activeLog.foodLog = new FoodLog(LocalDate.now());
//            }
//        }
//    }
//
//    public static void water_load_data(Context context){
//        String today_date = "";
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            today_date = LocalDate.now().toString();
//        }
//        boolean found = false;
//        List<Path> saved_data_paths = new ArrayList<>();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            Path path = Paths.get("saved_data/water");
//            try(Stream<Path> paths = Files.walk(path)){
//                saved_data_paths = paths.collect(Collectors.toList());
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        if(saved_data_paths.size() > 0){
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                for(Path file : saved_data_paths) {
//                    if(file.getFileName().toString().equals(today_date)) {
//                        found = true;
//                    }
//                }
//            }
//        }
//        if(!found){
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                activeLog.exerciseLog = new ExerciseLog(LocalDate.now());
//            }
//        }
//    }
//    public static void user_load_data(Context context){
//        String today_date = "";
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            today_date = LocalDate.now().toString();
//        }
//        boolean found = false;
//        List<Path> saved_data_paths = new ArrayList<>();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            Path path = Paths.get("saved_data/user");
//            try(Stream<Path> paths = Files.walk(path)){
//                saved_data_paths = paths.collect(Collectors.toList());
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        if(saved_data_paths.size() > 0){
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                for(Path file : saved_data_paths) {
//                    if(file.getFileName().toString().equals(today_date)) {
//                        found = true;
//                    }
//                }
//            }
//        }
//
//    }

}
