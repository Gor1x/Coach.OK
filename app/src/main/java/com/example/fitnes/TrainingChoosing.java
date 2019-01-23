package com.example.fitnes;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TrainingChoosing extends AppCompatActivity implements TrainingAdapter.ListItemClickListener{

    private RecyclerView list;
    public List<Training> trainingList = new ArrayList<>();
    private Toolbar toolbar;
    private TrainingAdapter adapter;
    private View view;
    final TrainingChoosing current = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_choose);
        view = findViewById(R.id.coordinator);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        DBRoom.getAllTrainingDb(new DBRoom.OnCallbackAllTraining() {
            @Override
            public void onCallbackAllTraining(List<Training> trainings) {
                trainingList = trainings;
                adapter = new TrainingAdapter(trainingList, current);
                list.setAdapter(adapter);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR
            Toast.makeText(getApplicationContext(), "ХА-ХА-ХА",Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_training) {
            DBRoom.addTrainingDB(new Training("Something"), new DBRoom.OnCallbackComplete() {
                @Override
                public void OmComplete() {

                    DBRoom.getAllTrainingDb(new DBRoom.OnCallbackAllTraining() {
                        @Override
                        public void onCallbackAllTraining(List<Training> trainings) {
                            trainingList = trainings;
                            //adapter = new TrainingAdapter(trainingList, current);
                            adapter.setData(trainingList);
                            adapter.notifyDataSetChanged();
                            //list.setAdapter(adapter);
                        }
                    });

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
        int id = trainingList.get(clickedItemIndex).getId();
        intent.putExtra("TrainingName", id);
        startActivity(intent);
    }


}
