package com.example.fitflow;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Set;

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
        CheckBox healthyCheckbox = findViewById(R.id.checkbox_gluten_free);
        Button backButton = findViewById(R.id.backButton);

        loadPreferences();

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

        healthyCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateSelectedPreferences("Healthy", isChecked);
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

    private void savePreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("FitFlowPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(activeLog.userInfo.foodPreferences);
        editor.putString("FoodPreferences", json);
        editor.apply();
    }

    private void loadPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("FitFlowPreferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("FoodPreferences", null);
        Type type = new TypeToken<Set<String>>() {}.getType();
        Set<String> savedPreferences = gson.fromJson(json, type);

        if (savedPreferences != null) {
            activeLog.userInfo.foodPreferences.clear();
            activeLog.userInfo.foodPreferences.addAll(savedPreferences);
        }


        updateCheckboxesBasedOnPreferences();
    }

    private void updateCheckboxesBasedOnPreferences() {
        CheckBox indianCheckbox = findViewById(R.id.checkbox_indian);
        CheckBox chineseCheckbox = findViewById(R.id.checkbox_chinese);
        CheckBox italianCheckbox = findViewById(R.id.checkbox_italian);
        CheckBox japaneseCheckbox = findViewById(R.id.checkbox_japanese);
        CheckBox mexicanCheckbox = findViewById(R.id.checkbox_mexican);
        CheckBox vegetarianCheckbox = findViewById(R.id.checkbox_vegetarian);
        CheckBox veganCheckbox = findViewById(R.id.checkbox_vegan);
        CheckBox healthyCheckbox = findViewById(R.id.checkbox_gluten_free);

        indianCheckbox.setChecked(activeLog.userInfo.foodPreferences.contains("Indian"));
        chineseCheckbox.setChecked(activeLog.userInfo.foodPreferences.contains("Chinese"));
        italianCheckbox.setChecked(activeLog.userInfo.foodPreferences.contains("Italian"));
        japaneseCheckbox.setChecked(activeLog.userInfo.foodPreferences.contains("Japanese"));
        mexicanCheckbox.setChecked(activeLog.userInfo.foodPreferences.contains("Mexican"));
        vegetarianCheckbox.setChecked(activeLog.userInfo.foodPreferences.contains("Vegetarian"));
        veganCheckbox.setChecked(activeLog.userInfo.foodPreferences.contains("Vegan"));
        healthyCheckbox.setChecked(activeLog.userInfo.foodPreferences.contains("Healthy"));

    }

    @Override
    protected void onPause() {
        super.onPause();
        savePreferences();
    }







}
