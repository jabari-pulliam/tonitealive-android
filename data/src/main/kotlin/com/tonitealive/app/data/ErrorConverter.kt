package com.tonitealive.app.data

import com.tonitealive.app.domain.model.ApiError

interface ErrorConverter {

    fun convertToApiError(str: String): ApiError

}