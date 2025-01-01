package com.capgemini.capcars.domain.core.di

import com.capgemini.capcars.domain.usecase.FetchCarsUseCase
import com.capgemini.capcars.domain.usecase.FetchCarsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Module for providing use case dependencies like FetchCarsUseCase.
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun bindFetchCarsUseCase(
        fetchCarsUseCaseImpl: FetchCarsUseCaseImpl
    ): FetchCarsUseCase
}
