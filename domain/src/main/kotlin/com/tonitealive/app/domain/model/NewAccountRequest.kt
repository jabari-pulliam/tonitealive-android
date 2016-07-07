package com.tonitealive.app.domain.model

import com.google.gson.annotations.SerializedName


data class NewAccountRequest (@SerializedName("username") val username: String,
                              @SerializedName("email") val email: String,
                              @SerializedName("password") val password: String)