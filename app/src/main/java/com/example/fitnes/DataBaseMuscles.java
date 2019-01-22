package com.example.fitnes;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import APIParse.MusclePackage.Muscle;
import Room.DaoMuscle;

@Database(entities = {Muscle.class}, version = 2)
public abstract class DataBaseMuscles extends RoomDatabase {
    public abstract DaoMuscle daoMuscle();
}