package com.bonnjalal.wikiindaba.data.repository

import com.bonnjalal.wikiindaba.data.online.online_entity.mapper.AttendeeOnlineMapper
import com.bonnjalal.wikiindaba.data.online.online_entity.mapper.OrganizerOnlineMapper
import com.bonnjalal.wikiindaba.data.online.online_entity.mapper.ProgramOnlineMapper
import com.bonnjalal.wikiindaba.data.db.dao.AttendeeDao
import com.bonnjalal.wikiindaba.data.db.dao.OrganizerDao
import com.bonnjalal.wikiindaba.data.db.dao.ProgramDao
import com.bonnjalal.wikiindaba.presentation.model.Attendee
import com.bonnjalal.wikiindaba.presentation.model.Organizer
import com.bonnjalal.wikiindaba.presentation.model.Program
import com.bonnjalal.wikiindaba.presentation.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepository
constructor(
    private val attendeeDao: AttendeeDao,
    private val organizerDao: OrganizerDao,
    private val programDao: ProgramDao,
    private val attendeeCacheMapper: AttendeeOnlineMapper,
    private val organizerCacheMapper: OrganizerOnlineMapper,
    private val programCacheMapper: ProgramOnlineMapper
){
    suspend fun getAttendees() : Flow<DataState<List<Attendee>>> = flow {
        emit(DataState.Loading)
        try {
            val cachedAttendees = attendeeDao.get()
            val attendees = attendeeCacheMapper.mapFromEntityList(cachedAttendees)
            emit(DataState.Success(attendees))
        }catch (e:Exception) {
            emit(DataState.Error(e))
        }


    }
    suspend fun getOrganizers() : Flow<DataState<List<Organizer>>> = flow {
        emit(DataState.Loading)
        try {
            val cachedOrganizers = organizerDao.get()
            val organizers = organizerCacheMapper.mapFromEntityList(cachedOrganizers)
            emit(DataState.Success(organizers))
        }catch (e:Exception) {
            emit(DataState.Error(e))
        }


    }
    suspend fun getProgram() : Flow<DataState<List<Program>>> = flow {
        emit(DataState.Loading)
        try {
            val cachedProgram = programDao.get()
            val program = programCacheMapper.mapFromEntityList(cachedProgram)
            emit(DataState.Success(program))
        }catch (e:Exception) {
            emit(DataState.Error(e))
        }
    }
}