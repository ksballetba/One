package com.ksballetba.one.entity

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class SerialListItem(
        val id: String, // 701
        @SerializedName("serial_id")
        val serialId: String, // 61
        val number: String, // 14
        val title: String, // ????? � ???? � ????
        val excerpt: String, // Joey??????????????????????
        @SerializedName("read_num")
        val readNum: String, // 13700
        val maketime: String, // 2018-08-09 06:00:00
        @SerializedName("start_video")
        val startVideo: String,
        val author: Author,
        @SerializedName("has_audio")
        val hasAudio: Boolean, // false
        @SerializedName("author_list")
        val authorList: List<Author>,
        @SerializedName("serial_list")
        val serialList: List<String>
) {
    data class Author(
            @SerializedName("user_id")
            val userId: String, // 4814711
            @SerializedName("user_name")
            val userName: String, // ??
            val desc: String, // ????????????
            @SerializedName("wb_name")
            val wbName: String, // @??vagrancy
            @SerializedName("is_settled")
            val isSettled: String, // 0
            @SerializedName("settled_type")
            val settledType: String, // 0
            val summary: String, // ????????????
            @SerializedName("fans_total")
            val fansTotal: String, // 11302
            @SerializedName("web_url")
            val webUrl: String // http://image.wufazhuce.com/FlKZMg2_6FZPZC6VfIyejb7lHnLl
    )
        class Deserializer : ResponseDeserializable<SerialListItem> {
                override fun deserialize(content: String): SerialListItem? {
                        return Gson().fromJson(content,SerialListItem::class.java)
                }
        }
}