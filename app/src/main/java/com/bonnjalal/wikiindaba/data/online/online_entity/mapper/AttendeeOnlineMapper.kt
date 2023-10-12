package com.bonnjalal.wikiindaba.data.online.online_entity.mapper

import com.bonnjalal.wikiindaba.data.online.online_entity.AttendeeOnlineEntity
import com.bonnjalal.wikiindaba.data.db.cache_entity.AttendeeCacheEntity
import javax.inject.Inject


class AttendeeOnlineMapper @Inject constructor():
    EntityOnlineMapper<AttendeeOnlineEntity, AttendeeCacheEntity> {
    override fun mapFromEntity(entity: AttendeeOnlineEntity): AttendeeCacheEntity {
        return AttendeeCacheEntity(
            id = entity.id,
            name = entity.name,
            room = entity.room,
            role = entity.role,
            username = entity.username,
            phoneNumber = entity.phone,
            email = entity.email,
            imgUrl = entity.imgUrl

        )
    }

    override fun mapToEntity(model: AttendeeCacheEntity): AttendeeOnlineEntity {
        return AttendeeOnlineEntity(
            id = model.id,
            name = model.name,
            room = model.room,
            role = model.role,
            username = model.username,
            phone = model.phoneNumber,
            email = model.email,
            imgUrl = model.imgUrl
        )
    }

    fun mapFromEntityList(entities: List<AttendeeOnlineEntity>): List<AttendeeCacheEntity> {
        return entities.map { mapFromEntity(it) }
    }
}