package com.example.fitflow;

import android.widget.TextView;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class LifestyleScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lifestyle_scores_page);

        int caloriesConsumed = activeLog.foodLog.totalCals;
        int stepsTaken = activeLog.foodLog.steps;
        int waterIntake = activeLog.waterLog.totalOz;

        int caloriesRequested = 2000;
        int stepsRequested = 2000;
        int waterRequested = 2000;

        double caloriesRatio = (double) caloriesConsumed / caloriesRequested;
        double waterRatio = (double) waterIntake / waterRequested;
        double stepsRatio = (double) stepsTaken / stepsRequested;

        int lifestyleScorePercentage = (int) Math.round((caloriesRatio + waterRatio + stepsRatio) * 100 / 3);

        TextView textViewCalories = findViewById(R.id.textViewCalories);
        TextView textViewSteps = findViewById(R.id.textViewSteps);
        TextView textViewWaterIntake = findViewById(R.id.textViewWaterIntake);
        TextView textViewLifestyleScore = findViewById(R.id.progress_text);

        textViewCalories.setText("Calories Consumed: " + caloriesConsumed);
        textViewSteps.setText("Steps Taken: " + stepsTaken);
        textViewWaterIntake.setText("Water Intake: " + waterIntake);
        textViewLifestyleScore.setText(lifestyleScorePercentage + "%");
    }


}
