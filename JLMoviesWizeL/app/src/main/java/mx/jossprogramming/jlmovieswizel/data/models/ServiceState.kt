package mx.jossprogramming.jlmovieswizel.data.models

sealed class ServiceState {
    object ServiceDefaultState: ServiceState()
    object ServiceErrorState: ServiceState()
    object ServiceSuccessState: ServiceState()
}