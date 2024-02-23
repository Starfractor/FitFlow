package com.example.fitflow;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddFoodActivity extends AppCompatActivity {

    private EditText editFood;
    private EditText editTime;
    private Button buttonAdd;
    private Button buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_food);

        editFood = findViewById(R.id.editFood);
        editTime = findViewById(R.id.editTextTime);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonCancel = findViewById(R.id.buttonCancel);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String food = editFood.getText().toString().trim();
                String time = editTime.getText().toString().trim();

                if (TextUtils.isEmpty(food) || TextUtils.isEmpty(time)) {
                    Toast.makeText(AddFoodActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validate time format
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
                sdf.setLenient(false); // Disable lenient parsing

                try {
                    Date parsedTime = sdf.parse(time);
                    // If parsing successful, display the toast message
                    Toast.makeText(AddFoodActivity.this, "Food: " + food + ", Time: " + sdf.format(parsedTime), Toast.LENGTH_SHORT).show();
                } catch (ParseException e) {
                    // If parsing fails, show error message
                    Toast.makeText(AddFoodActivity.this, "Invalid time format. Please use HH:mm format.", Toast.LENGTH_SHORT).show();
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
}
