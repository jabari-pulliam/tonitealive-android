package com.tonitealive.app.domain.model

import com.google.gson.annotations.SerializedName


data class UserProfile (@SerializedName("profileId") val profileId: Long,
                        @SerializedName("username") val username: String)