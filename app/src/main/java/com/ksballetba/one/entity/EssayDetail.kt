package com.ksballetba.one.entity

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class EssayDetail(
        val res: Int, // 0
        val data: Data
) {
    data class Data(
            @SerializedName("content_id")
            val contentId: String, // 3416
            @SerializedName("hp_title")
            val hpTitle: String, // ???????
            @SerializedName("sub_title")
            val subTitle: String,
            @SerializedName("hp_author")
            val hpAuthor: String, // ???
            @SerializedName("auth_it")
            val authIt: String, // ?????????????
            @SerializedName("hp_author_introduce")
            val hpAuthorIntroduce: String, // ????????? afra@wufazhuce.com?
            @SerializedName("hp_content")
            val hpContent: String, // <p>1
            @SerializedName("hp_makettime")
            val hpMakettime: String, // 2018-08-13 08:00:00
            @SerializedName("hide_flag")
            val hideFlag: String, // 0
            @SerializedName("wb_name")
            val wbName: String,
            @SerializedName("wb_img_url")
            val wbImgUrl: String,
            @SerializedName("last_update_date")
            val lastUpdateDate: String, // 2018-08-12 20:02:48
            @SerializedName("web_url")
            val webUrl: String, // http://m.wufazhuce.com/article/3416
            @SerializedName("guide_word")
            val guideWord: String, // ??????????????????????????
            val audio: String,
            val anchor: String,
            @SerializedName("editor_email")
            val editorEmail: String, // afra@wufazhuce.com
            @SerializedName("top_media_type")
            val topMediaType: String, // 0
            @SerializedName("top_media_file")
            val topMediaFile: String,
            @SerializedName("top_media_image")
            val topMediaImage: String,
            @SerializedName("start_video")
            val startVideo: String,
            val copyright: String, // ??? | christopher-sardegna
            @SerializedName("audio_duration")
            val audioDuration: String, // 0
            val cover: String, // 0
            val author: List<Author>,
            val maketime: String, // 2018-08-13 08:00:00
            @SerializedName("author_list")
            val authorList: List<Author>,
            @SerializedName("next_id")
            val nextId: Int, // 0
            @SerializedName("previous_id")
            val previousId: String, // 3417
            @SerializedName("tag_list")
            val tagList: List<Tag>,
            @SerializedName("share_list")
            val shareList: ShareList,
            val praisenum: Int, // 1268
            val sharenum: Int, // 273
            val commentnum: Int // 185
    ) {
        data class Tag(
                val id: String, // 7
                val title: String // ONE STORY
        )

        data class ShareList(
                val wx: Wx,
                @SerializedName("wx_timeline")
                val wxTimeline: WxTimeline,
                val weibo: Weibo,
                val qq: Qq
        ) {
            data class Wx(
                    val title: String, // ONE STORY | ???????
                    val desc: String, // ?/??? ??????????????????????????
                    val link: String, // http://m.wufazhuce.com/article/3416?channel=singlemessage
                    val imgUrl: String, // http://image.wufazhuce.com/ONE_logo_120_square.png
                    val audio: String
            )

            data class WxTimeline(
                    val title: String, // ONE STORY | ???????
                    val desc: String, // ?/??? ??????????????????????????
                    val link: String, // http://m.wufazhuce.com/article/3416?channel=timeline
                    val imgUrl: String, // http://image.wufazhuce.com/ONE_logo_120_square.png
                    val audio: String
            )

            data class Weibo(
                    val title: String, // ONE???ONE STORY | ???????? ?/???? ?????????????????????????? ?????http://m.wufazhuce.com/article/3416?channel=weibo ??ONE??APP:http://weibo.com/p/100404157874
                    val desc: String,
                    val link: String, // http://m.wufazhuce.com/article/3416?channel=weibo
                    val imgUrl: String,
                    val audio: String
            )

            data class Qq(
                    val title: String, // ???????
                    val desc: String, // ??????????????????????????
                    val link: String, // http://m.wufazhuce.com/article/3416?channel=qq
                    val imgUrl: String, // http://image.wufazhuce.com/ONE_logo_120_square.png
                    val audio: String
            )
        }

        data class Author(
                @SerializedName("user_id")
                val userId: String, // 8741452
                @SerializedName("user_name")
                val userName: String, // ???
                val desc: String, // ?????????????
                @SerializedName("wb_name")
                val wbName: String,
                @SerializedName("is_settled")
                val isSettled: String, // 0
                @SerializedName("settled_type")
                val settledType: String, // 0
                val summary: String, // ?????????????
                @SerializedName("fans_total")
                val fansTotal: String, // 2661
                @SerializedName("web_url")
                val webUrl: String // http://image.wufazhuce.com/FjKfi58DFi5Xrwnc7a6Ac8DDTIEI
        )
    }
    class Deserializer : ResponseDeserializable<EssayDetail> {
        override fun deserialize(content: String): EssayDetail? {
            return Gson().fromJson(content,EssayDetail::class.java)
        }
    }
}