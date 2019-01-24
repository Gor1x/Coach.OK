package com.example.fitnes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.List;

import APIParse.Exercise;

public class TrainingDescription extends AppCompatActivity implements ExerciseAdapterDescription.ListItemClickListener {

    private Toolbar toolbar;
    private View view;
    private int trainingID;
    private RecyclerView recyclerView;
    private List<Exercise> exerciseList;
    private final TrainingDescription current = this;
    private final Context context = this;
    private LayoutInflater li;
    private View promptsView;
    private ActionBar actionbar;
    private Training training;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_description);

        view = findViewById(R.id.coordinator_training_description);
        toolbar = findViewById(R.id.toolbar_training_description);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.list_description_exercise);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        Intent intent = getIntent();
        trainingID = intent.getIntExtra("TrainingName", 0);
        actionbar = getSupportActionBar();

        DBRoom.getTrainingForId(new DBRoom.OnCallbackTraining() {
            @Override
            public void onCallbackTraining(Training trainings) {
                training = trainings;
            }
        }, trainingID);
        //actionbar.setTitle(training.getName());

        DBRoom.getExerciseForTraining(new DBRoom.OnCallbackGetAllExercise() {
            @Override
            public void onCallback(List<Exercise> exercises) {
                exerciseList = exercises;
                ExerciseAdapterDescription adapter = new ExerciseAdapterDescription(exercises, current);
                recyclerView.setAdapter(adapter);
            }
        }, trainingID);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.edit_training) {
            Intent intent = new Intent(getApplicationContext(), ExerciseChoosing.class);
            intent.putExtra("ID", trainingID);
            startActivity(intent);

        } else if (item.getItemId() == R.id.delete_training) {
            DBRoom.deleteTrainingForId(trainingID);
            Snackbar.make(TrainingChoosing.getView(), "You have successfully deleted your training", Snackbar.LENGTH_LONG).show();
            finish();

        }else if(item.getItemId() == R.id.change_name){

            li = LayoutInflater.from(context);
            promptsView = li.inflate(R.layout.activity_alert_dialog, null);
            AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context);
            mDialogBuilder.setView(promptsView);
            final EditText userInput = (EditText) promptsView.findViewById(R.id.input_text);
            mDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //Вводим текст и отображаем в строке ввода на основном экране:
                                    String userText = userInput.getText().toString();
                                    actionbar.setTitle(userText);
                                    training.setName(userText);
                                    DBRoom.updateTrainingDB(new DBRoom.OnCallbackComplete() {
                                        @Override
                                        public void OmComplete() { }  }, training);
                                }
                            })
                    .setNegativeButton("Отмена",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            });

            AlertDialog alertDialog = mDialogBuilder.create();
            alertDialog.show();
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.training_description_menu, menu);
        return true;
    }


    @Override
    public void onListItemClick(int clickedItemIndex) {
        Exercise exercise = exerciseList.get(clickedItemIndex);
        Intent intent = new Intent(getApplicationContext(), ExerciseDescription.class);
        intent.putExtra("Id", Integer.toString(exercise.getId())); //Открытие активности с exercise description
        startActivity(intent);
    }
}