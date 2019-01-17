package APIParse;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@InjectViewState
public class MainPresenter extends MvpPresenter<IMainView> {


    public void info(){
        getViewState().load();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://wger.de")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);

        Call<ExerciseList> call = api.getPost();
        call.enqueue(new Callback<ExerciseList>() {
            @Override
            public void onResponse(Call<ExerciseList> call, Response<ExerciseList> response) {

                ExerciseList exerciseLists = response.body();
                String content = "";

                content += "count" + exerciseLists.getResults().get(0) + "\n";
                content += "Next "+ exerciseLists.getNext() + "\n";

                getViewState().ok(content);
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
}
