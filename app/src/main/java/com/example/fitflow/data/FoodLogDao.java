import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.Date;

@Dao
public interface FoodLogDao {
    @Insert
    void insert(FoodLogEntity foodLog);

    @Query("SELECT * FROM food_logs WHERE date = :date")
    FoodLogEntity getFoodLogForDate(Date date);
}
