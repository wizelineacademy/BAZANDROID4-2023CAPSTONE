package mx.jramon.subias.dbmovieproyect.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mx.jramon.subias.dbmovieproyect.network.FirebaseCommunication
import mx.jramon.subias.dbmovieproyect.GlobalViewModel

class LoginViewModel : GlobalViewModel() {

    private val firebase: FirebaseCommunication by lazy { FirebaseCommunication() }

    private val _validUser = MutableLiveData<Boolean>()

    val validUser:LiveData<Boolean>
    get() = _validUser

    fun authCreateUser(email:String, pass:String){
        firebase.auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener { task->
            _validUser.value = task.isSuccessful
        }
    }

    fun validateSignInUser(email: String, pass:String){
        firebase.auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener { task->
            _validUser.value = task.isSuccessful
        }
    }
}