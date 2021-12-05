package com.example.childapp.ui.splash.start.code

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.childapp.ui.splash.start.code.model.CodeRequest
import com.example.childapp.ui.splash.start.code.model.CodeResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ConfirmViewModel @Inject constructor(
    private val confirmRepository: ConfirmRepository
) : ViewModel() {


    private val _response = MutableLiveData<Response<CodeResponse>>()
    val response: LiveData<Response<CodeResponse>> = _response

    fun checkParentCode(codeRequest: CodeRequest) {
        viewModelScope.launch {
            val result = confirmRepository.checkParentsCode(codeRequest)
            if (result.isSuccessful){
                _response.value = result
            }
        }
    }
}
