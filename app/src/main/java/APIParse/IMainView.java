package APIParse;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

import retrofit2.Call;

public interface IMainView extends MvpView {
    void getExercise(ArrayList<Exercise> exercises);
    void load();
    void error();
}
