package com.example.fitnes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
    private static List<Exercise> exercises;

    @InjectPresenter
    public MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar  = findViewById(R.id.progressBar);
        downloadMuscles();
    }

    @Override
    public void setMuscle(List<Muscle> muscles) {
       //Какой то установщик мышц ахахах.
    }


    @Override
    public void intentTrainingChoosing() {
        Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), TrainingChoosing.class);
        startActivity(intent);
    }

    public static List<Exercise> getExercise(){
        return exercises;
    }

    private void setProgress(boolean flag){
        if (flag){
            progressBar.setVisibility(View.VISIBLE);
        }
        else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void downloadMuscles() {
        presenter.returnMuscle();
    }

    @Override
    public void downloadExercise() {
        presenter.returnExercise();
    }

    @Override
    public void setExerciseDB(List<Exercise> exercises) {
        DBRoom.exerciseDB(exercises);
    }

    @Override
    public void setExercise(List<Exercise> exercises) {
        setProgress(false);
        MainActivity.exercises = exercises;
        Intent intent = new Intent(getApplicationContext(), TrainingChoosing.class);
        startActivity(intent);
    }

    @Override
    public void load() {
        setProgress(true);
    }

    @Override
    public void error() {
        setProgress(false);
        Toast.makeText(getApplicationContext(), "JSON НЕ СКАЧАН", Toast.LENGTH_LONG).show();
    }
}
