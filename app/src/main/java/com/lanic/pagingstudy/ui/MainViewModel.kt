package com.lanic.pagingstudy.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.lanic.pagingstudy.data.response.PokeiResponse
import com.lanic.pagingstudy.network.repository.RemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val remoteRepository: RemoteRepository) :
    ViewModel() {

    private val _pokeiList = MutableLiveData<PokeiResponse>()
    val pokeiList: LiveData<PokeiResponse> = _pokeiList

    val pokeiPagingList = createPokeiLivedata()

    fun getPokeiList() {
        viewModelScope.launch {
            remoteRepository.getPokeiList()
                .catch { throwable ->
                    Timber.e(throwable)
                }.collect {
                    Timber.tag("test").e("실행")
                    _pokeiList.value = it
                    Timber.e(it.toString())
                }
        }
    }

    fun getPokeiList(offset: String, limit: String) {
        viewModelScope.launch {
            remoteRepository.getPokeiList(offset, limit)
                .catch { throttle ->
                    Timber.e(throttle)
                }.collect {
                    _pokeiList.value = it
                }
        }
    }

    private fun createPokeiLivedata(): LiveData<PagedList<PokeiResponse.Result>> {
        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(20)
            .setPageSize(20)
            .setPrefetchDistance(10)
            .build()
        return LivePagedListBuilder(object : DataSource.Factory<String, PokeiResponse.Result>() {
            override fun create(): DataSource<String, PokeiResponse.Result> {
                return PokeiDataSource(viewModelScope, remoteRepository)
            }
        }, config).build()
    }
}