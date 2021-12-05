package com.example.childapp.ui.splash.start.code.data

import com.example.childapp.ui.splash.start.code.model.CodeResponse

data class ConfirmCodeState(
    val isLoading: Boolean = false,
    val confirmCode: CodeResponse? = null,
    val error: String = ""
)