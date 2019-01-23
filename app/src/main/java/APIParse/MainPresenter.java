package APIParse;

import android.util.Log;
import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.fitnes.DBRoom;

import java.util.List;

import APIParse.MusclePackage.APIHelperMuscle;
import APIParse.MusclePackage.Muscle;


@InjectViewState
public class MainPresenter extends MvpPresenter<IMainView> {

    public void downloadInfo(){
       DBRoom.getAllExerciseDB(new DBRoom.OnCallbackGetAllExercise() {
           @Override
           public void onCallback(List<Exercise> exercises) {
               if (exercises == null || exercises.size() == 0)
                   startDownload();
               else {
                   DBRoom.setExerciseList(exercises);
                   goInTrainingChoosing();
               }
           }
       });
    }

    public void startDownload(){
        returnMuscle();
    }

    public void goInTrainingChoosing(){
        getViewState().intentTrainingChoosing();
    }

    public void returnMuscle(){
        getViewState().load();
        APIHelperMuscle.getInstance().loadMuscleFinal(new APIHelperMuscle.OnCallback<List<Muscle>>() {
            @Override
            public void onCallback(List<Muscle> response) {
                DBRoom.musclesDB(response, new DBRoom.OnCallbackComplete() {
                    @Override
                    public void OmComplete() {
                        returnExercise(); //Скачивание упражнений
                    }
                }); //Пуш ответа в базу данных
            }

            @Override
            public void onError() {
                getViewState().error();
            }
        });
    }

    public void returnExercise() {
        getViewState().load();
        Log.d("My Log", "returnExerciseIn");
        APIHelper.getInstance().loadExercise(new APIHelper.OnCallback<List<Exercise>>() {
            @Override
            public void onCallback(final List<Exercise> response) {

                DBRoom.exerciseDB(response, new DBRoom.OnCallbackComplete() {
                    @Override
                    public void OmComplete() {
                        DBRoom.setExerciseList(response); //Ответ -> лист упражнений
                        goInTrainingChoosing();
                    }
                }); //Пуш ответа в базу данных

            }

            @Override
            public void onError() {
                getViewState().error();
            }
        });
    }



}

