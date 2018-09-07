package com.ksballetba.one.entity

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class WeatherNow(
        val results: List<Result>
) {
    data class Result(
            val location: Location,
            val now: Now,
            @SerializedName("last_update")
            val lastUpdate: String // 2018-09-07T14:15:00+08:00
    ) {
        data class Location(
                val id: String, // WX4FBXXFKE4F
                val name: String, // 北京
                val country: String, // CN
                val path: String, // 北京,北京,中国
                val timezone: String, // Asia/Shanghai
                @SerializedName("timezone_offset")
                val timezoneOffset: String // +08:00
        )

        data class Now(
                val text: String, // 晴
                val code: String, // 0
                val temperature: String // 28
        )
    }
    class Deserializer : ResponseDeserializable<WeatherNow> {
        override fun deserialize(content: String): WeatherNow? {
            return Gson().fromJson(content,WeatherNow::class.java)
        }
    }
}