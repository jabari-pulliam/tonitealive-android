package com.tonitealive.app.domain.model

import com.google.gson.annotations.SerializedName


data class AuthToken (@SerializedName("expires_in") val expiresIn: Long,
                      @SerializedName("token_type") val tokenType: String,
                      @SerializedName("access_token") val accessToken: String,
                      @SerializedName("refresh_token") val refreshToken: String?)