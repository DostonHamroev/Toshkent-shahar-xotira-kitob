package uz.hamroev.toshkentshaharxotirakitob.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
class PersonEntity {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var person_name: String = ""
    var person_info: String = ""
}