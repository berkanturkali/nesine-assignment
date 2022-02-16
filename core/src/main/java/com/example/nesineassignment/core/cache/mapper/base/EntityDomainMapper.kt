package com.example.nesineassignment.core.cache.mapper.base

interface EntityDomainMapper<E,D> {

    fun mapFromEntity(entity:E):D

    fun mapToEntity(domain:D):E

    fun mapDomainList(entities: List<E>): List<D>? {
        return entities.mapTo(mutableListOf(), ::mapFromEntity)
    }
}