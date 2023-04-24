package com.andresrivas.bazpeliculasyseries.tools

sealed class SignInProviderType {
    object GoogleSignIn : SignInProviderType()
    object EmailSignIn : SignInProviderType()
}