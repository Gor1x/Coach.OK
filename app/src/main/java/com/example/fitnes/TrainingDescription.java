package com.example.fitnes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import APIParse.Exercise;

public class TrainingDescription extends AppCompatActivity implements ExerciseAdapter.ListItemClickListener {

    private Toolbar toolbar;
    private View view;
    private int trainingId;
    private RecyclerView recyclerView;
    private List<Exercise> exerciseList;
    private final TrainingDescription current = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_description);
        view = findViewById(R.id.coordinator_training_description);
        toolbar = findViewById(R.id.toolbar_training_description);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.list_description_exercise);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        Intent intent = getIntent();
        trainingId = intent.getIntExtra("TrainingName", 0);

        DBRoom.getExerciseForTraining(new DBRoom.OnCallbackGetAllExercise() {
            @Override
            public void onCallback(List<Exercise> exercises) {
                exerciseList = exercises;
                ExerciseAdapter adapter = new ExerciseAdapter(exercises, current);
                recyclerView.setAdapter(adapter);
            }
        }, trainingId);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.edit_training) {
            Intent intent = new Intent(getApplicationContext(), ExerciseChoosing.class);
            intent.putExtra("Info", "training name");
            startActivity(intent);
        } else if (item.getItemId() == R.id.delete_training) {
            DBRoom.deleteTrainingForId(trainingId);
            Snackbar.make(TrainingChoosing.getView(), "You have successfully deleted your training", Snackbar.LENGTH_LONG).show();
            finish();
        }else if(item.getItemId() == R.id.change_name){

        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.training_description_menu, menu);
        return true;
    }


    @Override
    public void onListItemClick(int clickedItemIndex) {
        Exercise exercise = exerciseList.get(clickedItemIndex);
        Intent intent = new Intent(getApplicationContext(), ExerciseDescription.class);
        intent.putExtra("Id", Integer.toString(exercise.getId())); //Открытие активности с exercise description
        startActivity(intent);
    }
}