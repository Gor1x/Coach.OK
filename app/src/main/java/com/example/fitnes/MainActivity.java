package com.example.fitnes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import APIParse.Exercise;
import APIParse.IMainView;
import APIParse.MainPresenter;
import APIParse.MusclePackage.Muscle;


public class MainActivity extends MvpAppCompatActivity implements IMainView {

    private ProgressBar progressBar;
    SharedPreferences prefs = null;
    private static List<Exercise> exercises;

    @InjectPresenter
    public MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar  = findViewById(R.id.progressBar);
        prefs = getSharedPreferences("com.mycompany.myAppName", MODE_PRIVATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.returnMuscle();

       /* if (prefs.getBoolean("firstrun", true)) {

            prefs.edit().putBoolean("firstrun", false).commit();
        }
        else{
            Intent intent = new Intent(getApplicationContext(), TrainingChoosing.class);
            startActivity(intent);
        }*/
    }

    @Override
    public void setMuscle(List<Muscle> muscles) {
        DBRoom.MusclesDB(muscles);
        presenter.returnExercise();
    }

    public static List<Exercise> getExercise(){
        return exercises;
    }

    @Override
    public void setExerciseDB(List<Exercise> exercises) {
        DBRoom.ExerciseDB(exercises);
    }

    @Override
    public void setExercise(List<Exercise> exercises) {
        progressBar.setVisibility(ProgressBar.GONE);
        MainActivity.exercises = exercises;
    }


    @Override
    public void IntentTrainingChoosing(){
        Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), TrainingChoosing.class);
        startActivity(intent);
    }

    @Override
    public void load() {
        progressBar.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    public void error() {
        Toast.makeText(getApplicationContext(), "JSON НЕ СКАЧАН", Toast.LENGTH_LONG).show();
    }
}