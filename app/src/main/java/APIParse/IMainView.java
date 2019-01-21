package APIParse;

import com.arellomobile.mvp.MvpView;

import java.util.List;

public interface IMainView extends MvpView {
    void getExercise(List<Exercise> exercises);
    void load();
    void error();
}