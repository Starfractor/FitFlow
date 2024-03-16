package com.example.fitflow;

import com.example.fitflow.Water_Food_Exercise_Data.FoodEntry;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
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

        // Listener for adding food
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFoodEntry(editFood.getText().toString(), editTime.getText().toString(), editCals.getText().toString(), display.getText().toString());
            }
        });

        // Listener for canceling
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Configure Quick Snack and Quick Meal buttons
        configureQuickButton(findViewById(R.id.buttonQuickSnack), "Quick Snack", "150");
        configureQuickButton(findViewById(R.id.buttonQuickMeal), "Quick Meal", "600");
    }

    // Method to configure Quick Snack and Quick Meal buttons
    private void configureQuickButton(Button button, final String food, final String cals) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = getCurrentTime();
                addFoodEntry(food, time, cals, "Calories eaten today: " + Integer.toString(activeLog.foodLog.totalCals));
            }
        });
    }

    // Method to add a food entry
    private void addFoodEntry(String food, String time, String cals, String display_cals) {
        if (!food.isEmpty() && !time.isEmpty() && !cals.isEmpty()) {
            Toast.makeText(AddFoodActivity.this, "Added Food Item: " + food, Toast.LENGTH_SHORT).show(); // New toast message
            editFood.setText("");
            editTime.setText("");
            editCals.setText("");
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                LocalTime current_time = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
                FoodEntry new_food = new FoodEntry(food, Integer.parseInt(cals), 1, current_time);
                activeLog.foodLog.addEntry(new_food);
                String cal_string = "Calories eaten today: " + Integer.toString(activeLog.foodLog.totalCals);
                display.setText(cal_string);
                activeLog.foodLog.saveLog(AddFoodActivity.this);
                LocalTime mealTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
                LocalTime nextMealTime = NotificationService.calculateNextMealTime(mealTime, activeLog.foodLog.totalCals, activeLog.userInfo.recommendedCalories);
                NotificationService.cancelNotification(AddFoodActivity.this, "Meal Reminder");
                if (nextMealTime != null) {
                    NotificationService.scheduleNotification(AddFoodActivity.this, "Meal Reminder", "Don't forget to eat!", nextMealTime);
                }
            }
        } else {
            Toast.makeText(AddFoodActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
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
