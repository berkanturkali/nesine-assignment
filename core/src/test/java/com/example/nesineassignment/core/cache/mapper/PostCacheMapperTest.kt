package com.example.nesineassignment.core.cache.mapper

import com.example.nesineassignment.core.BuildConfig
import com.example.nesineassignment.core.util.DummyData
import com.google.common.truth.Truth
import org.junit.Test

class PostCacheMapperTest {

    private val mapper = PostCacheMapper()

    @Test
    fun `check that map from entity map data correctly`() {
        val entity = DummyData.postEntity
        val domain = mapper.mapFromEntity(entity)
        Truth.assertThat(domain.image)
            .isEqualTo("${BuildConfig.BASE_IMAGE_URL}/id/${entity.id}/200/")
    }

    @Test
    fun `check that map to entity map data correctly`(){
        val domain = DummyData.post
        val entity = mapper.mapToEntity(domain)
        Truth.assertThat(domain.id).isEqualTo(entity.id)
    }
}