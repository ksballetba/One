package com.ksballetba.one.entity

import com.google.gson.annotations.SerializedName

data class SerialListItem(
        val id: String, // 703
        @SerializedName("serial_id")
        val serialId: String, // 61
        val number: String, // 16
        val title: String, // 风从海上来 · 第十七章 · 只有一次的冒险
        val excerpt: String, // 在她被推着远离这一刻，这一段狼狈颠簸的路途从来都没有清晰过。
        @SerializedName("read_num")
        val readNum: String, // 5600
        val maketime: String, // 2018-08-14 06:00:00
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
            val userName: String, // 姚瑶
            val desc: String, // 作家，翻译，独立摄影师。
            @SerializedName("wb_name")
            val wbName: String, // @姚瑶vagrancy
            @SerializedName("is_settled")
            val isSettled: String, // 0
            @SerializedName("settled_type")
            val settledType: String, // 0
            val summary: String, // 作家，翻译，独立摄影师。
            @SerializedName("fans_total")
            val fansTotal: String, // 11349
            @SerializedName("web_url")
            val webUrl: String // http://image.wufazhuce.com/FlKZMg2_6FZPZC6VfIyejb7lHnLl
    )
}