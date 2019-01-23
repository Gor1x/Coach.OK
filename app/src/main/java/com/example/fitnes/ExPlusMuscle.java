package com.example.fitnes;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import APIParse.Exercise;
import APIParse.MusclePackage.Muscle;

@Entity(tableName = "m_plus_e",
        primaryKeys = { "eId", "mId" },
        foreignKeys = {
                @ForeignKey(entity = Exercise.class,
                        parentColumns = "id",
                        childColumns = "eId"),
                @ForeignKey(entity = Muscle.class,
                        parentColumns = "id",
                        childColumns = "mId")
        })
public class ExPlusMuscle {
    public int eId;
    public int mId;

    public ExPlusMuscle(final int eId, final int mId) {
        this.eId = eId;
        this.mId = mId;
    }
}
