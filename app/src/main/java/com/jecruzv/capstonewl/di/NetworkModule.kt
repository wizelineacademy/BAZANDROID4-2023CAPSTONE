package com.jecruzv.capstonewl.di

import com.jecruzv.capstonewl.util.Annotations
import com.jecruzv.capstonewl.util.Constants.BASE_URL
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Annotations("Entregable 2")
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


}