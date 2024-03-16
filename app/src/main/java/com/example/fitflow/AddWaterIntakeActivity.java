package com.example.fitflow;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitflow.Water_Food_Exercise_Data.FoodEntry;
import com.example.fitflow.Water_Food_Exercise_Data.WaterEntry;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AddWaterIntakeActivity extends AppCompatActivity {

    private EditText editWater;
    private EditText editTime;

    private static int count = 0;
    private TextView display;

    private Button buttonAdd;
    private Button buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_water_intake);

        editWater = findViewById(R.id.editWater);
        editTime = findViewById(R.id.editTime);

        display = findViewById(R.id.waterDisplay);
        buttonAdd = findViewById(R.id.buttonAdd);
        Button buttonCupWater = findViewById(R.id.buttonCupWater);
        Button buttonBottleWater = findViewById(R.id.buttonBottleWater);
        buttonCancel = findViewById(R.id.buttonCancel);

        String cal_string = "Today's water intake: " + Integer.toString(activeLog.waterLog.totalOz) + " oz";
        display.setText(cal_string);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered water intake and time
                String water = editWater.getText().toString();
                String time = editTime.getText().toString();
                String display_water = display.getText().toString();

                if (!water.isEmpty() && !time.isEmpty()) {
                    Toast.makeText(AddWaterIntakeActivity.this, "Water: " + water + " oz, Time: " + time, Toast.LENGTH_SHORT).show();
                    editWater.setText("");
                    editTime.setText("");
                    count += 1;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        LocalTime current_time = LocalTime.parse(time, DateTimeFormatter.ofPattern("H:mm"));
                        WaterEntry new_water = new WaterEntry(Integer.toString(count), Integer.parseInt(water), current_time);
                        activeLog.waterLog.addEntry(new_water);
                        String cal_string = "Today's water intake: " + Integer.toString(activeLog.waterLog.totalOz) + " oz";
                        display.setText(cal_string);
                        activeLog.waterLog.saveLog(AddWaterIntakeActivity.this);
                        LocalTime waterTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
                        LocalTime nextWaterTime = NotificationService.calculateNextWaterTime(waterTime, Integer.parseInt(water), activeLog.waterLog.totalOz);
                        NotificationService.cancelNotification(AddWaterIntakeActivity.this, "Water Reminder");
                        if (nextWaterTime != null) {
                            NotificationService.scheduleNotification(AddWaterIntakeActivity.this, "Water Reminder", "Don't forget to drink!", nextWaterTime);
                        }
                    }
                } else {
                    Toast.makeText(AddWaterIntakeActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonCupWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered water intake and time
                String water = "8";
                String time = getCurrentTime();
                String display_water = display.getText().toString();

                // Check if both fields are not empty
                if (!water.isEmpty() && !time.isEmpty()) {
                    Toast.makeText(AddWaterIntakeActivity.this, "Water: " + water + " oz, Time: " + time, Toast.LENGTH_SHORT).show();
                    editWater.setText("");
                    editTime.setText("");
                    count += 1;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        LocalTime current_time = LocalTime.parse(time, DateTimeFormatter.ofPattern("H:mm"));
                        WaterEntry new_water = new WaterEntry(Integer.toString(count), Integer.parseInt(water), current_time);
                        activeLog.waterLog.addEntry(new_water);
                        String cal_string = "Today's water intake: " + Integer.toString(activeLog.waterLog.totalOz) + " oz";
                        display.setText(cal_string);
                        activeLog.waterLog.saveLog(AddWaterIntakeActivity.this);
                        LocalTime nextWaterTime = NotificationService.calculateNextWaterTime(current_time, Integer.parseInt(water), activeLog.waterLog.totalOz);
                        NotificationService.cancelNotification(AddWaterIntakeActivity.this, "Water Reminder");
                        if (nextWaterTime != null) {
                            NotificationService.scheduleNotification(AddWaterIntakeActivity.this, "Water Reminder", "Don't forget to drink!", nextWaterTime);
                        }
                    }
                } else {
                    Toast.makeText(AddWaterIntakeActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonBottleWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered water intake and time
                String water = "24";
                String time = getCurrentTime();
                String display_water = display.getText().toString();

                // Check if both fields are not empty
                if (!water.isEmpty() && !time.isEmpty()) {
                    Toast.makeText(AddWaterIntakeActivity.this, "Water: " + water + " oz, Time: " + time, Toast.LENGTH_SHORT).show();
                    editWater.setText("");
                    editTime.setText("");
                    count += 1;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        LocalTime current_time = LocalTime.parse(time, DateTimeFormatter.ofPattern("H:mm"));
                        WaterEntry new_water = new WaterEntry(Integer.toString(count), Integer.parseInt(water), current_time);
                        activeLog.waterLog.addEntry(new_water);
                        String cal_string = "Today's water intake: " + Integer.toString(activeLog.waterLog.totalOz) + " oz";
                        display.setText(cal_string);
                        activeLog.waterLog.saveLog(AddWaterIntakeActivity.this);
                        LocalTime nextWaterTime = NotificationService.calculateNextWaterTime(current_time, Integer.parseInt(water), activeLog.waterLog.totalOz);
                        Toast.makeText(AddWaterIntakeActivity.this, "Next Reminder " + nextWaterTime, Toast.LENGTH_SHORT).show();
                        NotificationService.cancelNotification(AddWaterIntakeActivity.this, "Water Reminder");
                        if (nextWaterTime != null) {
                            NotificationService.scheduleNotification(AddWaterIntakeActivity.this, "Water Reminder", "Don't forget to drink!", nextWaterTime);
                        }
                    }
                } else {
                    Toast.makeText(AddWaterIntakeActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });        

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // Method to get current time
    private String getCurrentTime() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            return LocalTime.now().format(DateTimeFormatter.ofPattern("H:mm"));
        }
        return "";
    }
}
