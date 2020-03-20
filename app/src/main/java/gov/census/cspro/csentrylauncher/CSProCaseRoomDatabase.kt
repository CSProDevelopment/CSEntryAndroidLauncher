package gov.census.cspro.csentrylauncher

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(entities = [CSProCase::class], version = 1, exportSchema = false)
abstract class CSProCaseRoomDatabase : RoomDatabase() {

    abstract fun dao(): CSProCaseDao

    companion object {

        @Volatile
        private var INSTANCE: CSProCaseRoomDatabase? = null

        fun getDatabase(context: Context): CSProCaseRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        CSProCaseRoomDatabase::class.java,
                        "cspro_case_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}