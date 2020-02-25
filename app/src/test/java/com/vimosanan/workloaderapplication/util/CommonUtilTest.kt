package com.vimosanan.workloaderapplication.util

import org.junit.Assert.*
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class CommonUtilTest{

    @Test
    fun `should return customized date str when submit date obj`(){
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        format.timeZone = TimeZone.getTimeZone("UTC")
        val input = format.parse("2020-02-25T09:59:18.956153Z")!!

        val expectedValue = "2020-02-25 06:15 pm"

        val actualValue = CommonUtil.dateToString(input)
        assertEquals(expectedValue, actualValue)
    }
}