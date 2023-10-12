package com.bonnjalal.wikiindaba.data.db.cache_entity.mapper

import com.bonnjalal.wikiindaba.data.db.cache_entity.ProgramCacheEntity
import com.bonnjalal.wikiindaba.data.online.online_entity.ProgramOnlineEntity
import com.bonnjalal.wikiindaba.data.online.online_entity.mapper.EntityOnlineMapper
import com.bonnjalal.wikiindaba.presentation.model.Program
import com.google.firebase.Timestamp
import javax.inject.Inject

class ProgramCacheMapper @Inject constructor(): EntityOnlineMapper<ProgramCacheEntity, Program> {

    fun mapFromEntityList(entities: List<ProgramCacheEntity>): List<Program> {
        return entities.map { mapFromEntity(it) }
    }

    override fun mapFromEntity(entity: ProgramCacheEntity): Program {
        return Program(
            id = entity.id,
            title = entity.title,
            authors = entity.authors,
            room = entity.room,
            responsible = entity.responsible,
            slide = entity.slide,
            startTime = Timestamp(entity.startTime,0),
            endTime = Timestamp(entity.endTime,0)
        )
    }

    override fun mapToEntity(model: Program): ProgramCacheEntity {
        return ProgramCacheEntity(
            id = model.id,
            title = model.title,
            authors = model.authors,
            room = model.room,
            responsible = model.responsible,
            slide = model.slide,
            startTime = model.startTime.seconds,
            endTime = model.endTime.seconds
        )
    }
}