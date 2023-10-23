package com.bonnjalal.wikiindaba.data.online.online_entity.mapper

import com.bonnjalal.wikiindaba.data.db.cache_entity.AttendanceCacheEntity
import com.bonnjalal.wikiindaba.data.online.online_entity.AttendeeOnlineEntity
import com.bonnjalal.wikiindaba.data.db.cache_entity.AttendeeCacheEntity
import com.bonnjalal.wikiindaba.data.online.online_entity.AttendanceOnlineEntity
import com.bonnjalal.wikiindaba.presentation.model.Attendance
import javax.inject.Inject


class AttendanceOnlineMapper @Inject constructor(){
    fun mapFromEntity(programID:String, entity: List<AttendanceOnlineEntity>): AttendanceCacheEntity {
        val nameList: List<String> = entity.map { it.name }
        return AttendanceCacheEntity(
            programId = programID,
            name = Attendance(programID, nameList),
        )
    }

    fun mapToEntity(model: AttendanceCacheEntity): List<AttendanceOnlineEntity> {
        return model.name.attendanceList.map { AttendanceOnlineEntity(it) }
    }

//    fun mapFromEntityList(programID: String, entities: List<AttendanceOnlineEntity>): List<AttendanceCacheEntity> {
//        return entities.map { mapFromEntity(programID, it) }
//    }

}