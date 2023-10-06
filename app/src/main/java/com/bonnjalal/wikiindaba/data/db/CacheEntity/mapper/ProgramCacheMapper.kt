package com.bonnjalal.wikiindaba.data.db.CacheEntity.mapper

import com.bonnjalal.wikiindaba.data.db.CacheEntity.ProgramCacheEntity
import com.bonnjalal.wikiindaba.presentation.model.Program
import javax.inject.Inject

class ProgramCacheMapper @Inject constructor(): EntityCacheMapper<ProgramCacheEntity, Program> {

    fun mapFromEntityList(entities: List<ProgramCacheEntity>): List<Program> {
        return entities.map { mapFromEntity(it) }
    }

    override fun mapFromEntity(entity: ProgramCacheEntity): Program {
        TODO("Not yet implemented")
    }

    override fun mapToEntity(model: Program): ProgramCacheEntity {
        TODO("Not yet implemented")
    }
}