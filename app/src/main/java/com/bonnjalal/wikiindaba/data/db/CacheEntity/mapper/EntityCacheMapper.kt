package com.bonnjalal.wikiindaba.data.db.CacheEntity.mapper

interface EntityCacheMapper<EntityModel, DomainModel> {
    fun mapFromEntity(entity:EntityModel): DomainModel

    fun mapToEntity(model:DomainModel): EntityModel
}