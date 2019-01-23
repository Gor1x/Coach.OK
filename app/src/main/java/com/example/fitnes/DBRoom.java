package com.example.fitnes;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import java.util.List;

import APIParse.Exercise;
import APIParse.MusclePackage.Muscle;

public class DBRoom {


    public interface OnCallbackGetAllExercise {
        void onCallback(List<Exercise> exercises);
    }

    public interface OnCallbackAllTraining {
        void onCallbackAllTraining(List<Training> trainings);
    }

    public interface OnCallbackTraining {
        void onCallbackTraining(Training trainings);
    }

    public interface OnCallbackGetMuscleForId{
        void onCallbackM(List<Muscle> muscles);
    }

    public interface OnCallbackGetExerciseId {
        void onCallbackE(Exercise exercises);
    }

    public interface OnCallbackComplete {
        void OmComplete();
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
    public static void trainingAndExerciseDB(final List<Exercise> exercises, final Training training, final OnCallbackComplete callback) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... Void) {
                for (Exercise i : exercises)
                    MyApplication.getInstance().getDataBase().getTrainingExerciseDao().insert(new TrainingPlusExercise(training.getId(), i.getId()));
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                callback.OmComplete();
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public static void getAllTrainingDb(final OnCallbackAllTraining callbackTr) {
        new AsyncTask<Void, Void, List<Training>>() {
            @Override
            protected List<Training> doInBackground(Void... Void) {
                return MyApplication.getInstance().getDataBase().getTrainingDao().getAllTraining();

            }
            @Override
            protected void onPostExecute(List<Training> training) {
                callbackTr.onCallbackAllTraining(training);
            }
        }.execute();
    }



    @SuppressLint("StaticFieldLeak")
    public static void addTrainingDB(final Training training, final OnCallbackComplete callback) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... Void) {
                    MyApplication.getInstance().getDataBase().getTrainingDao().insertTraining(training);
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
                           MyApplication.getInstance().getDataBase().getMuscleExerciseDao().insert(new ExPlusMuscle(i.getId(), j));
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
        @SuppressLint("StaticFieldLeak")
        public static Exercise getMuscleForExercise (final OnCallbackGetMuscleForId callbackM, final int id){
            new AsyncTask<Void, Void, List<Muscle>>() {
                @Override
                protected List<Muscle> doInBackground(Void... voids) {
                    return MyApplication.getInstance().getDataBase().getMuscleExerciseDao().getMuscleForExercise(id);
                }
                @Override
                protected void onPostExecute(List<Muscle> muscles) {
                    callbackM.onCallbackM(muscles);
                }
            }.execute();
            return null;
        }

        @SuppressLint("StaticFieldLeak")
        public static Exercise getExerciseDB (final OnCallbackGetExerciseId callbackE, final int id){
            new AsyncTask<Void, Void, Exercise>() {
                @Override
                protected Exercise doInBackground(Void... voids) {
                    return MyApplication.getInstance().getDataBase().getExerciseDao().getExerciseById(id);
                }
                @Override
                protected void onPostExecute(Exercise exercises) {
                    callbackE.onCallbackE(exercises);
                }
            }.execute();
            return null;
        }


        @SuppressLint("StaticFieldLeak")
        public static Exercise getExerciseForMuscleId (final OnCallbackGetAllExercise callbackE, final int id){
            new AsyncTask<Void, Void, List<Exercise>>() {
                @Override
                protected List<Exercise> doInBackground(Void... voids) {
                    return MyApplication.getInstance().getDataBase().getMuscleExerciseDao().getExerciseForMuscle(id);
                }
                @Override
                protected void onPostExecute(List<Exercise> exercises) {
                    callbackE.onCallback(exercises);
                }
            }.execute();
            return null;
        }


    @SuppressLint("StaticFieldLeak")
    public static Exercise getExerciseForTraining (final OnCallbackGetAllExercise callbackE, final int id){
        new AsyncTask<Void, Void, List<Exercise>>() {
            @Override
            protected List<Exercise> doInBackground(Void... voids) {
                return MyApplication.getInstance().getDataBase().getTrainingExerciseDao().getExerciseForTraining(id);
            }
            @Override
            protected void onPostExecute(List<Exercise> exercises) {
                callbackE.onCallback(exercises);
            }
        }.execute();
        return null;
    }


    public static void deleteTrainingForId(final int id) {
        final Training[] training = new Training[1];
        getTrainingForId(new OnCallbackTraining() {
            @Override
            public void onCallbackTraining(Training trainings) {
                training[0] = trainings;
            }
        }, id);
        deleteTrainingDB(new OnCallbackComplete() {
            @Override
            public void OmComplete() { }
        }, training[0]);
    }

    @SuppressLint("StaticFieldLeak")
    public static void deleteTrainingDB (final OnCallbackComplete callback, final Training training){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                MyApplication.getInstance().getDataBase().getTrainingDao().deleteTraining(training);
                return null;
            }
            @Override
            protected void onPostExecute(Void avoid) {
                callback.OmComplete();
            }
        }.execute();
    }


    @SuppressLint("StaticFieldLeak")
    public static void getTrainingForId (final OnCallbackTraining callback, final int id){
        new AsyncTask<Void, Void, Training>() {
            @Override
            protected Training doInBackground(Void... voids) {
                return MyApplication.getInstance().getDataBase().getTrainingDao().getTraining(id);
            }
            @Override
            protected void onPostExecute(Training training) {
                callback.onCallbackTraining(training);
            }
        }.execute();
    }




}
