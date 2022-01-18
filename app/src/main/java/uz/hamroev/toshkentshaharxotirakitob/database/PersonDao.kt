package uz.hamroev.toshkentshaharxotirakitob.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PersonDao {

    @Query("SELECT * from person")
    fun getAllPerson(): List<PersonEntity>

    @Insert
    fun addPerson(personEntity: PersonEntity)
}