package com.example.testprojek.util

object Format {

    /**
     timeFormat(9, 45)
     09.45
     timeFormat(10, 9)
     10.09
     */
    fun timeFormat(hour: Int, minute: Int): String {
        return if (hour < 10 && minute < 10)
            String.format("0$hour.0$minute")
        else if (hour < 10)
            String.format("0$hour.$minute")
        else if (minute < 10)
            String.format("$hour.0$minute")
        else
            String.format("$hour.$minute")
    }
}