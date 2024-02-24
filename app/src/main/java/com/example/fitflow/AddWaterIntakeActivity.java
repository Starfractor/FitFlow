package com.example.fitflow;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddWaterIntakeActivity extends AppCompatActivity {

    private EditText editWater;
    private EditText editTime;

    private Button buttonAdd;
    private Button buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_water_intake);

        editWater = findViewById(R.id.editWater);
        editTime = findViewById(R.id.editTime);

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonCancel = findViewById(R.id.buttonCancel);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered water intake and time
                String water = editWater.getText().toString();
                String time = editTime.getText().toString();

                // Check if both fields are not empty
                if (!water.isEmpty() && !time.isEmpty()) {
                    // Add your logic here to handle adding water intake
                    // For now, just displaying a toast message
                    Toast.makeText(AddWaterIntakeActivity.this, "Water: " + water + "oz, Time: " + time, Toast.LENGTH_SHORT).show();
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
