package com.example.fitnes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class TrainingDescription extends AppCompatActivity {

    private TextView description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_description);
        Log.d("MYLOG", "TrainingDescription");
        description = findViewById(R.id.description);
        Intent intent = getIntent();
        description.setText(intent.getStringExtra("TrainingName"));
    }
}
