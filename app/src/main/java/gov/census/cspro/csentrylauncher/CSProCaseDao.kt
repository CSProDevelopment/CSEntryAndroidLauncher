package gov.census.cspro.csentrylauncher

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CSProCaseDao {

    @Query("SELECT * from CSProCase")
    fun getCases(): LiveData<List<CSProCase>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(c : CSProCase)

}
