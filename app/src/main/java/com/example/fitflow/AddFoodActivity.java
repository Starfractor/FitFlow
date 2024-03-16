package com.example.fitflow;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.example.fitflow.Water_Food_Exercise_Data.FoodEntry;

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

        String cal_string = "Calories eaten today: " + activeLog.foodLog.totalCals;
        display.setText(cal_string);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String food = editFood.getText().toString();
                String time = editTime.getText().toString();
                String cals = editCals.getText().toString();
                String display_cals = display.getText().toString();

                if (!food.isEmpty() && !time.isEmpty() && !cals.isEmpty()) {
                    try {
                        LocalTime current_time = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
                        FoodEntry new_food = new FoodEntry(food, Integer.parseInt(cals), 1, current_time);
                        activeLog.foodLog.addEntry(new_food);
                        String cal_string = "Calories eaten today: " + activeLog.foodLog.totalCals;
                        display.setText(cal_string);
                        activeLog.foodLog.saveLog(AddFoodActivity.this);
                    } catch (DateTimeParseException e) {
                        Toast.makeText(AddFoodActivity.this, "Invalid time format. Please enter time in HH:mm format.", Toast.LENGTH_SHORT).show();
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
                String cals = "150"; 
                String time = getCurrentTime();
                addFoodEntry(food, cals, time);
            }
        });

        // Handle click event for Quick Meal button
        buttonQuickMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add Quick Meal
                String food = "Quick Meal";
                String cals = "600"; 
                String time = getCurrentTime();
                addFoodEntry(food, cals, time);
            }
        });
    }

    // Method to add a food entry
    private void addFoodEntry(String food, String cals, String time) {
        if (!time.isEmpty()) {
            try {
                LocalTime current_time = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
                FoodEntry new_food = new FoodEntry(food, Integer.parseInt(cals), 1, current_time);
                activeLog.foodLog.addEntry(new_food);
                String cal_string = "Calories eaten today: " + activeLog.foodLog.totalCals;
                display.setText(cal_string);
                activeLog.foodLog.saveLog(AddFoodActivity.this);
            } catch (DateTimeParseException e) {
                Toast.makeText(AddFoodActivity.this, "Invalid time format. Please enter time in HH:mm format.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(AddFoodActivity.this, "Please enter a time", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to get current time
    private String getCurrentTime() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        }
        return ""; // Return empty string
    }
}
