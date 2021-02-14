package com.lanic.pagingstudy.network.repository

import com.lanic.pagingstudy.data.response.PokeiDetailResponse
import com.lanic.pagingstudy.data.response.PokeiResponse
import com.lanic.pagingstudy.network.PokeiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteRepositoryImpl(private val pokeiService: PokeiService) : RemoteRepository {

    override fun getPokeiList(): Flow<PokeiResponse> {
        return flow {
            emit(pokeiService.getPokeiList())
        }.flowOn(Dispatchers.Default)
    }

    override fun getPokeiDetail(uid: Int): Flow<PokeiDetailResponse> {
        return flow {
            emit(pokeiService.getPokeiDetail(uid))
        }
    }
}

interface RemoteRepository {

    fun getPokeiList(): Flow<PokeiResponse>

    fun getPokeiDetail(uid: Int): Flow<PokeiDetailResponse>
}