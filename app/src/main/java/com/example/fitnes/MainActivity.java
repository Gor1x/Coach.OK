package com.example.fitnes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
import java.util.List;

import APIParse.Exercise;
import APIParse.ExerciseList;
import APIParse.IMainView;
import APIParse.MainPresenter;
import APIParse.MusclePackage.Muscle;
import retrofit2.Call;


public class MainActivity extends MvpAppCompatActivity implements IMainView {

    SharedPreferences prefs = null;
    @InjectPresenter
    public MainPresenter presenter;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences("com.mycompany.myAppName", MODE_PRIVATE);
    }

    @Override
    protected void onResume() {

        super.onResume();
        presenter.muscleReturn();
       /* if (prefs.getBoolean("firstrun", true)) {

            prefs.edit().putBoolean("firstrun", false).commit();
        }
        else{
            Intent intent = new Intent(getApplicationContext(), TrainingChoosing.class);
            startActivity(intent);
        }*/
    }

    @Override
    public void getExercise(List<Exercise> exercises) {

        Log.d("MyLog", exercises.toString());
        Log.d("MyLog",String.valueOf(exercises.size()));
        Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_LONG);
        Intent intent = new Intent(getApplicationContext(), TrainingChoosing.class);
        startActivity(intent);
    }

    @Override
    public void getMuscle(List<Muscle> muscles) {
        Log.d("MyLog",String.valueOf(muscles.size()));
        presenter.info();
    }

    @Override
    public void load() {

    }

    @Override
    public void error() {
        Toast.makeText(getApplicationContext(), "JSON НЕ СКАЧЕН", Toast.LENGTH_LONG);
    }
}
