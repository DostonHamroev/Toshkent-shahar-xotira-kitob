package uz.hamroev.toshkentshaharxotirakitob.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "xotira")
class XotiraEntity {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var person_name: String = ""
    var person_info: String = ""
    var year_id: Int = 0



}