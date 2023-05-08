package mx.jramon.subias.dbmovieproyect.login.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.dropWhile
import mx.jramon.subias.dbmovieproyect.network.FirebaseCommunication
import mx.jramon.subias.dbmovieproyect.GlobalViewModel
import mx.jramon.subias.dbmovieproyect.network.NetworkMonitor
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    networkMonitor: NetworkMonitor
) : GlobalViewModel() {

    private val firebase: FirebaseCommunication by lazy { FirebaseCommunication() }

    private val _validUser = MutableLiveData<Boolean>()

    val validUser:LiveData<Boolean>
    get() = _validUser

    val validUserState by mutableStateOf(false)

    var isOnline = networkMonitor.isOnline.dropWhile { it }.distinctUntilChanged()

    fun authCreateUser(email:String, pass:String){
        _lottieState.value = true
        firebase.auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener { task->
            _validUser.value = task.isSuccessful
        }
    }

    fun validateSignInUser(email: String, pass:String){
        _lottieState.value = true
        firebase.auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener { task->
            _validUser.value = task.isSuccessful
        }
    }
}