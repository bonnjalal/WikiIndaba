package com.bonnjalal.wikiindaba.data.online.online_entity.mapper

interface EntityOnlineMapper<EntityModel, DomainModel> {
    fun mapFromEntity(entity:EntityModel): DomainModel

    fun mapToEntity(model:DomainModel): EntityModel
}