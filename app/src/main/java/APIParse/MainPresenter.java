package APIParse;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@InjectViewState
public class MainPresenter extends MvpPresenter<IMainView> {


    public void info() {
        getViewState().load();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://wger.de")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);


        final ArrayList<APIParse.Exercise> arrayList = new ArrayList<>();

        for (int i = 1; i < 28; i++) {
            Call<ExerciseList> call = api.getPost(i, "json");
            call.enqueue(new Callback<ExerciseList>() {
                @Override
                public void onResponse(Call<ExerciseList> call, Response<ExerciseList> response) {

                    ExerciseList exerciseLists = response.body();
                    arrayList.addAll(exerciseLists.getResults());
                }

                @Override
                public void onFailure(Call<ExerciseList> call, Throwable t) {
                    try {
                        throw (t);
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }
            });
        }
        getViewState().getExercise(arrayList);
    }
}
