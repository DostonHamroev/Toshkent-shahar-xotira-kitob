package uz.hamroev.toshkentshaharxotirakitob.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class YearEntity {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var year_name: String = ""


}