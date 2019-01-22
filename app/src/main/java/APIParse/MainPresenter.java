package APIParse;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

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
                getViewState().intentTrainingChoosing();
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
            }

            @Override
            public void onError() {
                getViewState().error();
            }
        });
    }

}

