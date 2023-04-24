package mx.jramon.subias.dbmovieproyect.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.core.content.getSystemService
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate
import javax.inject.Inject

class ConnectivityManagerNetworkMonitor @Inject constructor(
    @ApplicationContext private val context: Context
)
    : NetworkMonitor {

    private val connectivityManager = context.getSystemService<ConnectivityManager>()

    override val isOnline: Flow<Boolean> = callbackFlow {
        val callback = object : NetworkCallback(){

            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                channel.trySend(true)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                channel.trySend(false)
            }
        }

        connectivityManager?.registerNetworkCallback(
            NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build(),
            callback
        )

        channel.trySend(connectivityManager?.isCurrentlyConnected()?:false)

        awaitClose{
            connectivityManager?.unregisterNetworkCallback(callback)
        }
    }.conflate()

    private fun ConnectivityManager?.isCurrentlyConnected() = when(this){
        null -> false
        else -> activeNetwork?.let(::getNetworkCapabilities)
            ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            ?:false
    }

    override fun isOnline() = connectivityManager.isCurrentlyConnected()
}