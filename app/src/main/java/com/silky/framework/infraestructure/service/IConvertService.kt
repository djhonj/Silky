package com.silky.framework.infraestructure.service

import com.silky.domain.VideoYT
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface IConvertService {
    @GET("api/search")
    fun getMessage(): String

    @POST("api/search")
    //fun getVideoList(@Body search: String): Call<List<VideoYT>>
    suspend fun getVideoList(@Body search: String): MutableList<VideoYT>

    @POST("api/download")
    //suspend fun download(@Body link: String): String
    suspend fun download(@Body video: VideoYT): String
}