package Room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.fitnes.ExPlusMuscle;

import java.util.List;

import APIParse.Exercise;
import APIParse.MusclePackage.Muscle;

@Dao
public interface DaoMuscleExercise {
    @Insert
    void insert(ExPlusMuscle exPlusMuscle);

    @Query("SELECT * FROM dbExercise INNER JOIN m_plus_e ON dbExercise.id=m_plus_e.eId WHERE m_plus_e.mId=:mId")
    List<Exercise> getExerciseForMuscle(final int mId);

    @Query("SELECT * FROM dbMuscle INNER JOIN m_plus_e ON dbMuscle.id=m_plus_e.mId WHERE m_plus_e.eId=:eId")
    List<Muscle> getMuscleForExercise (final int eId);

}
