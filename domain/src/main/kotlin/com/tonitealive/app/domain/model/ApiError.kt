package com.tonitealive.app.domain.model

import com.google.gson.annotations.SerializedName


data class ApiError (@SerializedName("timestamp") val timestamp: Long,
                     @SerializedName("status") val status: Int,
                     @SerializedName("error") val error: String,
                     @SerializedName("exception") val exception: String,
                     @SerializedName("message") val message: String,
                     @SerializedName("path") val path: String)