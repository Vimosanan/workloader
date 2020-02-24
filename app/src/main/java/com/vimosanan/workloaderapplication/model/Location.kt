package com.vimosanan.workloaderapplication.model

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("address")
    val address: Address? = null
)