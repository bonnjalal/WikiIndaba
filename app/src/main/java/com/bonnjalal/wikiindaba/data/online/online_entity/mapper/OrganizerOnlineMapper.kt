package com.bonnjalal.wikiindaba.data.online.online_entity.mapper

import com.bonnjalal.wikiindaba.data.online.online_entity.OrganizerOnlineEntity
import com.bonnjalal.wikiindaba.data.db.cache_entity.OrganizerCacheEntity
import javax.inject.Inject

class OrganizerOnlineMapper @Inject constructor():
    EntityOnlineMapper<OrganizerOnlineEntity, OrganizerCacheEntity> {
    override fun mapFromEntity(entity: OrganizerOnlineEntity): OrganizerCacheEntity {
        TODO("Not yet implemented")
    }

    override fun mapToEntity(model: OrganizerCacheEntity): OrganizerOnlineEntity {
        TODO("Not yet implemented")
    }

    fun mapFromEntityList(entities: List<OrganizerOnlineEntity>): List<OrganizerCacheEntity> {
        return entities.map { mapFromEntity(it) }
    }
}
