package com.lanic.pagingstudy.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lanic.pagingstudy.utils.Event

class BaseViewModel : ViewModel() {

    private val _loading = MutableLiveData<Event<Boolean>>()
    val loading: LiveData<Event<Boolean>> = _loading

    fun showLoading() {
        _loading.value = Event(true)
    }

}