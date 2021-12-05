package com.example.childapp.ui.splash.start.code

import com.example.childapp.remote.api.ApiClientService
import com.example.childapp.ui.splash.start.code.model.CodeRequest
import javax.inject.Inject

class ConfirmRepository @Inject constructor(
    private val api: ApiClientService
) {
    suspend fun checkParentsCode(codeRequest: CodeRequest) = api.checkParentCode(codeRequest)

    suspend fun sendNewCodeToParent() = api.sendNewToParent()

}