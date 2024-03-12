package com.example.fitflow;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitflow.Water_Food_Exercise_Data.WaterEntry;
import com.example.fitflow.Water_Food_Exercise_Data.userInfo;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AddPersonalData extends AppCompatActivity {

    private EditText editTextHeight;
    private EditText editTextWeight;
    private EditText editSex;
    private EditText editTextBodyType;
    private Button buttonAdd;
    private Button buttonCancel;
    private TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_personal_data);

        editTextHeight = findViewById(R.id.editTextHeight);
        editTextWeight = findViewById(R.id.editTextWeight);
        editSex = findViewById(R.id.editSex);
        editTextBodyType = findViewById(R.id.editTextBodyType);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonCancel = findViewById(R.id.buttonCancel);

        String height = editTextHeight.getText().toString();
        String weight = editTextWeight.getText().toString();
        String sex = editSex.getText().toString();
        String bodyType = editTextBodyType.getText().toString();

        editTextHeight.setText(Integer.toString(activeLog.userInfo.height));
        editTextWeight.setText(Integer.toString(activeLog.userInfo.weight));
        editSex.setText(activeLog.userInfo.sex);
        editTextBodyType.setText(activeLog.userInfo.bodyType);


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String height = editTextHeight.getText().toString();
                String weight = editTextWeight.getText().toString();
                String sex = editSex.getText().toString();
                String bodyType = editTextBodyType.getText().toString();
                // Check if all fields are filled
                Log.e("random", height + "|" + weight + "|" + sex + "|" + bodyType);
                if (!height.isEmpty() && !weight.isEmpty() && !sex.isEmpty() && !bodyType.isEmpty()) {
                    // Perform your logic here to add personal data
                    // For now, let's just display a toast message
                    Toast.makeText(AddPersonalData.this, "Height: " + height + ", Weight: " + weight +
                            ", Sex: " + sex + ", Body Type: " + bodyType, Toast.LENGTH_SHORT).show();
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        activeLog.userInfo = new userInfo(Integer.parseInt(height), Integer.parseInt(weight), sex, bodyType);
                        activeLog.userInfo.saveInfo(AddPersonalData.this);
                    }
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
