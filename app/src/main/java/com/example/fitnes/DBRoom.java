package com.example.fitnes;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import java.util.List;

import APIParse.Exercise;
import APIParse.MusclePackage.Muscle;

public class DBRoom {

    private static List<Exercise> exerciseList = null;

    public interface OnCallbackGetAllExercise {
        void onCallback(List<Exercise> exercises);
    }


    public interface OnCallbackComplete {
        void OmComplete();
    }

    public static void setExerciseList(List<Exercise> exerciseList) {
        DBRoom.exerciseList = exerciseList;
    }

    @SuppressLint("StaticFieldLeak")
    public static void musclesDB(final List<Muscle> muscles, final OnCallbackComplete callback) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... Void) {
                for (Muscle i : muscles)
                    MyApplication.getInstance().getDataBase().getMuscleDao().insertMuscles(i);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                callback.OmComplete();
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public static void exerciseDB(final List<Exercise> exercises, final OnCallbackComplete callback) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... Void) {
                for (Exercise i : exercises) {
                   if(i.getLanguage()== 2 && i.getName().length() > 4 && i.getDescription().length() >= 20) {
                       MyApplication.getInstance().getDataBase().getExerciseDao().insertExercise(i);
                       List<Integer> list = i.getMuscles();
                       for (Integer j : list) {
                           try {
                               MyApplication.getInstance().getDataBase().getMuscleExerciseDao().insert(new ExPlusMuscle(i.getId(), j));
                           } catch (Exception e) {
                               e.printStackTrace();
                           }
                       }
                   }
                }
                return null;
            }

                @Override
                protected void onPostExecute (Void aVoid){
                    callback.OmComplete();
                }
            }.

            execute();
        }

        @SuppressLint("StaticFieldLeak")
        public static List<Exercise> getAllExerciseDB (final OnCallbackGetAllExercise callback){
            new AsyncTask<Void, Void, List<Exercise>>() {
                @Override
                protected List<Exercise> doInBackground(Void... voids) {
                    return MyApplication.getInstance().getDataBase().getExerciseDao().getAllExercise();
                }

                @Override
                protected void onPostExecute(List<Exercise> exercises) {
                    callback.onCallback(exercises);
                }
            }.execute();
            return null;
        }
    }
