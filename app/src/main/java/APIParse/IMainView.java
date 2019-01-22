package APIParse;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import APIParse.MusclePackage.Muscle;

public interface IMainView extends MvpView {

    void setMuscle(List<Muscle> muscles);
    void intentTrainingChoosing();
    void load();
    void error();
}