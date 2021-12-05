package com.example.childapp.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.childapp.remote.api.Message
import com.example.childapp.remote.model.AppList
import com.example.childapp.common.utils.showLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    private val _response = MutableLiveData<Message>()
    val messageAllApps: LiveData<Message>
        get() = _response


    fun sendAllApps(allApps: AppList) {
        viewModelScope.launch {
            repository.sendAllApps(allApps).let { response ->
                if (response.isSuccessful) {
                    _response.postValue(response.body())
                } else {
                    showLog( " some error ${response.code()}")
                }
            }
        }
    }


}