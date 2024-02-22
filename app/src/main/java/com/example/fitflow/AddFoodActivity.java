package com.example.fitflow;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
                String food = editFood.getText().toString();
                String time = editTime.getText().toString();

                if (!food.isEmpty() && !time.isEmpty()) {
                    // Perform your logic here to add food
                    // For now, let's just display a toast message
                    Toast.makeText(AddFoodActivity.this, "Food: " + food + ", Time: " + time, Toast.LENGTH_SHORT).show();
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
    }
}
