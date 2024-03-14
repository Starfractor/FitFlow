package com.example.fitflow;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import java.util.List;

public class FoodRecActivity extends AppCompatActivity {

    private EditText queryEditText;
    private Button searchButton;
    private TextView resultTextView;
    private Context context; // Declare a Context variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_recommendation);
        context = this; // Initialize the Context variable

        // Start Python with AndroidPlatform
        Python.start(new AndroidPlatform(context));

        queryEditText = findViewById(R.id.queryEditText);
        searchButton = findViewById(R.id.searchButton);
        resultTextView = findViewById(R.id.resultTextView);

        // Set an action listener to detect when the user presses Enter on the keyboard
        queryEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    String query = queryEditText.getText().toString();
                    if (!query.isEmpty()) {
                        handleQuery(query);
                    } else {
                        Toast.makeText(FoodRecActivity.this, "Please enter a query", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
                return false;
            }
        });

        // Set an OnClickListener for the search button
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = queryEditText.getText().toString();
                if (!query.isEmpty()) {
                    handleQuery(query);
                } else {
                    Toast.makeText(FoodRecActivity.this, "Please enter a query", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void handleQuery(String query) {
        int maxCalories = Math.max(activeLog.userInfo.recommendedCalories - activeLog.foodLog.totalCals, 0);
        Python python = Python.getInstance();
        PyObject pyObject = python.getModule("food_recommendation").callAttr("recommend_food", query, maxCalories);

        try {
            List<PyObject> pyList = pyObject.asList();
            StringBuilder resultTextBuilder = new StringBuilder();
    
            // Append each item in results with a corresponding number
            for (int i = 0; i < pyList.size(); i++) {
                String itemText = pyList.get(i).toJava(String.class);
                resultTextBuilder.append(i + 1).append(". ").append(itemText).append("\n");
            }
    
            // Set the resultText to the resultTextView
            resultTextView.setText(resultTextBuilder.toString());
        } catch (ClassCastException e) {
            Toast.makeText(this, "Error: Unexpected return value from Python", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
