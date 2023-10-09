package com.bonnjalal.wikiindaba.data.db.cache_entity.mapper

import com.bonnjalal.wikiindaba.data.online.online_entity.OrganizerOnlineEntity
import com.bonnjalal.wikiindaba.data.online.online_entity.mapper.EntityOnlineMapper
import com.bonnjalal.wikiindaba.presentation.model.Organizer
import javax.inject.Inject

class OrganizerCacheMapper @Inject constructor():
    EntityOnlineMapper<OrganizerOnlineEntity, Organizer> {
    override fun mapFromEntity(entity: OrganizerOnlineEntity): Organizer {
        TODO("Not yet implemented")
    }

    override fun mapToEntity(model: Organizer): OrganizerOnlineEntity {
        TODO("Not yet implemented")
    }

    fun mapFromEntityList(entities: List<OrganizerOnlineEntity>): List<Organizer> {
        return entities.map { mapFromEntity(it) }
    }
}
