package com.lanic.pagingstudy.network

import com.lanic.pagingstudy.data.response.PokeiResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface PokeiNetwork {

    @GET("pokemon/")
    fun getPokeiList(): Flow<PokeiResponse>
}