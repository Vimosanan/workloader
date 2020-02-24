package com.vimosanan.workloaderapplication.model

import com.google.gson.annotations.SerializedName

data class Client(
    @SerializedName("id")
    val id: String? = null,

    @SerializedName("name")
    val name: String? = null
)