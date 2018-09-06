package ru.shadowsparky.scheduler.utils

open class TimeAndDateParser() {
    companion object {
        val HOUR = 0
        val MINUTE = 1
        val YEAR = HOUR
        val MONTH = MINUTE
        val DAY = 2

        fun parseDate(date: String): IntArray {
            val parsedArray = date.split('.')
            return intArrayOf(
                parsedArray[YEAR].toInt(), parsedArray[MONTH].toInt(), parsedArray[DAY].toInt()
            )
        }

        fun parseTime(Time: String): IntArray {
            val parsedArray = Time.split(':')
            return intArrayOf(
                    parsedArray[HOUR].toInt(), parsedArray[MINUTE].toInt()
            )
        }
    }
}