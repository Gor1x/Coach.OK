package com.example.fitnes;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import APIParse.Exercise;
import APIParse.MusclePackage.Muscle;
import Room.DaoExercise;
import Room.DaoMuscle;
import Room.DaoMuscleExercise;
import Room.DaoTraining;
import Room.DaoTrainingExercise;

@Database(entities = { Exercise.class, Muscle.class, ExPlusMuscle.class, Training.class, TrainingPlusExercise.class }, version = 1)
public abstract class DataBaseMuscleExercise extends RoomDatabase {
    public abstract DaoMuscle getMuscleDao();
    public abstract DaoExercise getExerciseDao();
    public abstract DaoMuscleExercise getMuscleExerciseDao();
    public abstract DaoTrainingExercise getTrainingExerciseDao();
    public abstract DaoTraining getTrainingDao();

}