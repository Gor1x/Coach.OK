package com.example.fitnes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import APIParse.Exercise;

public class ExerciseChoosing extends AppCompatActivity implements ExerciseAdapter.ListItemClickListener{

    private ArrayList<Exercise> exerciseList;
    private RecyclerView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_exercise);
        list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        exerciseList = new ArrayList<>();
        exerciseList.add(new Exercise("Doing something", "Chilling"));
        ExerciseAdapter adapter = new ExerciseAdapter(exerciseList, this);
        list.setAdapter(adapter);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Exercise exercise = exerciseList.get(clickedItemIndex);
        Intent intent = new Intent(getApplicationContext(), ExerciseDescription.class);
        intent.putExtra("Id", Integer.toString(exercise.getId())); //Открытие активности с exercise decription
        startActivity(intent);
    }

}
