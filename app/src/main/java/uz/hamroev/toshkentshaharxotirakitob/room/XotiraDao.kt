package uz.hamroev.toshkentshaharxotirakitob.room

import androidx.room.Dao
import androidx.room.Query

@Dao
interface XotiraDao {

    @Query("SELECT * from xotira limit 500")
    fun getAllPerson(): List<XotiraEntity>

    @Query("SELECT * FROM xotira WHERE year_id=:yearId")
    fun getPersonByYearId(yearId: Int): List<XotiraEntity>

    @Query("SELECT * FROM xotira WHERE person_name LIKE '%' || :name || '%' ")
    fun search(name: String): List<XotiraEntity>

}