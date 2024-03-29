package com.example.fitflow;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        if(!activeLog.init) {
            activeLog.init_active();
            activeLog.water_load_data(this);
            activeLog.food_load_data(this);
            activeLog.user_info_load_data(this);
        }

        Intent serviceIntent = new Intent(this, stepService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent);
        }

        Button btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to MainActivity
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
                // Finish current activity
                finish();
            }
        });

        Button btnFoodRec = findViewById(R.id.btnFoodRecommendation);
        btnFoodRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to FoodRecActivity
                Intent intent = new Intent(HomeActivity.this, FoodRecActivity.class);
                startActivity(intent);
            }
        });

        Button btnAddWaterIntake = findViewById(R.id.btnAddWaterIntake);
        btnAddWaterIntake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to AddWaterIntakeActivity
                Intent intent = new Intent(HomeActivity.this, AddWaterIntakeActivity.class);
                startActivity(intent);
            }
        });
        
        // Add OnClickListener for the "Add Personal Data" button
        Button btnAddPersonalData = findViewById(R.id.btnAddPersonalData);
        btnAddPersonalData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to AddPersonalData activity
                Intent intent = new Intent(HomeActivity.this, AddPersonalData.class);
                startActivity(intent);
            }
        });
        
        // Add OnClickListener for the "Add Food" button
        Button btnAddFood = findViewById(R.id.btnAddFood);
        btnAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to AddFoodActivity
                Intent intent = new Intent(HomeActivity.this, AddFoodActivity.class);
                startActivity(intent);
            }
        });

        // Add OnClickListener for the "Food Preferences" button
        Button btnFoodPreferences = findViewById(R.id.btnFoodPreferences);
        btnFoodPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to AddFoodActivity
                Intent intent = new Intent(HomeActivity.this, FoodPreferencesActivity.class);
                startActivity(intent);
            }
        });

        // Add onClickListener for the "Lifestyle Score" button
        Button btnLifestyleScore = findViewById(R.id.btnLifestyleScore);

        btnLifestyleScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, LifestyleScoreActivity.class);
                startActivity(intent);
            }
        });
    }
}
