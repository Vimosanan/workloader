package com.vimosanan.workloaderapplication.model

import com.google.gson.annotations.SerializedName

data class Manager(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("phone")
    val contactNumber: String? = null
)