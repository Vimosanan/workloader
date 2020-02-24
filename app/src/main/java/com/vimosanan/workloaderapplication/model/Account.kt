package com.vimosanan.workloaderapplication.model

import com.google.gson.annotations.SerializedName

data class Account (
    @SerializedName("id")
    val id: String? = null,

    @SerializedName("wage_amount")
    val wageAmount: Float? = null,

    @SerializedName("wage_type")
    val wageType: String? = null,

    @SerializedName("client")
    val client: Client? = null,

    @SerializedName("location")
    val location: Location? = null,

    @SerializedName("position")
    val position: Postiton? = null,

    @SerializedName("manager")
    val manager: Manager? = null
)