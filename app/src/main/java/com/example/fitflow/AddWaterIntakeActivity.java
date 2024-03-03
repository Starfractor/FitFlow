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

                // Check if both fields are not empty
                if (!water.isEmpty() && !time.isEmpty()) {
                    // Add your logic here to handle adding water intake
                    // For now, just displaying a toast message
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
                    }
                } else {
                    Toast.makeText(AddWaterIntakeActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the activity when "Cancel" button is clicked
            }
        });
    }
}
