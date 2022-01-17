package uz.hamroev.toshkentshaharxotirakitob.viewmodel

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import uz.hamroev.toshkentshaharxotirakitob.room.XotiraDatabase
import uz.hamroev.toshkentshaharxotirakitob.room.XotiraEntity
import uz.hamroev.toshkentshaharxotirakitob.utils.Resource
import java.lang.Exception

class MyViewModel : ViewModel() {
    var list = MutableLiveData<Resource<List<XotiraEntity>>>()

    fun getList() : LiveData<Resource<List<XotiraEntity>>> {
        viewModelScope.launch(Dispatchers.IO){
            list.postValue(Resource.loading(null))
            try {
                val xotiraDao = XotiraDatabase.GET.getXotiraDatabase().getXotiraDao()
                val allPerson = xotiraDao.getAllPerson()
                list.postValue(Resource.success(allPerson))
            } catch (e: Exception) {
                list.postValue(Resource.error(e.message.toString(), null))
            }
        }
        return list
    }
}