package com.ksballetba.one.entity

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class MovieListItem(
        val res: Int, // 0
        val data: List<Data>
) {
    data class Data(
            val id: String, // 1494
            val title: String, // ????
            val verse: String,
            @SerializedName("verse_en")
            val verseEn: String,
            val score: Any?, // null
            val revisedscore: String, // 0
            val releasetime: String, // 0000-00-00 00:00:00
            val scoretime: String, // 0000-00-00 00:00:00
            @SerializedName("start_video")
            val startVideo: String,
            val cover: String,
            @SerializedName("author_list")
            val authorList: List<Author>,
            val servertime: Int // 1534128433
    ) {
        data class Author(
                @SerializedName("user_id")
                val userId: String, // 9448908
                @SerializedName("user_name")
                val userName: String, // ????
                val desc: String, // ???
                @SerializedName("wb_name")
                val wbName: String,
                @SerializedName("is_settled")
                val isSettled: String, // 0
                @SerializedName("settled_type")
                val settledType: String, // 0
                val summary: String, // ???
                @SerializedName("fans_total")
                val fansTotal: String, // 36
                @SerializedName("web_url")
                val webUrl: String // http://image.wufazhuce.com/Fq-lx07oORH9L2mJHEiI_109etg3
        )
    }
    class Deserializer : ResponseDeserializable<MovieListItem> {
        override fun deserialize(content: String): MovieListItem? {
            return Gson().fromJson(content,MovieListItem::class.java)
        }
    }
}