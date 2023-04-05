package mx.jramon.subias.dbmovieproyect

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.dropWhile

open class GlobalViewModel(): ViewModel() {

    private val _showLottie = MutableLiveData<Boolean>()
    val showLottie:LiveData<Boolean>get() = _showLottie

    protected val _errorApi = MutableLiveData<String>()
    val errorApi:LiveData<String> get() = _errorApi

    //val isOnline = networkMonitor.isOnline.dropWhile { it }.distinctUntilChanged()

    fun showLottie(show:Boolean){
        _showLottie.postValue(show)
    }
}