package com.example.fitnes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class ChooseExercise extends AppCompatActivity {

    private RecyclerView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_exercise);
        list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }

}
