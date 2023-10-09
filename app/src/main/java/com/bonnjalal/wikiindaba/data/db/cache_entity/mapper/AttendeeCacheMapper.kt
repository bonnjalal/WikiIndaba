package com.bonnjalal.wikiindaba.data.db.cache_entity.mapper

import com.bonnjalal.wikiindaba.data.online.online_entity.AttendeeOnlineEntity
import com.bonnjalal.wikiindaba.data.online.online_entity.mapper.EntityOnlineMapper
import com.bonnjalal.wikiindaba.presentation.model.Attendee
import javax.inject.Inject


class AttendeeCacheMapper @Inject constructor():
    EntityOnlineMapper<AttendeeOnlineEntity, Attendee> {
    override fun mapFromEntity(entity: AttendeeOnlineEntity): Attendee {
        TODO("Not yet implemented")
    }

    override fun mapToEntity(model: Attendee): AttendeeOnlineEntity {
        TODO("Not yet implemented")
    }

    fun mapFromEntityList(entities: List<AttendeeOnlineEntity>): List<Attendee> {
        return entities.map { mapFromEntity(it) }
    }
}