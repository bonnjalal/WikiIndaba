package com.bonnjalal.wikiindaba.presentation.di

import com.bonnjalal.wikiindaba.data.db.cache_entity.mapper.AttendeeCacheMapper
import com.bonnjalal.wikiindaba.data.db.cache_entity.mapper.OrganizerCacheMapper
import com.bonnjalal.wikiindaba.data.db.cache_entity.mapper.ProgramCacheMapper
import com.bonnjalal.wikiindaba.data.online.online_entity.mapper.AttendeeOnlineMapper
import com.bonnjalal.wikiindaba.data.online.online_entity.mapper.OrganizerOnlineMapper
import com.bonnjalal.wikiindaba.data.online.online_entity.mapper.ProgramOnlineMapper
import com.bonnjalal.wikiindaba.data.db.dao.AttendeeDao
import com.bonnjalal.wikiindaba.data.db.dao.OrganizerDao
import com.bonnjalal.wikiindaba.data.db.dao.ProgramDao
import com.bonnjalal.wikiindaba.data.online.service.StorageService
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
        storageService: StorageService,
        attendeeCacheMapper: AttendeeCacheMapper,
        organizerCacheMapper: OrganizerCacheMapper,
        programCacheMapper: ProgramCacheMapper,
        attendeeOnlineMapper: AttendeeOnlineMapper,
        organizerOnlineMapper: OrganizerOnlineMapper,
        programOnlineMapper: ProgramOnlineMapper
    ): MainRepository {
        return MainRepository(attendeeDao, organizerDao, programDao,storageService,
            attendeeCacheMapper, organizerCacheMapper, programCacheMapper,
            attendeeOnlineMapper, programOnlineMapper, organizerOnlineMapper)
    }

}