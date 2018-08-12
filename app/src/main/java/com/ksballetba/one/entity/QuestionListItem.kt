package com.ksballetba.one.entity

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class QuestionListItem(
        @SerializedName("question_id")
        val questionId: String, // 2188
        @SerializedName("question_title")
        val questionTitle: String, // ???????????????????
        @SerializedName("answer_title")
        val answerTitle: String,
        @SerializedName("answer_content")
        val answerContent: String, // ???????????????????????????????
        @SerializedName("question_makettime")
        val questionMakettime: String, // 2018-08-12 06:00:00
        @SerializedName("start_video")
        val startVideo: String,
        @SerializedName("author_list")
        val authorList: List<Author>,
        @SerializedName("asker_list")
        val askerList: List<Asker>
) {
    data class Author(
            @SerializedName("user_id")
            val userId: String, // 5541601
            @SerializedName("user_name")
            val userName: String, // ????
            val desc: String, // ????????????????????????????????????????????????????ID?staynormal?
            @SerializedName("wb_name")
            val wbName: String, // @????
            @SerializedName("is_settled")
            val isSettled: String, // 0
            @SerializedName("settled_type")
            val settledType: String, // 0
            val summary: String, // ??????????????????
            @SerializedName("fans_total")
            val fansTotal: String, // 10825
            @SerializedName("web_url")
            val webUrl: String // http://image.wufazhuce.com/Fk5FGFq5cICtxVG8qI1NbUmAVkWp
    )

    data class Asker(
            @SerializedName("user_id")
            val userId: String, // 0
            @SerializedName("user_name")
            val userName: String, // COS
            @SerializedName("web_url")
            val webUrl: String, // http://image.wufazhuce.com/placeholder-author-avatar.png
            val summary: String,
            val desc: String,
            @SerializedName("is_settled")
            val isSettled: String,
            @SerializedName("settled_type")
            val settledType: String,
            @SerializedName("fans_total")
            val fansTotal: String,
            @SerializedName("wb_name")
            val wbName: String
    )
        class Deserializer : ResponseDeserializable<QuestionListItem> {
                override fun deserialize(content: String): QuestionListItem? {
                        return Gson().fromJson(content,QuestionListItem::class.java)
                }
        }
}