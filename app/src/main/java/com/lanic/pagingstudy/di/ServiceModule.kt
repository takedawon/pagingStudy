package com.lanic.pagingstudy.di

import com.lanic.pagingstudy.network.PokeiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    fun providePokeiList(retrofit: Retrofit): PokeiService {
        return retrofit.create(PokeiService::class.java)
    }

}