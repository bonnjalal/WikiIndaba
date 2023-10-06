package com.bonnjalal.wikiindaba.presentation.di

import com.bonnjalal.wikiindaba.data.db.CacheEntity.mapper.AttendeeCacheMapper
import com.bonnjalal.wikiindaba.data.db.CacheEntity.mapper.OrganizerCacheMapper
import com.bonnjalal.wikiindaba.data.db.CacheEntity.mapper.ProgramCacheMapper
import com.bonnjalal.wikiindaba.data.db.dao.AttendeeDao
import com.bonnjalal.wikiindaba.data.db.dao.OrganizerDao
import com.bonnjalal.wikiindaba.data.db.dao.ProgramDao
import com.bonnjalal.wikiindaba.data.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        attendeeDao: AttendeeDao,
        organizerDao: OrganizerDao,
        programDao: ProgramDao,
        attendeeCacheMapper: AttendeeCacheMapper,
        organizerCacheMapper: OrganizerCacheMapper,
        programCacheMapper: ProgramCacheMapper
    ): MainRepository {
        return MainRepository(attendeeDao, organizerDao, programDao, attendeeCacheMapper, organizerCacheMapper, programCacheMapper)
    }

}