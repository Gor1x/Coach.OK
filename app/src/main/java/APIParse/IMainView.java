package APIParse;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import APIParse.MusclePackage.Muscle;

public interface IMainView extends MvpView {
    void getExercise(List<Exercise> exercises);
    void getMuscle(List<Muscle> muscles);
    void load();
    void error();
}