package com.lanic.pagingstudy.ui

import androidx.paging.PageKeyedDataSource
import com.lanic.pagingstudy.data.response.PokeiResponse
import com.lanic.pagingstudy.network.PokeiService
import com.lanic.pagingstudy.network.repository.RemoteRepository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

@ActivityRetainedScoped
class PokeiDataSource @Inject constructor(
    private val scope: CoroutineScope,
    private val remoteRepository: RemoteRepository
) : PageKeyedDataSource<String, PokeiResponse.Result>() {
    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, PokeiResponse.Result>
    ) {
        scope.launch {
            remoteRepository.getPokeiList()
                .catch { throttle ->
                    Timber.e(throttle)
                }.collect { response ->
                    val next = response.next
                    val previous = response.previous
                    callback.onResult(response.results, previous, next)
                }
        }
    }

    override fun loadBefore(
        params: LoadParams<String>,
        callback: LoadCallback<String, PokeiResponse.Result>
    ) {
        //https://pokeapi.co/api/v2/pokemon/?offset=20&limit=20
        val data = params.key.split("?")[1].split("&")

        val offset = data[0].split("=")[1]
        val limit = data[1].split("=")[1]

        scope.launch {
            remoteRepository.getPokeiList(offset, limit)
                .catch { throttle ->
                    Timber.e(throttle)
                }.collect { response ->
                    callback.onResult(response.results, response.previous)
                }
        }
    }

    override fun loadAfter(
        params: LoadParams<String>,
        callback: LoadCallback<String, PokeiResponse.Result>
    ) {
        //https://pokeapi.co/api/v2/pokemon/?offset=20&limit=20
        val data = params.key.split("?")[1].split("&")

        val offset = data[0].split("=")[1]
        val limit = data[1].split("=")[1]

        scope.launch {
            remoteRepository.getPokeiList(offset = offset, limit = limit)
                .catch { throttle ->
                    Timber.e(throttle)
                }.collect { response ->
                    callback.onResult(response.results, response.next)
                }
        }
    }
}