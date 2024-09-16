package com.example.ktorclient.data.remote

import com.example.ktorclient.data.remote.dto.PostRequest
import com.example.ktorclient.data.remote.dto.PostResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

interface PostService {

    suspend fun getPost():List<PostResponse>

    suspend fun addPost(post:PostRequest):PostResponse?


    companion object{
        fun create():PostService{
            return PostServiceImp(
                HttpClient(Android){
                    install(ContentNegotiation){
                        json(
                            Json {
                                prettyPrint = true
                                isLenient  = true
                            }
                        )
                    }
                }
            )
        }
    }


}