package com.bonnjalal.wikiindaba.data.db.cache_entity.mapper

import com.bonnjalal.wikiindaba.data.db.cache_entity.AttendanceCacheEntity
import com.bonnjalal.wikiindaba.data.db.cache_entity.AttendeeCacheEntity
import com.bonnjalal.wikiindaba.data.online.online_entity.mapper.EntityOnlineMapper
import com.bonnjalal.wikiindaba.presentation.model.Attendance
import com.bonnjalal.wikiindaba.presentation.model.Attendee
import javax.inject.Inject


class AttendanceCacheMapper @Inject constructor():
    EntityOnlineMapper<AttendanceCacheEntity, Attendance> {
    override fun mapFromEntity(entity: AttendanceCacheEntity): Attendance {
        return Attendance(
            program = entity.programId,
            attendanceList = entity.name.attendanceList
        )
    }

    override fun mapToEntity(model: Attendance): AttendanceCacheEntity {
        return AttendanceCacheEntity(
            programId = model.program,
            name = model
        )
    }

    fun mapFromEntityList(entities: List<AttendanceCacheEntity>): List<Attendance> {
        return entities.map { mapFromEntity(it) }
    }
}