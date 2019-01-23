package com.example.fitnes;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import APIParse.Exercise;

@Entity(tableName = "tr_plus_ex",
        primaryKeys = { "trId", "exId" },
        foreignKeys = {
                @ForeignKey(entity = Exercise.class,
                        parentColumns = "id",
                        childColumns = "exId"),
                @ForeignKey(entity = Training.class,
                        parentColumns = "id",
                        childColumns = "trId")
        })
public class TrainingPlusExercise {
    public final int trId;
    public final int exId;

    public TrainingPlusExercise(final int trId, final int exId) {
        this.trId = trId;
        this.exId = exId;
    }
}