package com.example.nesineassignment.core.util

import com.example.nesineassignment.core.remote.model.PostDto
import com.example.nesineassignment.core.remote.service.ApiService
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types.*
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.reflect.ParameterizedType
import java.nio.file.Paths

internal const val REQUEST_PATH: String = "/posts"
private val okHttpClient: OkHttpClient
    get() = OkHttpClient.Builder().build()

internal val moshi: Moshi
    get() = Moshi.Builder()
        .add(KotlinJsonAdapterFactory()).build()

internal val adapter: JsonAdapter<List<PostDto>>
    get() {
        val type: ParameterizedType = newParameterizedType(
            List::class.java,
            PostDto::class.java
        )
        return moshi.adapter(type)
    }

internal fun getJson(path: String): String {
    val file = Paths.get("src", "test", "resources", path).toFile()
    return String(file.readBytes())
}

internal fun makeApiService(mockWebServer: MockWebServer): ApiService = Retrofit.Builder()
    .baseUrl(mockWebServer.url("/"))
    .client(okHttpClient)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()
    .create(ApiService::class.java)