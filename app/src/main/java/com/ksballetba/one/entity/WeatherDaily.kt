package com.ksballetba.one.entity

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class WeatherDaily(
        val results: List<Result>
) {
    data class Result(
            val location: Location,
            val daily: List<Daily>,
            @SerializedName("last_update")
            val lastUpdate: String // 2018-09-07T11:00:00+08:00
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

        data class Daily(
                val date: String, // 2018-09-09
                @SerializedName("text_day")
                val textDay: String, // 多云
                @SerializedName("code_day")
                val codeDay: String, // 4
                @SerializedName("text_night")
                val textNight: String, // 多云
                @SerializedName("code_night")
                val codeNight: String, // 4
                val high: String, // 27
                val low: String, // 16
                val precip: String,
                @SerializedName("wind_direction")
                val windDirection: String, // 西南
                @SerializedName("wind_direction_degree")
                val windDirectionDegree: String, // 225
                @SerializedName("wind_speed")
                val windSpeed: String, // 10
                @SerializedName("wind_scale")
                val windScale: String // 2
        )
    }
    class Deserializer : ResponseDeserializable<WeatherDaily> {
        override fun deserialize(content: String): WeatherDaily? {
            return Gson().fromJson(content,WeatherDaily::class.java)
        }
    }
}