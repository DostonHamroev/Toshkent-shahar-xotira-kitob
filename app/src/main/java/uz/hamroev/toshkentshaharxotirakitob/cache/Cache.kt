package uz.hamroev.toshkentshaharxotirakitob.cache

import android.content.Context
import android.content.SharedPreferences

object Cache {

    private const val NAME = "xotiratoshkent"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context){
        sharedPreferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit){
        val editor = edit()
        operation(editor)
        editor.apply()
    }


    var yearId:String?
        get() = sharedPreferences.getString("joy","0")
        set(value) = Cache.sharedPreferences.edit() {
            if (value != null){
                it.putString("joy",value)
            }
        }



}
