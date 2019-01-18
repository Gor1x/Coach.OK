package com.example.fitnes;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class TrainingChoosing extends AppCompatActivity implements TrainingAdapter.ListItemClickListener{

    private RecyclerView list;
    public ArrayList<Training> exerciseList = new ArrayList<>();
    private String[] names = new String[]{"Shoulders", "Legs", "Body", "Fourth", "Fifth", "Sixth", "Seventh"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_choosing);
        for (String v: names){
            exerciseList.add(new Training(v));
        }
        list = findViewById(R.id.list);


        list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        TrainingAdapter adapter = new TrainingAdapter(exerciseList, this);
        list.setAdapter(adapter);

    }


    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(getApplicationContext(), TrainingDescription.class);
        intent.putExtra("TrainingName", names[clickedItemIndex]);
        startActivity(intent);
    }

    @Override
    public void onListItemLongClick(int clickedItemIndex) { //Вызов редактора
        Toast.makeText(getApplicationContext(), "U did it", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), ExerciseChoosing.class);
        startActivity(intent);
    }


}
