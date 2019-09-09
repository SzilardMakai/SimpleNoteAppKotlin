package com.szilardmakai.notetakingappkotlin

import java.text.SimpleDateFormat
import java.util.*


const val MONTH_FORMAT = "MMM"
const val TIME_FORMAT_FROM_DATE = "hh:mm"

class Util {
    companion object {
        fun convertLongToDateString(time: Long): String {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = time
            val year = calendar.get(Calendar.YEAR)
            val month = SimpleDateFormat(MONTH_FORMAT).format(calendar.get(Calendar.MONTH))
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            var second = calendar.get(Calendar.SECOND).toString()
            if (second.length < 2) {
                second = "0$second"
            }
            return "$hour:$minute:$second $day $month $year"
        }
    }
}