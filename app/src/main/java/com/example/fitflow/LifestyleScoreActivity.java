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

        double caloriesRatio = (double) caloriesConsumed / caloriesRequested;
        double waterRatio = (double) waterIntake / waterRequested;
        double stepsRatio = (double) stepsTaken / stepsRequested;

        int lifestyleScorePercentage = (int) Math.round((caloriesRatio + waterRatio + stepsRatio) * 100 / 3);

        TextView textViewCalories = findViewById(R.id.textViewCalories);
        TextView textViewSteps = findViewById(R.id.textViewSteps);
        TextView textViewWaterIntake = findViewById(R.id.textViewWaterIntake);
        TextView textViewLifestyleScore = findViewById(R.id.progress_text);

        textViewCalories.setText("Calories: " + caloriesConsumed + "/" + caloriesRequested);
        textViewSteps.setText("Steps Taken: " + stepsTaken + "/" + stepsRequested);
        textViewWaterIntake.setText("Water Intake: " + waterIntake + "/" + waterRequested);
        textViewLifestyleScore.setText(lifestyleScorePercentage + "%");
    }


}
