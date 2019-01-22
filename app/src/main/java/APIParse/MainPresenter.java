package APIParse;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.fitnes.MainActivity;
import com.example.fitnes.MyApplication;

import java.util.List;

import APIParse.MusclePackage.APIHelperMuscle;
import APIParse.MusclePackage.Muscle;


@InjectViewState
public class MainPresenter extends MvpPresenter<IMainView> {

    public void returnExercise() {
        getViewState().load();

        Log.d("My Log", "returnExerciseIn");
        APIHelper.getInstance().loadExercise(new APIHelper.OnCallback<List<Exercise>>() {
            @Override
            public void onCallback(List<Exercise> response) {
                getViewState().setExerciseDB(response);
                getViewState().setExercise(response);
                getViewState().IntentTrainingChoosing();
            }


            @Override
            public void onError() {
                getViewState().error();
            }
        });
    }
    public void returnMuscle(){
        getViewState().load();
        APIHelperMuscle.getInstance().loadMusclefinal(new APIHelperMuscle.OnCallback<List<Muscle>>() {
            @Override
            public void onCallback(List<Muscle> response) {
                getViewState().setMuscle(response);
            }

            @Override
            public void onError() {
                getViewState().error();
            }
        });
    }

}

