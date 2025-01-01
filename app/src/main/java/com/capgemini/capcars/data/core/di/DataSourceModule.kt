package com.capgemini.capcars.data.core.di

import com.capgemini.capcars.data.datasource.CarRemoteDataSource
import com.capgemini.capcars.data.datasource.CarRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * This module provides dependency injection bindings for data sources in the application.
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindCarRemoteDataSource(
        impl: CarRemoteDataSourceImpl
    ): CarRemoteDataSource
}
