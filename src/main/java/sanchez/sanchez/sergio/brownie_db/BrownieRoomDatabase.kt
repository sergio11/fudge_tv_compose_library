package sanchez.sanchez.sergio.brownie_db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

/**
    Brownie Room Database
 **/
abstract class BrownieRoomDatabase: RoomDatabase() {

    companion object {

        private val databaseName = "BrownieDatabase"
        private var dbInstance: BrownieRoomDatabase? = null

        @Synchronized
        fun getDatabase(context: Context, testMode : Boolean): BrownieRoomDatabase {

            if (dbInstance == null) {
                dbInstance = if (testMode) {
                    Room.inMemoryDatabaseBuilder(context, BrownieRoomDatabase::class.java).allowMainThreadQueries().build()

                } else {
                    Room.databaseBuilder(context,
                        BrownieRoomDatabase::class.java, databaseName).build()

                }
            }
            return dbInstance!!
        }
    }

}