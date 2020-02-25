package com.vimosanan.workloaderapplication.util

import java.util.*

object CommonUtil {

    //change date obj to yyyy-mm-dd hh:mm am/pm
    fun dateToString(date: Date): String{
        val calendar = Calendar.getInstance()
        calendar.time = date
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        var hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        var monthStr = month.toString()
        var dayStr = day.toString()
        if(month<10){
            monthStr = "0$month"
        }
        if(day<10){
            dayStr = "0$day"
        }
        val dateString = "$year-$monthStr-$dayStr"


        var amPm = ""
        if(hour>12){
            amPm = "pm"
            hour -= 12
        }else if(hour>11){
            amPm = "pm"
        }else{
            amPm = "am"
        }

        var hourStr = hour.toString()
        var minuteStr = minute.toString()
        if(hour<10){
            hourStr = "0$hour"
        }
        if(minute<10){
            minuteStr = "0$minute"
        }
        return "$dateString $hourStr:$minuteStr $amPm"
    }
}