package ru.shadowsparky.scheduler.utils
import android.util.Log

open class LogUtils {
    companion object {
        val MAIN_TAG : String = "MAIN_TAG"
        fun print(message : String) = Log.println(Log.DEBUG, MAIN_TAG, message)
    }
}