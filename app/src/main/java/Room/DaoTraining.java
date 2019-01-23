package Room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.fitnes.Training;

import java.util.List;


@Dao
public interface DaoTraining {

    @Query("SELECT * FROM dbTraining")
    List<Training> getAllTraining();

    @Query("SELECT * FROM dbTraining WHERE dbTraining.id=:id" )
    Training getTraining(final int id);

    @Insert
    void insertTraining(Training training);

    @Delete
    void deleteTraining(Training training);
}
