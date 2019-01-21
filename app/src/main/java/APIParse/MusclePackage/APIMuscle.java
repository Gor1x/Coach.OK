package APIParse.MusclePackage;

import APIParse.ExerciseList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIMuscle {
    @GET("/api/v2/muscle/")
    Call<MuscleList> getPost(@Query("page") int page, @Query("format") String format);
}
