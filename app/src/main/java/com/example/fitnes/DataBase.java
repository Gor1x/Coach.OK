package com.example.fitnes;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import APIParse.Exercise;
import Room.DaoExercise;

@Database(entities = {Exercise.class}, version = 2)
public abstract class DataBase extends RoomDatabase {
    public abstract DaoExercise daoExercise();
}