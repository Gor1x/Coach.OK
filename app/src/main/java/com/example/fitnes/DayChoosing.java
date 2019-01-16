package com.example.fitnes;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class DayChoosing extends AppCompatActivity implements ExercisesAdapter.ListItemClickListener{

    private RecyclerView list;
    public ArrayList<Exercise> exerciseList = new ArrayList<>();
    private String[] names = new String[]{"Shoulders", "Legs", "Body"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_choosing);
        for (String v: names){
            exerciseList.add(new Exercise(v));
        }
        list = findViewById(R.id.list);


        list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ExercisesAdapter adapter = new ExercisesAdapter(exerciseList, this);
        list.setAdapter(adapter);

    }


    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(getApplicationContext(), TrainingDescription.class);
        intent.putExtra("TrainingName", names[clickedItemIndex]);
        startActivity(intent);
    }
}
