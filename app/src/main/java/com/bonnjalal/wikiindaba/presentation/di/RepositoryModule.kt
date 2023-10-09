package com.bonnjalal.wikiindaba.presentation.di

import com.bonnjalal.wikiindaba.data.online.online_entity.mapper.AttendeeOnlineMapper
import com.bonnjalal.wikiindaba.data.online.online_entity.mapper.OrganizerOnlineMapper
import com.bonnjalal.wikiindaba.data.online.online_entity.mapper.ProgramOnlineMapper
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
        attendeeCacheMapper: AttendeeOnlineMapper,
        organizerCacheMapper: OrganizerOnlineMapper,
        programCacheMapper: ProgramOnlineMapper
    ): MainRepository {
        return MainRepository(attendeeDao, organizerDao, programDao, attendeeCacheMapper, organizerCacheMapper, programCacheMapper)
    }

}