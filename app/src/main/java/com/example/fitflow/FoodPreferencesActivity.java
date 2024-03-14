package com.example.fitflow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FoodPreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_preferences);
        CheckBox indianCheckbox = findViewById(R.id.checkbox_indian);
        CheckBox chineseCheckbox = findViewById(R.id.checkbox_chinese);
        CheckBox italianCheckbox = findViewById(R.id.checkbox_italian);
        CheckBox japaneseCheckbox = findViewById(R.id.checkbox_japanese);
        CheckBox mexicanCheckbox = findViewById(R.id.checkbox_mexican);
        CheckBox vegetarianCheckbox = findViewById(R.id.checkbox_vegetarian);
        CheckBox veganCheckbox = findViewById(R.id.checkbox_vegan);
        CheckBox glutenCheckbox = findViewById(R.id.checkbox_gluten_free);
        Button backButton = findViewById(R.id.backButton);


        // Set up listeners for each checkbox
        indianCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateSelectedPreferences("Indian", isChecked);
            }
        });

        chineseCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateSelectedPreferences("Chinese", isChecked);
            }
        });

        italianCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateSelectedPreferences("Italian", isChecked);
            }
        });

        japaneseCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateSelectedPreferences("Japanese", isChecked);
            }
        });

        mexicanCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateSelectedPreferences("Mexican", isChecked);
            }
        });

        vegetarianCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateSelectedPreferences("Vegetarian", isChecked);
            }
        });

        veganCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateSelectedPreferences("Vegan", isChecked);
            }
        });

        glutenCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateSelectedPreferences("Gluten-free", isChecked);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodPreferencesActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }

    private void updateSelectedPreferences(String preference, boolean isChecked) {
        if (isChecked) {
            activeLog.userInfo.foodPreferences.add(preference);
        } else {
            activeLog.userInfo.foodPreferences.remove(preference);
        }


    }



}
