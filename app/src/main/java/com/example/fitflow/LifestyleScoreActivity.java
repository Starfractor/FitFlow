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

        int caloriesRequested = activeLog.userInfo.recommendedCalories;
        int stepsRequested = activeLog.userInfo.recommendedSteps;
        int waterRequested = (int) (activeLog.userInfo.recommendedLiters * 33.814);

        double caloriesRatio = Math.min((double) caloriesConsumed / caloriesRequested, 0.3333);
        double waterRatio = Math.min((double) waterIntake / waterRequested, 0.3333);
        double stepsRatio = Math.min((double) stepsTaken / stepsRequested, 0.3333);

        int lifestyleScorePercentage = (int) Math.round((caloriesRatio + waterRatio + stepsRatio) * 100 / 3);

        TextView textViewCalories = findViewById(R.id.textViewCalories);
        TextView textViewSteps = findViewById(R.id.textViewSteps);
        TextView textViewWaterIntake = findViewById(R.id.textViewWaterIntake);
        TextView textViewLifestyleScore = findViewById(R.id.progress_text);

        textViewCalories.setText("Calories: " + caloriesConsumed + "/" + caloriesRequested + " cals");
        textViewSteps.setText("Steps Taken: " + stepsTaken + "/" + stepsRequested + " steps");
        textViewWaterIntake.setText("Water Intake: " + waterIntake + "/" + waterRequested + " oz");
        textViewLifestyleScore.setText(lifestyleScorePercentage + "%");
    }


}
