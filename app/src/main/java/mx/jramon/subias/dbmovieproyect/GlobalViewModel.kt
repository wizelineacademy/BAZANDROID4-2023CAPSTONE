package mx.jramon.subias.dbmovieproyect

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.dropWhile
import mx.jramon.subias.dbmovieproyect.network.NetworkMonitor
import javax.inject.Inject

@HiltViewModel
open class GlobalViewModel @Inject constructor(): ViewModel() {

    private val _showLottie = MutableLiveData<Boolean>()
    val showLottie:LiveData<Boolean>get() = _showLottie

    protected val _errorApi = MutableLiveData<String>()
    val errorApi:LiveData<String> get() = _errorApi



    fun showLottie(show:Boolean){
        _showLottie.postValue(show)
    }
}