package com.tonitealive.app.domain.model

import com.google.gson.annotations.SerializedName


data class AuthRequest (@SerializedName("grant_type") var grant_type: String,
                        @SerializedName("username") var username: String,
                        @SerializedName("password") var password: String)