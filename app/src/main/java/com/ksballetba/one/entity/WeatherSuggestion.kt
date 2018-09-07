package com.ksballetba.one.entity

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class WeatherSuggestion(
        val results: List<Result>
) {
    data class Result(
            val location: Location,
            val suggestion: Suggestion,
            @SerializedName("last_update")
            val lastUpdate: String // 2018-09-07T14:10:41+08:00
    ) {
        data class Location(
                val id: String, // WTW3SJ5ZBJUY
                val name: String, // 上海
                val country: String, // CN
                val path: String, // 上海,上海,中国
                val timezone: String, // Asia/Shanghai
                @SerializedName("timezone_offset")
                val timezoneOffset: String // +08:00
        )

        data class Suggestion(
                @SerializedName("car_washing")
                val carWashing: CarWashing,
                val dressing: Dressing,
                val flu: Flu,
                val sport: Sport,
                val travel: Travel,
                val uv: Uv
        ) {
            data class Flu(
                    val brief: String, // 易发
                    val details: String
            )

            data class Sport(
                    val brief: String, // 较不宜
                    val details: String
            )

            data class Travel(
                    val brief: String, // 适宜
                    val details: String
            )

            data class Dressing(
                    val brief: String, // 舒适
                    val details: String
            )

            data class Uv(
                    val brief: String, // 最弱
                    val details: String
            )

            data class CarWashing(
                    val brief: String, // 不宜
                    val details: String
            )
        }
    }

    class Deserializer : ResponseDeserializable<WeatherSuggestion> {
        override fun deserialize(content: String): WeatherSuggestion? {
            return Gson().fromJson(content,WeatherSuggestion::class.java)
        }
    }
}