package com.example.fitnes;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import java.util.List;

import APIParse.Exercise;
import APIParse.MainPresenter;
import APIParse.MusclePackage.Muscle;

public class DBRoom {

    private static List<Exercise> exerciseList = null;

    public static List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public static void setExerciseList(List<Exercise> exerciseList) {
        DBRoom.exerciseList = exerciseList;
    }

    @SuppressLint("StaticFieldLeak")
    public static void musclesDB(final List<Muscle> muscles) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... Void) {
                for(Muscle i : muscles)
                    MyApplication.getInstanse().getDataBaseMuscles().daoMuscle().insertMuscles(i);
                return null;
            }
        }.execute();
    }
    @SuppressLint("StaticFieldLeak")
    public static void exerciseDB(final List<Exercise> exercises) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... Void) {
                for(Exercise i : exercises)
                    MyApplication.getInstanse().getDataBase().daoExercise().insertExercise(i);
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public static void getAllExerciseDB(final MainPresenter presenter) {
        new AsyncTask<Void, Void, List<Exercise>>() {
            @Override
            protected List<Exercise> doInBackground(Void... voids) {
                return MyApplication.getInstanse().getDataBase().daoExercise().getAllExercise();
            }

            @Override
            protected void onPostExecute(List<Exercise> exercises) {
                exerciseList = exercises;
                if (exercises == null){
                    presenter.startDownload();
                }
                else {
                    presenter.goInTrainingChoosing();
                }
            }
        }.execute();
    }
}
