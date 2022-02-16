package com.example.nesineassignment.core.remote.mapper

import com.example.nesineassignment.core.util.DummyData
import com.google.common.truth.Truth
import org.junit.Test

class PostMapperTest {

    private val mapper = PostMapper()

    @Test
    fun `check that mapFromModel map correctly`() {
        val dto = DummyData.postDto
        val entity = mapper.mapFromModel(dto)
        Truth.assertThat(entity.title).isEqualTo(dto.title)
    }
}