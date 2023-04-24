package mx.jramon.subias.dbmovieproyect.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.jramon.subias.dbmovieproyect.network.ConnectivityManagerNetworkMonitor
import mx.jramon.subias.dbmovieproyect.network.NetworkMonitor

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor
    ):NetworkMonitor
}