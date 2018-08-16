package com.ksballetba.one.entity

import com.google.gson.annotations.SerializedName

data class ReadingList(
        val res: Int, // 0
        val data: List<Data>
) {
    data class Data(
            val date: String, // 2018-08-10
            val items: List<Item>
    ) {
        data class Item(
                val time: String, // 2018-08-10 06:00:00
                val type: Int, // 1
                val content: Content
        ) {
            data class Content(
                    @SerializedName("content_id")
                    val contentId: String, // 3411
                    @SerializedName("hp_title")
                    val hpTitle: String, // ???????????????
                    @SerializedName("hp_makettime")
                    val hpMakettime: String, // 2018-08-10 06:00:00
                    @SerializedName("guide_word")
                    val guideWord: String, // ??????????????????
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
                        val userId: String, // 6258584
                        @SerializedName("user_name")
                        val userName: String, // ???
                        val desc: String, // ??????????????????????????GQ????2012?????????????????????
                        @SerializedName("wb_name")
                        val wbName: String, // @???
                        @SerializedName("is_settled")
                        val isSettled: String, // 0
                        @SerializedName("settled_type")
                        val settledType: String, // 0
                        val summary: String, // ??????????????????????????GQ????2012?????????????????????
                        @SerializedName("fans_total")
                        val fansTotal: String, // 6760
                        @SerializedName("web_url")
                        val webUrl: String // http://image.wufazhuce.com/Fp4wC5cAQGZ9GNKiKvFeN8BBcHBF
                )
            }
        }
    }
}