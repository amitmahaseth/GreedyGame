package com.example.greedygame.network

import com.example.greedygame.pojo.AlbumResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterFace {
    @GET("/2.0/")
    suspend fun getHomeAlbum(
        @Query("method") _pageNumber: String,
        @Query("tag") tag: String,
        @Query("api_key") api_key: String,
        @Query("format") format: String,
       ): Response<AlbumResponse>

}