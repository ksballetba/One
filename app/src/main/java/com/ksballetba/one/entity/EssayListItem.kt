package com.ksballetba.one.entity

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class EssayListItem(
        @SerializedName("content_id")
        val contentId: String, // 3417
        @SerializedName("hp_title")
        val hpTitle: String, // ?????
        @SerializedName("hp_makettime")
        val hpMakettime: String, // 2018-08-12 06:00:00
        @SerializedName("guide_word")
        val guideWord: String, // ?????????????????????????????????
        @SerializedName("start_video")
        val startVideo: String,
        val author: List<Author>,
        @SerializedName("has_audio")
        val hasAudio: Boolean, // false
        @SerializedName("author_list")
        val authorList: List<Author>
) {
    data class Author(
            @SerializedName("user_id")
            val userId: String, // 4814795
            @SerializedName("user_name")
            val userName: String, // ???
            val desc: String, // ???????????????????????
            @SerializedName("wb_name")
            val wbName: String, // @???
            @SerializedName("is_settled")
            val isSettled: String, // 0
            @SerializedName("settled_type")
            val settledType: String, // 0
            val summary: String, // ?????
            @SerializedName("fans_total")
            val fansTotal: String, // 1138
            @SerializedName("web_url")
            val webUrl: String // http://image.wufazhuce.com/FtYBiYEg2U6ggmLKgBZOsTKRqmJG
    )

        class Deserializer : ResponseDeserializable<EssayListItem> {
                override fun deserialize(content: String): EssayListItem? {
                        return Gson().fromJson(content,EssayListItem::class.java)
                }
        }
}

