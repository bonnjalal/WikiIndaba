package com.bonnjalal.wikiindaba.data.db.cache_entity.mapper

import com.bonnjalal.wikiindaba.data.online.online_entity.ProgramOnlineEntity
import com.bonnjalal.wikiindaba.data.online.online_entity.mapper.EntityOnlineMapper
import com.bonnjalal.wikiindaba.presentation.model.Program
import javax.inject.Inject

class ProgramCacheMapper @Inject constructor(): EntityOnlineMapper<ProgramOnlineEntity, Program> {

    fun mapFromEntityList(entities: List<ProgramOnlineEntity>): List<Program> {
        return entities.map { mapFromEntity(it) }
    }

    override fun mapFromEntity(entity: ProgramOnlineEntity): Program {
        TODO("Not yet implemented")
    }

    override fun mapToEntity(model: Program): ProgramOnlineEntity {
        TODO("Not yet implemented")
    }
}