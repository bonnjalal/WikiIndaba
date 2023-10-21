package com.bonnjalal.wikiindaba.data.repository

import android.util.Log
import com.bonnjalal.wikiindaba.data.db.cache_entity.mapper.AttendanceCacheMapper
import com.bonnjalal.wikiindaba.data.db.cache_entity.mapper.AttendeeCacheMapper
import com.bonnjalal.wikiindaba.data.db.cache_entity.mapper.OrganizerCacheMapper
import com.bonnjalal.wikiindaba.data.db.cache_entity.mapper.ProgramCacheMapper
import com.bonnjalal.wikiindaba.data.db.dao.AttendanceDao
import com.bonnjalal.wikiindaba.data.online.online_entity.mapper.AttendeeOnlineMapper
import com.bonnjalal.wikiindaba.data.online.online_entity.mapper.OrganizerOnlineMapper
import com.bonnjalal.wikiindaba.data.online.online_entity.mapper.ProgramOnlineMapper
import com.bonnjalal.wikiindaba.data.db.dao.AttendeeDao
import com.bonnjalal.wikiindaba.data.db.dao.OrganizerDao
import com.bonnjalal.wikiindaba.data.db.dao.ProgramDao
import com.bonnjalal.wikiindaba.data.online.online_entity.mapper.AttendanceOnlineMapper
import com.bonnjalal.wikiindaba.data.online.service.StorageService
import com.bonnjalal.wikiindaba.presentation.model.Attendance
import com.bonnjalal.wikiindaba.presentation.model.Attendee
import com.bonnjalal.wikiindaba.presentation.model.Organizer
import com.bonnjalal.wikiindaba.presentation.model.Program
import com.bonnjalal.wikiindaba.presentation.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.coroutineContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class MainRepository
constructor(
    private val attendeeDao: AttendeeDao,
    private val organizerDao: OrganizerDao,
    private val programDao: ProgramDao,
    private val attendanceDao: AttendanceDao,
    private val storageService: StorageService,
    private val attendeeCacheMapper: AttendeeCacheMapper,
    private val organizerCacheMapper: OrganizerCacheMapper,
    private val programCacheMapper: ProgramCacheMapper,
    private val attendanceCacheMapper: AttendanceCacheMapper,
    private val attendeeOnlineMapper: AttendeeOnlineMapper,
    private val programOnlineMapper: ProgramOnlineMapper,
    private val organizerOnlineMapper: OrganizerOnlineMapper,
    private val attendanceOnlineMapper: AttendanceOnlineMapper,

    ){
    suspend fun getAttendees() : Flow<DataState<List<Attendee>>> = flow {
        emit(DataState.Loading)
        try {
            storageService.attendees.collectLatest {
                attendeeDao.insert(attendeeOnlineMapper.mapFromEntityList(it))
            }
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
            storageService.organizers.collectLatest {
                organizerDao.insert(organizerOnlineMapper.mapFromEntityList(it))
            }
            val cachedOrganizers = organizerDao.get()
            val organizers = organizerCacheMapper.mapFromEntityList(cachedOrganizers)
            emit(DataState.Success(organizers))
        }catch (e:Exception) {
            emit(DataState.Error(e))
        }


    }
    suspend fun getProgram() : Flow<DataState<List<Program>>> = flow {
        Log.e("indaba Repository", "before loading")
        emit(DataState.Loading)
//        Log.e("indaba Repository", "After loading")
//        val prog = storageService.getProgram("programID_1")
//        Log.e("indaba Repository", "cellect" + prog.toString())


        try {
            val onlinePrograms = storageService.programs.first()
//            Log.e("indaba Repository", "cellect " + onlinePrograms.toString())
            val result = programDao.insert(programOnlineMapper.mapFromEntityList(onlinePrograms))
//            Log.e("indaba Repository", "aflet collect $result")
            val cachedProgram = programDao.get()
//            Log.e("indaba Repository", "program: $cachedProgram")
            val program = programCacheMapper.mapFromEntityList(cachedProgram)
//            Log.e("indaba Repository", "program: $program")

            emit(DataState.Success(program))
            Log.e("indaba Repository", "After emit program")
        }catch (e:Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun getAttendance(programId:String) : Flow<DataState<Attendance>> = flow {
        emit(DataState.Loading)

        try {
            val onlineAttendance = storageService.getAttendance(programId).first()
            val result = attendanceDao.insert(attendanceOnlineMapper.mapFromEntity(programId,onlineAttendance))
            val cachedAttendance = attendanceDao.getAttendance(programId)
            val attendance = attendanceCacheMapper.mapFromEntity(cachedAttendance)

            emit(DataState.Success(attendance))
//            Log.e("indaba Repository", "After emit program")
        }catch (e:Exception) {
            emit(DataState.Error(e))
        }
    }
}