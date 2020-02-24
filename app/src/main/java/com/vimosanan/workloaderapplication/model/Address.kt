package com.vimosanan.workloaderapplication.model

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("street_1")
    val streetOne: String? = null
)