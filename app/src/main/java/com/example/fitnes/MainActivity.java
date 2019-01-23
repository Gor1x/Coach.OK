package com.example.fitnes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    private static ProgressBar progressBar;

    @InjectPresenter
    public MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar  = findViewById(R.id.progressBar);
        presenter.downloadInfo();
    }

    @Override
    public void intentTrainingChoosing() {
        Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), TrainingChoosing.class);
        startActivity(intent);
    }

    private static void setProgress(boolean flag){
        if (flag){
            progressBar.setVisibility(View.VISIBLE);
        }
        else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void load() {
        setProgress(true);
    }

    @Override
    public void error() {
        setProgress(false);
        Toast.makeText(getApplicationContext(), "Internet problems", Toast.LENGTH_LONG).show();
    }
}
