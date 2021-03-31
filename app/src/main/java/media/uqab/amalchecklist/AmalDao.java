package media.uqab.amalchecklist;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AmalDao {

    @Insert
    public void insert(Amal amal);

    @Update
    public void update(Amal amal);

    @Delete
    public void delete(Amal amal);

    @Query("SELECT * FROM amal_table WHERE type LIKE '%DAILY%'")
    LiveData<List<Amal>> getDailyAmal();

    @Query("SELECT * FROM amal_table WHERE type LIKE '%WEEKLY%'")
    LiveData<List<Amal>> getWeeklyAmal();

    @Query("SELECT * FROM amal_table WHERE amalID IS :amalID")
    Amal getAmal(String amalID);
}
