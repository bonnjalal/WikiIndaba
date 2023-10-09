package com.bonnjalal.wikiindaba.data.online.online_entity.mapper

import com.bonnjalal.wikiindaba.data.online.online_entity.AttendeeOnlineEntity
import com.bonnjalal.wikiindaba.data.db.cache_entity.AttendeeCacheEntity
import javax.inject.Inject


class AttendeeOnlineMapper @Inject constructor():
    EntityOnlineMapper<AttendeeOnlineEntity, AttendeeCacheEntity> {
    override fun mapFromEntity(entity: AttendeeOnlineEntity): AttendeeCacheEntity {
        TODO("Not yet implemented")
    }

    override fun mapToEntity(model: AttendeeCacheEntity): AttendeeOnlineEntity {
        TODO("Not yet implemented")
    }

    fun mapFromEntityList(entities: List<AttendeeOnlineEntity>): List<AttendeeCacheEntity> {
        return entities.map { mapFromEntity(it) }
    }
}