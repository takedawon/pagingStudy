package com.lanic.pagingstudy.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lanic.pagingstudy.data.response.PokeiResponse
import com.lanic.pagingstudy.network.repository.RemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val remoteRepository: RemoteRepository) : ViewModel() {

    private val _pokeiList = MutableLiveData<PokeiResponse>()
    val pokeiList: LiveData<PokeiResponse> = _pokeiList

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
}