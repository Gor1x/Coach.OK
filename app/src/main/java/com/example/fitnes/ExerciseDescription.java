package com.example.fitnes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import java.util.List;

import APIParse.Exercise;
import APIParse.MusclePackage.APIHelperMuscle;
import APIParse.MusclePackage.Muscle;
import Room.DaoTraining;

public class ExerciseDescription extends AppCompatActivity {

    private TextView description;
    private TextView muscle;
    private ActionBar actionbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_decription);
        description = findViewById(R.id.description);
        muscle = findViewById(R.id.muscle);
        actionbar = getSupportActionBar();
        Intent intent = getIntent();

        Integer id = Integer.parseInt(intent.getStringExtra("Id"));

        DBRoom.getExerciseDB(new DBRoom.OnCallbackGetExerciseId() {
            @Override
            public void onCallbackE(Exercise exercises) {
                String sb = exercises.getDescription();
                String nohmtl = sb.replaceAll("\\<.*?>","");
                actionbar.setTitle(exercises.getName());
                description.setText(nohmtl);
            }
        }, id);

        DBRoom.getMuscleForExercise(new DBRoom.OnCallbackGetMuscleForId() {
            @Override
            public void onCallbackM(List<Muscle> muscles) {
               String m = "Muscles: \n";

               for(Muscle i : muscles) {
                   if(m != "Muscles: \n") m = m + ", ";
                   m = m  + i.getName();
               }
               muscle.setText(m);
            }
        }, id);
    }
}
