package io.muhwyndhamhp.basemodule.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object TypeConverter {

    @TypeConverter
    @JvmStatic
    fun stringToIntList(data: String): List<Int> =
        Gson().fromJson(data, object : TypeToken<List<Int>>() {}.type)

    @TypeConverter
    @JvmStatic
    fun intListToString(data: List<Int>): String = Gson().toJson(data)
}