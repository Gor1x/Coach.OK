package APIParse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    @GET("/api/v2/exercise/?page=28")
    Call<ExerciseList> getPost();
}
