package com.lanic.pagingstudy.network

import com.lanic.pagingstudy.data.response.PokeiDetailResponse
import com.lanic.pagingstudy.data.response.PokeiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeiService {

    @GET("v2/pokemon/")
    suspend fun getPokeiList(): PokeiResponse

    @GET("v2/pokemon/")
    suspend fun getPokeiList(
        @Query("offset") offset: String,
        @Query("limit") limit: String
    ): PokeiResponse

    @GET("v1/pokemon/{uid}")
    suspend fun getPokeiDetail(@Path("uid") uid: Int): PokeiDetailResponse

}