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
    private static List<Exercise> exercises;

    @InjectPresenter
    public MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar  = findViewById(R.id.progressBar);
    }

    @Override
    protected void onResume() {

        super.onResume();
        presenter.returnMuscle();

    }

    @Override
    public void setMuscle(List<Muscle> muscles) {
        presenter.returnExercise();
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
        Toast.makeText(getApplicationContext(), "JSON НЕ СКАЧЕН", Toast.LENGTH_LONG).show();
    }
}
