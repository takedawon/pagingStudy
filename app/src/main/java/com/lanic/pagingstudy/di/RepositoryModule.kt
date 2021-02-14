package com.lanic.pagingstudy.di

import com.lanic.pagingstudy.network.PokeiService
import com.lanic.pagingstudy.network.repository.RemoteRepository
import com.lanic.pagingstudy.network.repository.RemoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun provideRemoteRepository(pokeiService: PokeiService): RemoteRepository {
        return RemoteRepositoryImpl(pokeiService)
    }
}