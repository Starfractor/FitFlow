package com.example.fitflow;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitflow.Water_Food_Exercise_Data.FoodEntry;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AddFoodActivity extends AppCompatActivity {

    private EditText editFood;
    private EditText editTime;

    private EditText editCals;

    private TextView display;
    private Button buttonAdd;
    private Button buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_food);

        editFood = findViewById(R.id.editFood);
        editTime = findViewById(R.id.editTextTime);
        editCals = findViewById(R.id.editCalories);
        display = findViewById(R.id.calorieDisplay);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonCancel = findViewById(R.id.buttonCancel);

        String cal_string = "Calories eaten today: " + Integer.toString(activeLog.foodLog.totalCals);
        display.setText(cal_string);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String food = editFood.getText().toString();
                String time = editTime.getText().toString();
                String cals = editCals.getText().toString();
                String display_cals = display.getText().toString();

                if (!food.isEmpty() && !time.isEmpty() && !cals.isEmpty()) {
                    // Perform your logic here to add food
                    // For now, let's just display a toast message
                    Toast.makeText(AddFoodActivity.this, "Added Food Item: " + food, Toast.LENGTH_SHORT).show();
                    editFood.setText("");
                    editTime.setText("");
                    editCals.setText("");
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        LocalTime current_time = LocalTime.parse(time, DateTimeFormatter.ofPattern("H:mm"));
                        FoodEntry new_food = new FoodEntry(food, Integer.parseInt(cals), 1, current_time);
                        activeLog.foodLog.addEntry(new_food);
                        String cal_string = "Calories eaten today: " + Integer.toString(activeLog.foodLog.totalCals);
                        display.setText(cal_string);
                        activeLog.foodLog.saveLog(AddFoodActivity.this);
                    }
                } else {
                    Toast.makeText(AddFoodActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Get references to the two buttons
        Button buttonQuickSnack = findViewById(R.id.buttonQuickSnack);
        Button buttonQuickMeal = findViewById(R.id.buttonQuickMeal);

        // Handle click event for Quick Snack button
        buttonQuickSnack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add Quick Snack
                String food = "Quick Snack";
                String cals = "150"; // Set calories for Quick Snack
                String time = getCurrentTime(); // Get current time
                addFoodEntry(food, cals, time); // Call method to add food entry
            }
        });

        // Handle click event for Quick Meal button
        buttonQuickMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add Quick Meal
                String food = "Quick Meal";
                String cals = "600"; // Set calories for Quick Meal
                String time = getCurrentTime(); // Get current time
                addFoodEntry(food, cals, time); // Call method to add food entry
            }
        });
    }

    // Method to add a food entry
    private void addFoodEntry(String food, String cals, String time) {
        // Perform your logic here to add food
        // For now, let's just display a toast message
        Toast.makeText(AddFoodActivity.this, "Added Food Item: " + food, Toast.LENGTH_SHORT).show();
        editFood.setText("");
        editTime.setText("");
        editCals.setText("");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalTime current_time = LocalTime.parse(time, DateTimeFormatter.ofPattern("H:mm"));
            FoodEntry new_food = new FoodEntry(food, Integer.parseInt(cals), 1, current_time);
            activeLog.foodLog.addEntry(new_food);
            String cal_string = "Calories eaten today: " + Integer.toString(activeLog.foodLog.totalCals);
            display.setText(cal_string);
            activeLog.foodLog.saveLog(AddFoodActivity.this);
        }
    }

    // Method to get current time
    private String getCurrentTime() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            return LocalTime.now().format(DateTimeFormatter.ofPattern("H:mm"));
        }
        return ""; // Return empty strin
    }
}
