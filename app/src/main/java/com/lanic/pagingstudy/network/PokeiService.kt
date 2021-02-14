package com.lanic.pagingstudy.network

import com.lanic.pagingstudy.data.response.PokeiResponse
import retrofit2.http.GET

interface PokeiService {

    @GET("pokemon/")
    suspend fun getPokeiList(): PokeiResponse
}