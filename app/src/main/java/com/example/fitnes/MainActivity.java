package com.example.fitnes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;

import APIParse.Exercise;
import APIParse.ExerciseList;
import APIParse.IMainView;
import APIParse.MainPresenter;
import retrofit2.Call;


public class MainActivity extends MvpAppCompatActivity implements IMainView {

    @InjectPresenter
    public MainPresenter presenter;

    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(getApplicationContext(), TrainingChoosing.class);
        startActivity(intent);


        //presenter.info();
    }


    @Override
    public void getExercise(ArrayList<Exercise> exercises) {

    }

    @Override
    public void load() {

    }

    @Override
    public void error() {

    }
}
