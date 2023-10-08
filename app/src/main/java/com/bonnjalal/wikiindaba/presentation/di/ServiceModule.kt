package com.bonnjalal.wikiindaba.presentation.di

import com.bonnjalal.wikiindaba.presentation.service.AccountService
import com.bonnjalal.wikiindaba.presentation.service.impl.AccountServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideAccountService(impl: AccountServiceImpl): AccountService

//    @Binds abstract fun provideLogService(impl: LogServiceImpl): LogService

//    @Binds abstract fun provideStorageService(impl: StorageServiceImpl): StorageService

//    @Binds
//    abstract fun provideConfigurationService(impl: ConfigurationServiceImpl): ConfigurationService
}