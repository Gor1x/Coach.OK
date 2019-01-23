package com.example.fitnes;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

public class MyApplication extends Application {

    private DataBaseMuscleExercise dataBase;

    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    public DataBaseMuscleExercise getDataBase() {
        return dataBase;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        dataBase = Room.databaseBuilder(getApplicationContext(), DataBaseMuscleExercise.class, "coach_ok.db").addMigrations(new Migration(1, 2) {
            @Override
            public void migrate(@NonNull SupportSQLiteDatabase database) {

            }
        }).build();
    }
}
