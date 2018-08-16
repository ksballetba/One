package com.ksballetba.one.entity

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class MoviePosterDetail(
        val res: Int, // 0
        val data: Data
) {
    data class Data(
            val id: String, // 1512
            val title: String, // ??
            val indexcover: String,
            val detailcover: String, // http://image.wufazhuce.com/FsCxW6mAwZcMKDkyvMfl548_Evvz
            val video: String,
            val verse: String,
            @SerializedName("verse_en")
            val verseEn: String,
            val score: Any?, // null
            val revisedscore: String, // 0
            val review: String,
            val keywords: String,
            @SerializedName("movie_id")
            val movieId: String,
            val info: String, // ??: ?????: ??? / ?????: ??? / ??? / ??? / ??? / ??? / ??? / ?????: ??????/??: ?? / ??
            val officialstory: String, // ???Cheryl????????????????1977???????????17?????????????????????????????????????????????????????????????????????????????????????????????????????????????????
            @SerializedName("hide_flag")
            val hideFlag: String, // 0
            @SerializedName("charge_edt")
            val chargeEdt: String, // ????????? afra@wufazhuce.com?
            @SerializedName("web_url")
            val webUrl: String, // http://m.wufazhuce.com/movie/1512
            val praisenum: Int, // 0
            val sort: String, // 0
            val releasetime: String, // 0000-00-00 00:00:00
            val scoretime: String, // 0000-00-00 00:00:00
            val maketime: String, // 2018-08-13 06:00:00
            @SerializedName("last_update_date")
            val lastUpdateDate: String, // 2018-08-10 17:47:53
            @SerializedName("read_num")
            val readNum: String, // 6000
            val directors: String, // ???
            @SerializedName("editor_email")
            val editorEmail: String, // afra@wufazhuce.com
            val related: String,
            @SerializedName("directors_id")
            val directorsId: String, // 6500808
            @SerializedName("start_video")
            val startVideo: String,
            @SerializedName("media_type")
            val mediaType: String, // 0
            val poster: String, // http://image.wufazhuce.com/Fg8tXtXEgVRhlebxrQ4dGXEdazXY
            val photo: List<String>,
            @SerializedName("next_id")
            val nextId: Int, // 0
            @SerializedName("previous_id")
            val previousId: String, // 1509
            @SerializedName("tag_list")
            val tagList: List<Any>,
            @SerializedName("share_list")
            val shareList: ShareList,
            val sharenum: Int, // 58
            val commentnum: Int, // 32
            val servertime: Int // 1534126318
    ) {
        data class ShareList(
                val wx: Wx,
                @SerializedName("wx_timeline")
                val wxTimeline: WxTimeline,
                val weibo: Weibo,
                val qq: Qq
        ) {
            data class Qq(
                    val title: String, // ?????????????
                    val desc: String, // ????????????????
                    val link: String, // http://m.wufazhuce.com/movie/1512?channel=qq
                    val imgUrl: String, // http://image.wufazhuce.com/ONE_logo_120_square.png
                    val audio: String
            )

            data class Wx(
                    val title: String, // ?? | ?????????????
                    val desc: String, // ?/?? ????????????????
                    val link: String, // http://m.wufazhuce.com/movie/1512?channel=singlemessage
                    val imgUrl: String, // http://image.wufazhuce.com/ONE_logo_120_square.png
                    val audio: String
            )

            data class WxTimeline(
                    val title: String, // ?? | ?????????????
                    val desc: String, // ?/?? ????????????????
                    val link: String, // http://m.wufazhuce.com/movie/1512?channel=timeline
                    val imgUrl: String, // http://image.wufazhuce.com/ONE_logo_120_square.png
                    val audio: String
            )

            data class Weibo(
                    val title: String, // ONE????? | ?????????????? ?/??? ???????????????? ?????http://m.wufazhuce.com/movie/1512?channel=weibo ??ONE??APP:http://weibo.com/p/100404157874
                    val desc: String,
                    val link: String, // http://m.wufazhuce.com/movie/1512?channel=weibo
                    val imgUrl: String,
                    val audio: String
            )
        }
    }
    class Deserializer : ResponseDeserializable<MoviePosterDetail> {
        override fun deserialize(content: String): MoviePosterDetail? {
            return Gson().fromJson(content,MoviePosterDetail::class.java)
        }
    }
}