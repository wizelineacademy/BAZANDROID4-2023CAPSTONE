package mx.jramon.subias.dbmovieproyect.network

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirebaseCommunication {

    val auth:FirebaseAuth by lazy { Firebase.auth }

}