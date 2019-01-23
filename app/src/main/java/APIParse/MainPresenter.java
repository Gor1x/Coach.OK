package APIParse;

import android.util.Log;

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
               if (exercises == null)
                   startDownload();
               else
                   goInTrainingChoosing();
           }
       });
    }

    public void startDownload(){
        returnMuscle();
    }

    public void goInTrainingChoosing(){
        getViewState().intentTrainingChoosing();
    }

    public void returnExercise() {
        Log.d("My Log", "returnExerciseIn");
        APIHelper.getInstance().loadExercise(new APIHelper.OnCallback<List<Exercise>>() {
            @Override
            public void onCallback(List<Exercise> response) {
                DBRoom.exerciseDB(response); //Пуш ответа в базу данных
                DBRoom.setExerciseList(response); //Ответ -> лист упражнений
                goInTrainingChoosing();
            }

            @Override
            public void onError() {
                getViewState().error();
            }
        });
    }

    public void returnMuscle(){
        getViewState().load();
        APIHelperMuscle.getInstance().loadMuscleFinal(new APIHelperMuscle.OnCallback<List<Muscle>>() {
            @Override
            public void onCallback(List<Muscle> response) {
                getViewState().setMuscle(response);
                returnExercise(); //Скачивание упражнений
            }

            @Override
            public void onError() {
                getViewState().error();
            }
        });
    }

}

