package com.bonnjalal.wikiindaba.data.db.cache_entity.mapper

import com.bonnjalal.wikiindaba.data.db.cache_entity.ProgramCacheEntity
import com.bonnjalal.wikiindaba.data.online.online_entity.mapper.EntityOnlineMapper
import com.bonnjalal.wikiindaba.presentation.model.Program
import java.util.Date
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
            authorsName = entity.responsible,
            slide = entity.slide,
            startTime = Date(entity.startTime * 1000),
            endTime = Date(entity.endTime*1000)
        )
    }

    override fun mapToEntity(model: Program): ProgramCacheEntity {
        return ProgramCacheEntity(
            id = model.id,
            title = model.title,
            authors = model.authors,
            room = model.room,
            responsible = model.authorsName,
            slide = model.slide,
            startTime = model.startTime.time/1000,
            endTime = model.endTime.time/1000
        )
    }
}