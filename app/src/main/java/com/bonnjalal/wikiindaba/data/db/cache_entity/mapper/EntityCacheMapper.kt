package com.bonnjalal.wikiindaba.data.db.cache_entity.mapper

interface EntityCacheMapper<EntityModel, DomainModel> {
    fun mapFromEntity(entity:EntityModel): DomainModel

    fun mapToEntity(model:DomainModel): EntityModel
}