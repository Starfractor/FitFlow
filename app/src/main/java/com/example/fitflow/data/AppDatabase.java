import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {FoodLogEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FoodLogDao foodLogDao();
}
