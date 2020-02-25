package com.vimosanan.workloaderapplication.model

import com.google.gson.annotations.SerializedName

data class AccessToken(
    @SerializedName("key")
    val key: String? = null
)