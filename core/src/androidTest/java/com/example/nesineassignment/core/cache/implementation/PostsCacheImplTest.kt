package com.example.nesineassignment.core.cache.implementation

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.nesineassignment.DummyData
import com.example.nesineassignment.core.cache.abstraction.PostsCache
import com.example.nesineassignment.core.cache.db.PostsDb
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PostsCacheImplTest {

    private lateinit var postsDb: PostsDb

    private lateinit var postsCache: PostsCache

    @Before
    fun setup() {
        postsDb = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PostsDb::class.java
        ).allowMainThreadQueries().build()

        postsCache = PostsCacheImpl(
            postsDb.postsDao
        )
    }

    @Test
    fun upsert_insertData_successfully() = runBlocking {
        val entity = DummyData.postEntity
        postsCache.upsert(entity)
        val result = postsCache.posts().first()?.first()
        Truth.assertThat(result).isNotNull()
        Truth.assertThat(result!!.title).isEqualTo(entity.title)
        Truth.assertThat(result.body).isEqualTo(entity.body)
    }

    @Test
    fun upsertUpdateData_when_dataAlreadyInDb() = runBlocking {
        val entity = DummyData.postEntity
        postsCache.upsert(entity)
        val result = postsCache.posts().first()?.first()
        Truth.assertThat(result!!.title).isEqualTo(entity.title)
        postsCache.upsert(entity.copy(title = "new title"))
        val newResult = postsCache.posts().first()?.first()
        Truth.assertThat(newResult!!.title).isEqualTo("new title")
    }

    @Test
    fun insertAll_insertsListOfPosts() {
        runBlocking {
            val entityList = listOf(DummyData.postEntity)
            postsCache.insertAll(entityList)
            val result = postsCache.posts().first()
            Truth.assertThat(result).isNotNull()
            Truth.assertThat(result!!).containsExactlyElementsIn(entityList)
        }
    }

    @Test
    fun delete_deletesData_successfully() = runBlocking {
        val entity = DummyData.postEntity
        postsCache.upsert(entity)
        val result = postsCache.posts().first()
        Truth.assertThat(result).isNotEmpty()
        postsCache.delete(entity)
        val newResult = postsCache.posts().first()
        Truth.assertThat(newResult).doesNotContain(entity)
    }

    @Test
    fun update_updatesData_successfully() = runBlocking {
        val entity = DummyData.postEntity
        postsCache.upsert(entity)
        val result = postsCache.posts().first()
        Truth.assertThat(result!!).contains(entity)
        postsCache.update(entity.copy(title = "new"))
        val new = postsCache.posts().first()?.first()
        Truth.assertThat(new!!.title).isEqualTo("new")
    }

    @Test
    fun clear_clearAllItems() = runBlocking {
        val entity = DummyData.postEntity
        postsCache.upsert(entity)
        val result = postsCache.posts().first()
        Truth.assertThat(result!!).isNotEmpty()
        postsCache.clear()
        Truth.assertThat(postsCache.posts().first()).isEmpty()

    }

    @After
    fun tearDown(){
        postsDb.close()
    }

}
