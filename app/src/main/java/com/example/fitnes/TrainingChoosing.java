package com.example.fitnes;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class TrainingChoosing extends AppCompatActivity implements TrainingAdapter.ListItemClickListener{

    private RecyclerView list;
    public List<Training> trainingList = new ArrayList<>();
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

        list = findViewById(R.id.list);
        final TrainingChoosing current = this;
        DBRoom.getAllTrainingDb(new DBRoom.OnCallbackAllTraining() {
            @Override
            public void onCallbackAllTraining(List<Training> trainings) {
                trainingList = trainings;
                adapter = new TrainingAdapter(trainingList, current);
                adapter.notifyDataSetChanged();
                list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                list.setAdapter(adapter);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_training) {
            Training training = new Training("Something");
            trainingList.add(new Training("something"));
            DBRoom.addTrainingDB(training, new DBRoom.OnCallbackComplete() {
                @Override
                public void OmComplete() {
                    adapter.notifyDataSetChanged();
                }
            });
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
        intent.putExtra("TrainingName", String.valueOf(clickedItemIndex));
        startActivity(intent);
    }


}
