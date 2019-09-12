package com.szilardmakai.notetakingappkotlin

import java.text.SimpleDateFormat
import java.util.*


const val MONTH_FORMAT = "MMM"

class Util {
    companion object {
        fun convertLongToDateString(time: Long): String {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = time
            val year = calendar.get(Calendar.YEAR)
            val month = SimpleDateFormat(MONTH_FORMAT, Locale.getDefault()).format(calendar.get(Calendar.MONTH))
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val hour = convertToDoubleDigit(calendar.get(Calendar.HOUR_OF_DAY).toString())
            val minute = convertToDoubleDigit(calendar.get(Calendar.MINUTE).toString())
            val second = convertToDoubleDigit(calendar.get(Calendar.SECOND).toString())

            return "$hour:$minute:$second  $day $month $year"
        }


        private fun convertToDoubleDigit(string: String): String {
            var newString = string
            if (string.length < 2) {
                newString = "0$string"
            }
            return newString
        }
    }
}