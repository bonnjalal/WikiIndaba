package com.bonnjalal.wikiindaba.data.db.CacheEntity.mapper

import com.bonnjalal.wikiindaba.data.db.CacheEntity.AttendeeCacheEntity
import com.bonnjalal.wikiindaba.presentation.model.Attendee
import javax.inject.Inject


class AttendeeCacheMapper @Inject constructor(): EntityCacheMapper<AttendeeCacheEntity, Attendee> {
    override fun mapFromEntity(entity: AttendeeCacheEntity): Attendee {
        TODO("Not yet implemented")
    }

    override fun mapToEntity(model: Attendee): AttendeeCacheEntity {
        TODO("Not yet implemented")
    }

    fun mapFromEntityList(entities: List<AttendeeCacheEntity>): List<Attendee> {
        return entities.map { mapFromEntity(it) }
    }
}