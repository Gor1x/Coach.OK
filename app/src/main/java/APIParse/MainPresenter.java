package APIParse;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;


@InjectViewState
public class MainPresenter extends MvpPresenter<IMainView> {


    public void info() {
        getViewState().load();

        APIHelper.getInstance().loadExercise(new APIHelper.OnCallback<List<Exercise>>() {
            @Override
            public void onCallback(List<Exercise> response) {
                getViewState().getExercise(response);
            }

            @Override
            public void onError() {
                getViewState().error();
            }
        });


    }
}