package APIParse;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIHelper {

    private static APIHelper instance;
    private API apiService;

    interface OnCallback<T> {
        void onCallback(T response);
        void onError();
    }

    public static APIHelper getInstance() {
        if (instance == null)
            instance = new APIHelper();
        return instance;
    }

    private APIHelper() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://wger.de")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(API.class);
    }

    public void loadExercise(final OnCallback<List<Exercise>> callback) {

        class MyTask extends AsyncTask<Void, Void, List<Exercise>> {

            @Override
            protected List<Exercise> doInBackground(Void... voids) {
                List<Exercise> result = new ArrayList<>();
                Call<ExerciseList> call;
                int page = 1;
                Response<ExerciseList> response;
                ExerciseList exerciseList;
                String next;

                do {
                    call = apiService.getPost(page, "json");
                    try {
                        response = call.execute();
                        exerciseList = response.body();
                        result.addAll(exerciseList.getResults());
                        next = exerciseList.getNext();
                    } catch (Exception e) {
                        return null;
                    }
                    page++;
                } while (next != null);

                return result;
            }

            @Override
            protected void onPostExecute(List<Exercise> exercises) {
                if (exercises != null)
                    callback.onCallback(exercises);
                else
                    callback.onError();
            }
        }

        new MyTask().execute();
    }

}