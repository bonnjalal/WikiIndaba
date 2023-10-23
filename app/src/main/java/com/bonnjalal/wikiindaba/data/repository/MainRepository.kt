package com.bonnjalal.wikiindaba.data.repository

import android.util.Log
import com.bonnjalal.wikiindaba.data.db.cache_entity.AttendanceCacheEntity
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
import com.bonnjalal.wikiindaba.data.online.online_entity.AttendanceOnlineEntity
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
        Log.e("Main Repository:", "attendees: Loading")
        try {
//            val onlineAttendee = storageService.attendees.first()
            val onlineAttendee = storageService.getAttendees()
            Log.e("Main Repository:", "attendees result: $onlineAttendee")
//            val result = attendeeDao.insert(attendeeOnlineMapper.mapFromEntityList(onlineAttendee))
            attendeeDao.insertOrUpdate(attendeeOnlineMapper.mapFromEntityList(onlineAttendee))


        }catch (e:Exception) {
            Log.e("Main Repository:", "attendees Excep: ${e.message}")
            emit(DataState.Error(e))
        }

        try {
            val cachedAttendees = attendeeDao.get()
            val attendees = attendeeCacheMapper.mapFromEntityList(cachedAttendees)

            Log.e("Main Repository:", "attendees: $attendees")
            emit(DataState.Success(attendees))
        }catch (e:Exception){
            Log.e("Main Repository:", "attendees Excep: ${e.message}")
            emit(DataState.Error(e))
        }

    }
    suspend fun getOrganizers() : Flow<DataState<List<Organizer>>> = flow {
        emit(DataState.Loading)
        try {
            val onlineOrganizer = storageService.organizers.first()
            val result = organizerDao.insert(organizerOnlineMapper.mapFromEntityList(onlineOrganizer))

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
//            val onlinePrograms = storageService.programs.first()
            val onlinePrograms = storageService.getPrograms()
            val result = programDao.insert(programOnlineMapper.mapFromEntityList(onlinePrograms))
        }catch (e:Exception) {
            emit(DataState.Error(e))
        }

        try {
            val cachedProgram = programDao.get()
            val program = programCacheMapper.mapFromEntityList(cachedProgram)

            emit(DataState.Success(program))
        }catch (e:Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun getAttendance(programId:String) : Flow<DataState<Attendance>> = flow {
        emit(DataState.Loading)

        try {
            val onlineAttendance = storageService.getAttendance(programId)
            attendanceDao.insertOrUpdate(attendanceOnlineMapper.mapFromEntity(programId,onlineAttendance))

//            Log.e("indaba Repository", "After emit program")
        }catch (e:Exception) {
            Log.e("indaba Repository", "get Excep 1: ${e.message}")
            emit(DataState.Error(e))
        }
        try {
            val isExist = attendanceDao.isExist(programId)
            if (isExist > 0){
                val cachedAttendance = attendanceDao.getAttendance(programId)
                val attendance = attendanceCacheMapper.mapFromEntity(cachedAttendance)

                emit(DataState.Success(attendance))
            }else {
                emit(DataState.Success(Attendance(programId, emptyList())))
            }

//            for (att in attendance.attendanceList){
//                val atten = AttendanceOnlineEntity(name = att)
//                storageService.updateAttendance(programId, atten)
//            }

        }catch (e:Exception) {
            Log.e("indaba Repository", "get Excep 2: ${e.message}")
            emit(DataState.Error(e))
        }
    }

    suspend fun saveAttendance(programId:String, attendance:AttendanceOnlineEntity) : Flow<DataState<Boolean>> = flow {
        emit(DataState.Loading)

        try {
             storageService.updateAttendance(programId,attendance)
//             emit(DataState.Success(true))
        }catch (e:Exception) {
            emit(DataState.Error(e))
        }
        try {
            val isExist = attendanceDao.isExist(programId)
            if (isExist > 0){
                val dbAttendance = attendanceDao.getAttendance(programId).name.attendanceList
                val attList = mutableListOf<String>()
                attList.addAll(dbAttendance)
                if (!attList.contains(attendance.name)) attList.add(attendance.name)

                val newDbAttendance = AttendanceCacheEntity(programId, Attendance(programId, attList))
                attendanceDao.insertOrUpdate(newDbAttendance)
                emit(DataState.Success(true))
            }else {
                val newDbAttendance = AttendanceCacheEntity(programId, Attendance(programId, listOf(attendance.name)))
                attendanceDao.insertOrUpdate(newDbAttendance)
                emit(DataState.Success(true))
            }

//            Log.e("indaba Repository", "After emit program")
        }catch (e:Exception) {
            emit(DataState.Error(e))
        }
    }
    suspend fun deleteAttendance(programId:String, attendance: AttendanceOnlineEntity) : Flow<DataState<Boolean>> = flow {
        emit(DataState.Loading)
        try {
            storageService.deleteAttendance(programId,attendance.name)

//            Log.e("indaba Repository", "After emit program")
        }catch (e:Exception) {
            emit(DataState.Error(e))
        }
        try {
            val dbAttendance = attendanceDao.getAttendance(programId).name.attendanceList
            val attList = mutableListOf<String>()
            attList.addAll(dbAttendance)
            attList.remove(attendance.name)
            val newDbAttendance = AttendanceCacheEntity(programId, Attendance(programId, attList))
            attendanceDao.insertOrUpdate(newDbAttendance)
            emit(DataState.Success(true))

        }catch (e:Exception) {
            emit(DataState.Error(e))
        }
    }

//    suspend fun syncAttendance(){
//        Log.e("indaba Repository", "start Sync")
//        val cachedAttendance = attendanceDao.get()
//        for (dbAtt in cachedAttendance){
//            for (name in dbAtt.name.attendanceList) {
//                val atten = AttendanceOnlineEntity(name = name)
//                storageService.updateAttendance(dbAtt.programId, atten)
//            }
//        }
//        Log.e("indaba Repository", "end Sync")
//    }
}