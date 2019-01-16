package APIParse;

import com.arellomobile.mvp.MvpView;

public interface IMainView extends MvpView {
    void ok(String messenger);
    void load();
    void error();
}
