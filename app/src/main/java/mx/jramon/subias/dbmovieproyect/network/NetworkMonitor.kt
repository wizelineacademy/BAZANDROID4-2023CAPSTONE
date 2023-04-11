package mx.jramon.subias.dbmovieproyect.network

import kotlinx.coroutines.flow.Flow

interface NetworkMonitor {

    val isOnline: Flow<Boolean>

    fun isOnline():Boolean
}