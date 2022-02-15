package com.example.nesineassignment.core.util

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.net.HttpURLConnection

class RequestDispatcher: Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when(request.path){
            REQUEST_PATH->{
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson(POSTS_RESPONSE_PATH))
            }
            else ->  throw IllegalArgumentException("Unknown Request Path ${request.path}")
        }
    }
}