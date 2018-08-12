package com.ksballetba.one.entity

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class ReadingList(
        val res: Int, // 0
        val data: Data
) {
    data class Data(
            val essay: List<Essay>,
            val serial: List<Serial>,
            val question: List<Question>
    ) {
        data class Question(
                @SerializedName("question_id")
                val questionId: String, // 2178
                @SerializedName("question_title")
                val questionTitle: String, // ?????????????????????????
                @SerializedName("answer_title")
                val answerTitle: String,
                @SerializedName("answer_content")
                val answerContent: String, // ??????????????????????????????
                @SerializedName("question_makettime")
                val questionMakettime: String, // 2018-08-03 06:00:00
                @SerializedName("start_video")
                val startVideo: String,
                @SerializedName("author_list")
                val authorList: List<Author>,
                @SerializedName("asker_list")
                val askerList: List<Asker>
        ) {
            data class Author(
                    @SerializedName("user_id")
                    val userId: String, // 9307705
                    @SerializedName("user_name")
                    val userName: String, // ???
                    val desc: String, // ???????????????????????????????????
                    @SerializedName("wb_name")
                    val wbName: String,
                    @SerializedName("is_settled")
                    val isSettled: String, // 0
                    @SerializedName("settled_type")
                    val settledType: String, // 0
                    val summary: String, // ??????????
                    @SerializedName("fans_total")
                    val fansTotal: String, // 770
                    @SerializedName("web_url")
                    val webUrl: String // http://image.wufazhuce.com/FkJEAiceMwCqYtAoj6-GFnhYM_O-
            )

            data class Asker(
                    @SerializedName("user_id")
                    val userId: String, // 0
                    @SerializedName("user_name")
                    val userName: String, // ??
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
        }

        data class Serial(
                val id: String, // 688
                @SerializedName("serial_id")
                val serialId: String, // 62
                val number: String, // 5
                val title: String, // ????????�???
                val excerpt: String, // ????????????????????????????
                @SerializedName("read_num")
                val readNum: String, // 18000
                val maketime: String, // 2018-08-01 06:00:00
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
                    val userId: String, // 7609609
                    @SerializedName("user_name")
                    val userName: String, // ???
                    val desc: String, // ???????????????
                    @SerializedName("wb_name")
                    val wbName: String,
                    @SerializedName("is_settled")
                    val isSettled: String, // 0
                    @SerializedName("settled_type")
                    val settledType: String, // 0
                    val summary: String, // ???????????????
                    @SerializedName("fans_total")
                    val fansTotal: String, // 1376
                    @SerializedName("web_url")
                    val webUrl: String // http://image.wufazhuce.com/FidpPXiSppV_kEZ-pZsDOwYOXKYk
            )
        }

        data class Essay(
                @SerializedName("content_id")
                val contentId: String, // 3406
                @SerializedName("hp_title")
                val hpTitle: String, // ?
                @SerializedName("hp_makettime")
                val hpMakettime: String, // 2018-08-07 06:00:00
                @SerializedName("guide_word")
                val guideWord: String, // ?????????????????????????????
                @SerializedName("start_video")
                val startVideo: String,
                val author: List<Author>,
                @SerializedName("has_audio")
                val hasAudio: Boolean, // true
                @SerializedName("author_list")
                val authorList: List<Author>
        ) {
            data class Author(
                    @SerializedName("user_id")
                    val userId: String, // 9517511
                    @SerializedName("user_name")
                    val userName: String, // ???
                    val desc: String, // ??????70????????
                    @SerializedName("wb_name")
                    val wbName: String,
                    @SerializedName("is_settled")
                    val isSettled: String, // 0
                    @SerializedName("settled_type")
                    val settledType: String, // 0
                    val summary: String, // ??????70????????
                    @SerializedName("fans_total")
                    val fansTotal: String, // 50
                    @SerializedName("web_url")
                    val webUrl: String // http://image.wufazhuce.com/Fhw41VJwVwFvY4ADACIrgpJLUrh1
            )
        }
    }

    class Deserializer : ResponseDeserializable<ReadingList> {
        override fun deserialize(content: String): ReadingList? {
            return Gson().fromJson(content,ReadingList::class.java)
        }
    }
}