package com.example.fitnes;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;

public class TrainingChoosing extends AppCompatActivity implements TrainingAdapter.ListItemClickListener{

    private RecyclerView list;
    public ArrayList<Training> exerciseList = new ArrayList<>();
    private ArrayList<String> names = new ArrayList<>(Arrays.asList("Shoulders", "Legs", "Seventh"));
    private Toolbar toolbar;
    private TrainingAdapter adapter;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_choose);

        view = findViewById(R.id.coordinator);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        for (String v: names){
            exerciseList.add(new Training(v));
        }
        list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new TrainingAdapter(exerciseList, this);
        list.setAdapter(adapter);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_training) {
            exerciseList.add(new Training("something"));
            names.add("something");
            adapter.notifyDataSetChanged();
            Snackbar.make(view, "You have added training", Snackbar.LENGTH_LONG).show();
        }
        return true;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.choose_training_menu, menu);
        return true;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(getApplicationContext(), TrainingDescription.class);
        intent.putExtra("TrainingName", names.get(clickedItemIndex));
        startActivity(intent);
    }


}
