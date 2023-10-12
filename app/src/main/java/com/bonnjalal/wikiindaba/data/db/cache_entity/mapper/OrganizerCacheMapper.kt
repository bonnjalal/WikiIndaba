package com.bonnjalal.wikiindaba.data.db.cache_entity.mapper

import com.bonnjalal.wikiindaba.data.db.cache_entity.OrganizerCacheEntity
import com.bonnjalal.wikiindaba.data.online.online_entity.OrganizerOnlineEntity
import com.bonnjalal.wikiindaba.data.online.online_entity.mapper.EntityOnlineMapper
import com.bonnjalal.wikiindaba.presentation.model.Organizer
import javax.inject.Inject

class OrganizerCacheMapper @Inject constructor():
    EntityOnlineMapper<OrganizerCacheEntity, Organizer> {
    override fun mapFromEntity(entity: OrganizerCacheEntity): Organizer {
        return Organizer(
            id = entity.id,
            name = entity.name,
            username = entity.username,
            room = entity.room,
            phoneNumber = entity.phoneNumber,
            image = entity.image
        )
    }

    override fun mapToEntity(model: Organizer): OrganizerCacheEntity {
        return OrganizerCacheEntity(
            id = model.id,
            name = model.name,
            username = model.username,
            room = model.room,
            phoneNumber = model.phoneNumber,
            image = model.image
        )
    }

    fun mapFromEntityList(entities: List<OrganizerCacheEntity>): List<Organizer> {
        return entities.map { mapFromEntity(it) }
    }
}
