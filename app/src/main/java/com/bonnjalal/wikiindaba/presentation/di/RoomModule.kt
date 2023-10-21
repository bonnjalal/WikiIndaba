package com.bonnjalal.wikiindaba.presentation.di

import android.content.Context
import androidx.room.Room
import com.bonnjalal.wikiindaba.data.db.dao.AttendanceDao
import com.bonnjalal.wikiindaba.data.db.dao.AttendeeDao
import com.bonnjalal.wikiindaba.data.db.dao.OrganizerDao
import com.bonnjalal.wikiindaba.data.db.dao.ProgramDao
import com.bonnjalal.wikiindaba.data.db.room.EventDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideEventDb(@ApplicationContext context: Context):EventDatabase {
        return Room.databaseBuilder(
            context,
            EventDatabase::class.java,
            EventDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideAttendeeDao (eventDatabase: EventDatabase):AttendeeDao {
        return eventDatabase.attendeeDao()
    }
    @Singleton
    @Provides
    fun provideOrganizerDao (eventDatabase: EventDatabase): OrganizerDao {
        return eventDatabase.organizerDao()
    }
    @Singleton
    @Provides
    fun provideProgramDao (eventDatabase: EventDatabase): ProgramDao {
        return eventDatabase.programDao()
    }
    @Singleton
    @Provides
    fun provideAttendanceDao (eventDatabase: EventDatabase): AttendanceDao {
        return eventDatabase.attendanceDao()
    }

}