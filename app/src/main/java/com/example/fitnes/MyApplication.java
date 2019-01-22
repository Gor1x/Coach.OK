package com.example.fitnes;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

public class MyApplication extends Application {

    private DataBase database;
    private DataBaseMuscles databaseMuscles;

    private static MyApplication instense;

    public static MyApplication getInstanse(){
        return instense;
    }

    public DataBase getDataBase(){
        return database;
    }

    public DataBaseMuscles getDataBaseMuscles(){
        return databaseMuscles;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        instense = this;
        databaseMuscles = Room.databaseBuilder(getApplicationContext(), DataBaseMuscles.class, "dbM").addMigrations(new Migration(1, 2) {
            @Override
            public void migrate(@NonNull SupportSQLiteDatabase database) {

            }
        }).build();

        database = Room.databaseBuilder(getApplicationContext(), DataBase.class, "dbE").addMigrations(new Migration(1, 2) {
            @Override
            public void migrate(@NonNull SupportSQLiteDatabase database) {

            }
        }).build();
    }
}
