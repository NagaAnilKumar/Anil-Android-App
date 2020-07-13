package coding.androidcoding.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import coding.androidcoding.data.models.PageResponseModel

@Dao
interface CardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllCards(cards: List<CardEntity>)

    @Query("SELECT * FROM cards")
    fun getAllCards(): LiveData<List<CardEntity>>

}