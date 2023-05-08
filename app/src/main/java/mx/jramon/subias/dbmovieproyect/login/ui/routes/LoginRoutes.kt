package mx.jramon.subias.dbmovieproyect.login.ui.routes

sealed class LoginRoutes (val routes:String){
    object SignIn : LoginRoutes("signIn")
    object SignUp : LoginRoutes("signUp")
}