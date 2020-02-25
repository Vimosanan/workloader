package com.vimosanan.workloaderapplication.model

import com.google.gson.annotations.SerializedName

data class ClockInOutObj(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("clock_in_time")
    val clockInTime: String? = null,

    @SerializedName("clock_out_time")
    val clockOutTime: String? = null


)