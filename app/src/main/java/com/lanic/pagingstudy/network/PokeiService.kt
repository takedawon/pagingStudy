package com.lanic.pagingstudy.network

import com.lanic.pagingstudy.data.response.PokeiDetailResponse
import com.lanic.pagingstudy.data.response.PokeiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeiService {

    @GET("v2/pokemon/")
    suspend fun getPokeiList(): PokeiResponse

    @GET("v1/pokemon/{uid}")
    suspend fun getPokeiDetail(@Path("uid") uid: Int): PokeiDetailResponse

}