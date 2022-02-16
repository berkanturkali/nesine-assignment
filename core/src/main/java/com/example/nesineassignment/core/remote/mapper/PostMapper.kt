package com.example.nesineassignment.core.remote.mapper

import com.example.nesineassignment.core.cache.model.PostEntity
import com.example.nesineassignment.core.remote.model.PostDto
import com.example.nesineassignment.remote.mapper.RemoteModelMapper
import javax.inject.Inject

class PostMapper @Inject constructor():RemoteModelMapper<PostDto,PostEntity> {
    override fun mapFromModel(model: PostDto): PostEntity {
        return PostEntity(
            body = model.body,
            id = model.id,
            title = model.title,
            userId = model.userId
        )
    }
}