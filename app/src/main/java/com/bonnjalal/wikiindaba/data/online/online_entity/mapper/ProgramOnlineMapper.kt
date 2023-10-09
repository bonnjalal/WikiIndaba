package com.bonnjalal.wikiindaba.data.online.online_entity.mapper

import com.bonnjalal.wikiindaba.data.online.online_entity.ProgramOnlineEntity
import com.bonnjalal.wikiindaba.data.db.cache_entity.ProgramCacheEntity
import javax.inject.Inject

class ProgramOnlineMapper @Inject constructor():
    EntityOnlineMapper<ProgramOnlineEntity, ProgramCacheEntity> {

    fun mapFromEntityList(entities: List<ProgramOnlineEntity>): List<ProgramCacheEntity> {
        return entities.map { mapFromEntity(it) }
    }

    override fun mapFromEntity(entity: ProgramOnlineEntity): ProgramCacheEntity {
        TODO("Not yet implemented")
    }

    override fun mapToEntity(model: ProgramCacheEntity): ProgramOnlineEntity {
        TODO("Not yet implemented")
    }
}