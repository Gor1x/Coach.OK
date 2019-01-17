package APIParse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {
    @GET("/api/v2/exercise/")
    Call<ExerciseList> getPost(@Query("page") int page, @Query("format") String format);
    //Call<ExerciseList> getPost();

}
