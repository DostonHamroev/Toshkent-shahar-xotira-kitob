package uz.hamroev.toshkentshaharxotirakitob.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PersonEntity::class], version = 1)
abstract class PersonDatabase: RoomDatabase() {

    abstract fun personDao(): PersonDao

    companion object {
        private var instance: PersonDatabase? = null

        @Synchronized
        fun getInstance(context: Context): PersonDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonDatabase::class.java,
                    "person.db"
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}