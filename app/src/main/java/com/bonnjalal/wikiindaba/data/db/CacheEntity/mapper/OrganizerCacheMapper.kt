package com.bonnjalal.wikiindaba.data.db.CacheEntity.mapper

import com.bonnjalal.wikiindaba.data.db.CacheEntity.AttendeeCacheEntity
import com.bonnjalal.wikiindaba.data.db.CacheEntity.OrganizerCacheEntity
import com.bonnjalal.wikiindaba.presentation.model.Attendee
import com.bonnjalal.wikiindaba.presentation.model.Organizer
import javax.inject.Inject

class OrganizerCacheMapper @Inject constructor(): EntityCacheMapper<OrganizerCacheEntity, Organizer> {
    override fun mapFromEntity(entity: OrganizerCacheEntity): Organizer {
        TODO("Not yet implemented")
    }

    override fun mapToEntity(model: Organizer): OrganizerCacheEntity {
        TODO("Not yet implemented")
    }

    fun mapFromEntityList(entities: List<OrganizerCacheEntity>): List<Organizer> {
        return entities.map { mapFromEntity(it) }
    }
}
