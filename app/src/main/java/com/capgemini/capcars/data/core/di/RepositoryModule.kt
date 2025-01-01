package com.capgemini.capcars.data.core.di

import com.capgemini.capcars.data.repository.CarRepository
import com.capgemini.capcars.data.repository.CarRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Provides repository bindings for dependency injection.
 * This module ensures that the repository interfaces are linked to their implementations.
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    // Binds CarRepository to its implementation CarRepositoryImpl
    @Binds // TODO SCAT
    @Singleton
    abstract fun bindCarRepository(
        carRepositoryImpl: CarRepositoryImpl
    ): CarRepository
}