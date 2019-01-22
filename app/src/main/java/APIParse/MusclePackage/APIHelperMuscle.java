package APIParse.MusclePackage;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIHelperMuscle {
    private static APIHelperMuscle instance;
    private APIMuscle apiService;

    public interface OnCallback<T> {
        void onCallback(T response);
        void onError();
    }

    public static APIHelperMuscle getInstance() {
        if (instance == null)
            instance = new APIHelperMuscle();
        return instance;
    }

    public APIHelperMuscle() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://wger.de")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(APIMuscle.class);
    }

    public void loadMuscleFinal(final OnCallback<List<Muscle>> callback){

        class MyTask extends AsyncTask<Void, Void, List<Muscle>> {

            @Override
            protected List<Muscle> doInBackground(Void... voids) {
                List<Muscle> result = new ArrayList<>();
                Call<MuscleList> call;
                int page = 1;
                Response<MuscleList> response;
                MuscleList muscleList;
                String next;

                do {
                    call = apiService.getPost(page, "json");
                    try {
                        response = call.execute();
                        muscleList = response.body();
                        result.addAll(muscleList.getResults());
                        next = (String) muscleList.getNext();
                    } catch (Exception e) {
                        return null;
                    }
                    page++;
                } while (next != null);

                return result;
            }

            @Override
            protected void onPostExecute(List<Muscle> muscles) {
                if (muscles != null)
                    callback.onCallback(muscles);
                else
                    callback.onError();
            }
        }

        new MyTask().execute();
    }
}
