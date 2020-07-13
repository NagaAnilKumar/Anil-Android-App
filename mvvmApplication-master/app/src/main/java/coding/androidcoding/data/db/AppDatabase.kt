package coding.androidcoding.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import coding.androidcoding.data.db.dataconverter.CardDetailConverter

@Database(
    entities = [CardEntity::class],
    version = 1
)
@TypeConverters(CardDetailConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getCardDao(): CardDao


    companion object {

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "MyDatabase.db"
            ).build()
    }
}