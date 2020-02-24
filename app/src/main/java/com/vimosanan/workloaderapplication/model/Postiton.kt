package com.vimosanan.workloaderapplication.model

import com.google.gson.annotations.SerializedName

data class Postiton(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("active")
    val isActive: Boolean = false

)