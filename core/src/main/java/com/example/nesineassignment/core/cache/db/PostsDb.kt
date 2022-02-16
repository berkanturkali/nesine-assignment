package com.example.nesineassignment.core.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nesineassignment.core.BuildConfig
import com.example.nesineassignment.core.cache.dao.PostsDao
import com.example.nesineassignment.core.cache.model.PostEntity

@Database(
    entities = [PostEntity::class],
    version = BuildConfig.databaseVersion,
    exportSchema = false
)
abstract class PostsDb : RoomDatabase() {

    abstract val postsDao: PostsDao

    companion object {
        private const val DB_NAME: String = "posts_db"
        fun build(context: Context): PostsDb = Room.databaseBuilder(
            context.applicationContext,
            PostsDb::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration().build()
    }
}