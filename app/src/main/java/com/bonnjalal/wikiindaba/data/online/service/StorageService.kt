package com.bonnjalal.wikiindaba.data.online.service

import com.bonnjalal.wikiindaba.data.online.online_entity.AttendanceOnlineEntity
import com.bonnjalal.wikiindaba.data.online.online_entity.AttendeeOnlineEntity
import com.bonnjalal.wikiindaba.data.online.online_entity.OrganizerOnlineEntity
import com.bonnjalal.wikiindaba.data.online.online_entity.ProgramOnlineEntity
import com.bonnjalal.wikiindaba.presentation.model.Attendee
import kotlinx.coroutines.flow.Flow

interface StorageService {
    val attendees: Flow<List<AttendeeOnlineEntity>>
    suspend fun getAttendee(id: String): AttendeeOnlineEntity?
    suspend fun saveAttendee(attendee: AttendeeOnlineEntity): String
    suspend fun updateAttendee(attendee: AttendeeOnlineEntity)
    suspend fun deleteAttendee(id: String)

    val programs: Flow<List<ProgramOnlineEntity>>
    suspend fun getProgram(id: String): ProgramOnlineEntity?
    suspend fun saveProgram(program: ProgramOnlineEntity): String
    suspend fun updateProgram(program: ProgramOnlineEntity)
    suspend fun deleteProgram(id: String)

    val organizers: Flow<List<OrganizerOnlineEntity>>
    suspend fun getOrganizer(id: String): OrganizerOnlineEntity?
    suspend fun saveOrganizer(organizer: OrganizerOnlineEntity): String
    suspend fun updateOrganizer(organizer: OrganizerOnlineEntity)
    suspend fun deleteOrganizer(id: String)

//    val attendance: Flow<List<OrganizerOnlineEntity>>
    suspend fun getAttendance(path: String): Flow<List<AttendanceOnlineEntity>>
    suspend fun saveAttendance(path: String, attendance: AttendanceOnlineEntity): String
    suspend fun updateAttendance(path: String, attendance: AttendanceOnlineEntity)
    suspend fun deleteAttendance(path: String, name: String)
}