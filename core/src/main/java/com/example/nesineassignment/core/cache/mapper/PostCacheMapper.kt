package com.example.nesineassignment.core.cache.mapper

import com.example.nesineassignment.core.BuildConfig
import com.example.nesineassignment.core.cache.mapper.base.EntityDomainMapper
import com.example.nesineassignment.core.cache.model.PostEntity
import com.example.nesineassignment.core.domain.model.Post
import javax.inject.Inject

class PostCacheMapper @Inject constructor():EntityDomainMapper<PostEntity,Post> {

    companion object{
        const val BASE_IMAGE_URL =BuildConfig.BASE_IMAGE_URL
    }
    override fun mapFromEntity(entity: PostEntity): Post {
        return Post(
            id = entity.id,
            body = entity.body,
            title = entity.title,
            userId = entity.userId,
            image = "$BASE_IMAGE_URL/id/${entity.id}/200/"
        )
    }

    override fun mapToEntity(domain: Post): PostEntity {
        return PostEntity(
            id = domain.id,
            body = domain.body,
            title = domain.title,
            userId = domain.userId
        )
    }
}