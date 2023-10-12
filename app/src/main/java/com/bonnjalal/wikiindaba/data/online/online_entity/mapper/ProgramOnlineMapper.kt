package com.bonnjalal.wikiindaba.data.online.online_entity.mapper

import com.bonnjalal.wikiindaba.data.online.online_entity.ProgramOnlineEntity
import com.bonnjalal.wikiindaba.data.db.cache_entity.ProgramCacheEntity
import com.google.firebase.Timestamp
import javax.inject.Inject

class ProgramOnlineMapper @Inject constructor():
    EntityOnlineMapper<ProgramOnlineEntity, ProgramCacheEntity> {

    fun mapFromEntityList(entities: List<ProgramOnlineEntity>): List<ProgramCacheEntity> {
        return entities.map { mapFromEntity(it) }
    }

    override fun mapFromEntity(entity: ProgramOnlineEntity): ProgramCacheEntity {
        return ProgramCacheEntity(
            id = entity.id,
            title = entity.title,
            authors = entity.authors,
            room = entity.room,
            responsible = entity.responsible,
            slide = entity.slide,
            startTime = entity.startTime.seconds,
            endTime = entity.endTime.seconds
        )
    }

    override fun mapToEntity(model: ProgramCacheEntity): ProgramOnlineEntity {
        return ProgramOnlineEntity(
            id = model.id,
            title = model.title,
            authors = model.authors,
            room = model.room,
            responsible = model.responsible,
            slide = model.slide,
            startTime = Timestamp(model.startTime, 0),
            endTime = Timestamp(model.endTime,0)
        )
    }
}