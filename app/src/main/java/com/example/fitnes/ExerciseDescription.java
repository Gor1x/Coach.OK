package com.example.fitnes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import APIParse.Exercise;

public class ExerciseDescription extends AppCompatActivity {

    private TextView description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_decription);
        description = findViewById(R.id.description_exercise);
        Intent intent = getIntent();
        Integer id = Integer.parseInt(intent.getStringExtra("Id"));

    }
}
