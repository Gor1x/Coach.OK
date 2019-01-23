package com.example.fitnes;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "dbTraining")
public class Training {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;

    public Training(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public int getId() {
        return id;
    }
}
