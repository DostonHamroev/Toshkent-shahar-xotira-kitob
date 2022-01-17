package uz.hamroev.toshkentshaharxotirakitob.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [XotiraEntity::class, YearEntity::class],
    version = 1,
    exportSchema = false
)


abstract class XotiraDatabase: RoomDatabase() {

    abstract fun getXotiraDao():XotiraDao

    companion object{
        @Volatile
        private var database: XotiraDatabase? = null


        fun init(context: Context){
            synchronized(this){
                if (database == null){
                    database = Room.databaseBuilder(context.applicationContext,
                        XotiraDatabase::class.java,
                        "xotira.db")
                        .createFromAsset("xotira.db")
                        .allowMainThreadQueries()
                        .build()

                }

            }
        }
    }


    object GET {
        fun getXotiraDatabase(): XotiraDatabase = database!!
    }
}