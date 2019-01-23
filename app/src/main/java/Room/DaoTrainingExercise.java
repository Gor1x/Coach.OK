package Room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.fitnes.ExPlusMuscle;
import com.example.fitnes.TrainingPlusExercise;

import java.util.List;

import APIParse.Exercise;
import APIParse.MusclePackage.Muscle;

@Dao
public interface DaoTrainingExercise {
    @Insert
    void insert(TrainingPlusExercise trainingPlusExercise);

    @Query("SELECT * FROM dbExercise INNER JOIN tr_plus_ex ON dbExercise.id=tr_plus_ex.exId WHERE tr_plus_ex.trId=:trId")
    List<Exercise> getExerciseForTraining(final int trId);

    @Query("SELECT * FROM dbTraining INNER JOIN tr_plus_ex ON dbTraining.id=tr_plus_ex.trId WHERE tr_plus_ex.exId=:exId")
    List<Muscle> getTrainingForExeroise(final int exId);

    @Delete
    void deleteExerciseOfTraining(TrainingPlusExercise trainingPlusExercise);
}