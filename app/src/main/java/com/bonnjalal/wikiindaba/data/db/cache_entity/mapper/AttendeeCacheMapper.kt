package com.bonnjalal.wikiindaba.data.db.cache_entity.mapper

import com.bonnjalal.wikiindaba.data.db.cache_entity.AttendeeCacheEntity
import com.bonnjalal.wikiindaba.data.online.online_entity.mapper.EntityOnlineMapper
import com.bonnjalal.wikiindaba.presentation.model.Attendee
import javax.inject.Inject


class AttendeeCacheMapper @Inject constructor():
    EntityOnlineMapper<AttendeeCacheEntity, Attendee> {
    override fun mapFromEntity(entity: AttendeeCacheEntity): Attendee {
        return Attendee(
            id = entity.id,
            name = entity.name,
            username = entity.username,
            email = entity.email,
            role = entity.role,
            phoneNumber = entity.phoneNumber,
            room = entity.room
        )
    }

    override fun mapToEntity(model: Attendee): AttendeeCacheEntity {
        return AttendeeCacheEntity(
            id = model.id,
            name = model.name,
            username = model.username,
            email = model.email,
            role = model.role,
            phoneNumber = model.phoneNumber,
            room = model.room
        )
    }

    fun mapFromEntityList(entities: List<AttendeeCacheEntity>): List<Attendee> {
        return entities.map { mapFromEntity(it) }
    }
}