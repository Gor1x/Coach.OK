package com.example.fitnes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import java.util.List;

import APIParse.Exercise;
import APIParse.MusclePackage.APIHelperMuscle;
import APIParse.MusclePackage.Muscle;
import Room.DaoTraining;

public class ExerciseDescription extends AppCompatActivity {

    private TextView name;
    private TextView description;
    private TextView muscle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_decription);
        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        muscle = findViewById(R.id.muscle);
        Intent intent = getIntent();
        Integer id = Integer.parseInt(intent.getStringExtra("Id"));


        DBRoom.getExerciseDB(new DBRoom.OnCallbackGetExerciseId() {
            @Override
            public void onCallbackE(Exercise exercises) {
                description.setText(exercises.getDescription());
            }
        }, id);

        DBRoom.getMuscleForExercise(new DBRoom.OnCallbackGetMuscleForId() {
            @Override
            public void onCallbackM(List<Muscle> muscles) {
               String m = "Muscle: \n";
               for(Muscle i : muscles) {
                   if(m != "Muscle: \n") m = m + ", ";
                   m = m  + i.getName();
               }
               muscle.setText(m);
            }
        }, id);
    }
}
