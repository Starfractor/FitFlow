package com.example.fitflow;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitflow.Water_Food_Exercise_Data.WaterEntry;
import com.example.fitflow.Water_Food_Exercise_Data.userInfo;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AddPersonalData extends AppCompatActivity {

    private EditText editTextHeight;
    private EditText editTextWeight;
    private Spinner editSex;
    private EditText editTextAge;
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
        editTextAge = findViewById(R.id.editTextAge);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonCancel = findViewById(R.id.buttonCancel);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sex_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editSex.setAdapter(adapter);

        String height = editTextHeight.getText().toString();
        String weight = editTextWeight.getText().toString();
        String sex = editSex.getSelectedItem().toString();
        String age = editTextAge.getText().toString();


        editTextHeight.setText(Integer.toString(activeLog.userInfo.height));
        editTextWeight.setText(Integer.toString(activeLog.userInfo.weight));
        editTextAge.setText(Integer.toString(activeLog.userInfo.age));

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String height = editTextHeight.getText().toString();
                String weight = editTextWeight.getText().toString();
                String sex = editSex.getSelectedItem().toString();
                String age = editTextAge.getText().toString();

                // Check if all fields are filled
                Log.e("random", height + "|" + weight + "|" + sex + "|" + age);
                if (!height.isEmpty() && !weight.isEmpty() && !sex.isEmpty() && !age.isEmpty()) {
                    // Perform your logic here to add personal data
                    // For now, let's just display a toast message
                    Toast.makeText(AddPersonalData.this, "Height: " + height + ", Weight: " + weight +
                            ", Sex: " + sex + ", Age: " + age, Toast.LENGTH_SHORT).show();
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        ArrayList<String> old_preferences = activeLog.userInfo.foodPreferences;
                        activeLog.userInfo = new userInfo(Integer.parseInt(height), Integer.parseInt(weight), sex, Integer.parseInt(age));
                        activeLog.userInfo.foodPreferences = old_preferences;
                        activeLog.userInfo.calculateAndSetRecommendedGoals();
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
