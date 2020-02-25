package com.vimosanan.workloaderapplication.model

import com.google.gson.annotations.SerializedName

data class TimeSheet(
    @SerializedName("timesheet")
    val clockInOut: ClockInOutObj? = null
)