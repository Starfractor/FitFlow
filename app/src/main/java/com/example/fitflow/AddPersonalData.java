package com.example.fitflow;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddPersonalData extends AppCompatActivity {

    private EditText editTextHeight;
    private EditText editTextWeight;
    private EditText editTextDate;
    private EditText editTextBodyType;
    private Button buttonAdd;
    private Button buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_personal_data);

        editTextHeight = findViewById(R.id.editTextHeight);
        editTextWeight = findViewById(R.id.editTextWeight);
        editTextDate = findViewById(R.id.editTextDate);
        editTextBodyType = findViewById(R.id.editTextBodyType);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonCancel = findViewById(R.id.buttonCancel);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String height = editTextHeight.getText().toString();
                String weight = editTextWeight.getText().toString();
                String date = editTextDate.getText().toString();
                String bodyType = editTextBodyType.getText().toString();

                // Check if all fields are filled
                if (!height.isEmpty() && !weight.isEmpty() && !date.isEmpty() && !bodyType.isEmpty()) {
                    // Perform your logic here to add personal data
                    // For now, let's just display a toast message
                    Toast.makeText(AddPersonalData.this, "Height: " + height + ", Weight: " + weight +
                            ", Date: " + date + ", Body Type: " + bodyType, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddPersonalData.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
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
