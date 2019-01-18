package com.example.fitnes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ExerciseDescription extends AppCompatActivity {

    private TextView description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_decription);
        description = findViewById(R.id.description);
        Intent intent = getIntent();
        description.setText(intent.getStringExtra("Id"));
    }
}
