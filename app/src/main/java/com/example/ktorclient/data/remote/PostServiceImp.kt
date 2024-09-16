package com.example.ktorclient.data.remote


import com.example.ktorclient.data.remote.dto.PostRequest
import com.example.ktorclient.data.remote.dto.PostResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

class PostServiceImp(
   private val client: HttpClient
) :PostService{
    override suspend fun getPost(): List<PostResponse> {
    return  try {
        client.get {
            url(HttpRoutes.POST)
        }.body()
    }catch (e:ClientRequestException){
        println("Error:${e.response.status.description}")
        emptyList<PostResponse>()
    }

    }

    override suspend fun addPost(post: PostRequest): PostResponse? {
        return try {
            client.post {
                url (HttpRoutes.POST)
                contentType(ContentType.Application.Json)
                setBody(post)
            }.body()
        }
        catch (e:ClientRequestException){
            println("Error:${e.response.status.description}")
            null
            }
    }
}