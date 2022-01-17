package uz.hamroev.toshkentshaharxotirakitob.App

import android.app.Application
import uz.hamroev.toshkentshaharxotirakitob.room.XotiraDatabase

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        XotiraDatabase.init(this)
    }
}