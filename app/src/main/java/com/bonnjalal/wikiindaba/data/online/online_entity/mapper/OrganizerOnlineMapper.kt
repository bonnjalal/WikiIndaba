package com.bonnjalal.wikiindaba.data.online.online_entity.mapper

import com.bonnjalal.wikiindaba.data.online.online_entity.OrganizerOnlineEntity
import com.bonnjalal.wikiindaba.data.db.cache_entity.OrganizerCacheEntity
import javax.inject.Inject

class OrganizerOnlineMapper @Inject constructor():
    EntityOnlineMapper<OrganizerOnlineEntity, OrganizerCacheEntity> {
    override fun mapFromEntity(entity: OrganizerOnlineEntity): OrganizerCacheEntity {
        return OrganizerCacheEntity(
            id = entity.id,
            name = entity.name,
            username = entity.username,
            room = entity.room,
            phoneNumber = entity.phoneNumber,
            image = entity.image
        )
    }

    override fun mapToEntity(model: OrganizerCacheEntity): OrganizerOnlineEntity {
        return OrganizerOnlineEntity(
            id = model.id,
            name = model.name,
            username = model.username,
            room = model.room,
            phoneNumber = model.phoneNumber,
            image = model.image
        )
    }

    fun mapFromEntityList(entities: List<OrganizerOnlineEntity>): List<OrganizerCacheEntity> {
        return entities.map { mapFromEntity(it) }
    }
}
