package com.ksballetba.one.entity

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class OneDetail(
        val res: Int, // 0
        val data: Data
) {
    data class Data(
            val id: String, // 4215
            val weather: Weather,
            val date: String, // 2017-06-25 06:00:00
            @SerializedName("content_list")
            val contentList: List<Content>
    ) {
        data class Content(
                val id: String, // 12264
                val category: String, // 5
                @SerializedName("display_category")
                val displayCategory: String, // 1
                @SerializedName("item_id")
                val itemId: String, // 860
                val title: String, // 即使已看过100遍，但何以解忧？还是唯有《老友记》
                val forward: String, // 时间值得浪费，也要浪费在让你开心的事物上。
                @SerializedName("img_url")
                val imgUrl: String, // http://image.wufazhuce.com/FpyBRcP6YQmV2vGmOeEkQkG7jNQg
                @SerializedName("like_count")
                val likeCount: Int, // 0
                @SerializedName("post_date")
                val postDate: String, // 2017-06-25 06:00:00
                @SerializedName("last_update_date")
                val lastUpdateDate: String, // 2017-06-24 22:46:24
                val author: Author,
                @SerializedName("video_url")
                val videoUrl: String,
                @SerializedName("audio_url")
                val audioUrl: String,
                @SerializedName("audio_platform")
                val audioPlatform: Int, // 2
                @SerializedName("start_video")
                val startVideo: String,
                val volume: String, // 0
                @SerializedName("pic_info")
                val picInfo: String,
                @SerializedName("words_info")
                val wordsInfo: String,
                val subtitle: String, // 电影:老友记
                val number: Int, // 0
                @SerializedName("serial_id")
                val serialId: Int, // 0
                @SerializedName("serial_list")
                val serialList: List<Any>,
                @SerializedName("movie_story_id")
                val movieStoryId: String, // 3843
                @SerializedName("ad_id")
                val adId: Int, // 0
                @SerializedName("ad_type")
                val adType: Int, // 0
                @SerializedName("ad_pvurl")
                val adPvurl: String,
                @SerializedName("ad_linkurl")
                val adLinkurl: String,
                @SerializedName("ad_makettime")
                val adMakettime: String,
                @SerializedName("ad_closetime")
                val adClosetime: String,
                @SerializedName("ad_share_cnt")
                val adShareCnt: String,
                @SerializedName("ad_pvurl_vendor")
                val adPvurlVendor: String,
                @SerializedName("content_id")
                val contentId: String, // 860
                @SerializedName("content_type")
                val contentType: String, // 5
                @SerializedName("content_bgcolor")
                val contentBgcolor: String, // #FFFFFF
                @SerializedName("share_url")
                val shareUrl: String, // http://m.wufazhuce.com/movie/860
                @SerializedName("share_info")
                val shareInfo: ShareInfo,
                @SerializedName("share_list")
                val shareList: ShareList,
                @SerializedName("tag_list")
                val tagList: List<Any>
        ) {
            data class ShareList(
                    val wx: Wx,
                    @SerializedName("wx_timeline")
                    val wxTimeline: WxTimeline,
                    val weibo: Weibo,
                    val qq: Qq
            ) {
                data class WxTimeline(
                        val title: String, // 电影 | 即使已看过100遍，但何以解忧？还是唯有《老友记》
                        val desc: String, // 文/ 小豆 时间值得浪费，也要浪费在让你开心的事物上。
                        val link: String, // http://m.wufazhuce.com/movie/860?channel=timeline
                        val imgUrl: String, // http://image.wufazhuce.com/ONE_logo_120_square.png
                        val audio: String
                )

                data class Qq(
                        val title: String, // 即使已看过100遍，但何以解忧？还是唯有《老友记》
                        val desc: String, // 时间值得浪费，也要浪费在让你开心的事物上。
                        val link: String, // http://m.wufazhuce.com/movie/860?channel=qq
                        val imgUrl: String, // http://image.wufazhuce.com/ONE_logo_120_square.png
                        val audio: String
                )

                data class Wx(
                        val title: String, // 电影 | 即使已看过100遍，但何以解忧？还是唯有《老友记》
                        val desc: String, // 文/ 小豆 时间值得浪费，也要浪费在让你开心的事物上。
                        val link: String, // http://m.wufazhuce.com/movie/860?channel=singlemessage
                        val imgUrl: String, // http://image.wufazhuce.com/ONE_logo_120_square.png
                        val audio: String
                )

                data class Weibo(
                        val title: String, // ONE一个《电影 | 即使已看过100遍，但何以解忧？还是唯有《老友记》》 文/ 小豆： 时间值得浪费，也要浪费在让你开心的事物上。 阅读全文：http://m.wufazhuce.com/movie/860?channel=weibo 下载ONE一个APP:http://weibo.com/p/100404157874
                        val desc: String,
                        val link: String, // http://m.wufazhuce.com/movie/860?channel=weibo
                        val imgUrl: String,
                        val audio: String
                )
            }

            data class ShareInfo(
                    val url: String, // http://m.wufazhuce.com/movie/860
                    val image: String, // http://image.wufazhuce.com/FpyBRcP6YQmV2vGmOeEkQkG7jNQg
                    val title: String, // 「一个」电影: 老友记
                    val content: String // 前一阵子朱皮突然离婚，我的第一反应是：好想再看一遍《老友记》。好像不管发生什么，解药都是看一遍《老友记》。我已经看了很多遍《老友记》，从哪儿放都能接上。毕竟这个剧，剧本流畅，既怪咖又主流。真是好神奇。不知不觉间，曼哈顿街景在看剧中变得亲切。莫妮卡的公寓显然就在华盛顿广场附近，在大大有名的格林威治村。那里离纽约大学近，住着很多艺术家。而我们小时候学的课文——欧亨利的《最后一片叶子》，开篇第一句话就是“在华盛顿广场西边的一个小区里……”。对，这个故事里的画家们就住在华盛顿广场西边的格林威治村。 《老友记》中处处可见令人眼熟的曼哈顿街景，这里是Ross和他的倒不出来的老爷车我很快就断断续续地看到了第六季。Rachel和Ross的关系变得有点儿找抽；Chandler开始胖到方头方脑没脖子（后来证明是演员酗酒所致）；Monica突然变漂亮了，原因是她整容了（最近有点整过头了）；Rachel的颜值在高起点上一直稳步上升，绝对是时尚担当；Joey越演越出彩，渐渐露出了“续集担当”的意思了；Phoebe我一直摸不着她的门道儿，怪咖芭比和神经大婶儿二合一；Ross各方面都最稳定，但该不靠谱的继续不靠谱。总之一切都好，笑声不断，除了双子塔在每一集都出现，让人感到刺痛。但是在那么多次重看之后，我第一次注意到了一句话。那是第六季第四集，Ross去纽约大学讲古生物学。为了搏出位，他强装英国腔，结果Monica和Rachel突然出现在教室惊讶地盯着他，让他完全泄了气。他只好跟学生老实说：“我不是英国人，我来自长岛。” 囧爆了Ross坦言自己来自长岛我在这一句后面按了暂停。在《老友记》播出这么多年后，我才突然意识到，原来，这个曼哈顿年轻人逗比故事的欢乐根基，在于长岛啊。很多人对《老友记》的设定表示不明白。为什么这些年轻人随便当个厨师或者端端咖啡什么的，就住得起曼哈顿的公寓，负担得了那么贵的生活？他们说这是胡编，是不真实的。是的，大城市的友谊和爱情，是昂贵的。尤其是在曼哈顿。不过，其实所有的解释都在Ross的这句话里：“我来自长岛。”长岛是美国有名的富人区，是曼哈顿的后花园，又以学区好而著名。 土豪长岛夜景长岛的公立学校，比如高中，能在就近入学而非考试选拔的情况下，在全美国近三万学校里排到前30。而排在它前面的，很多都是经过考试选拔的重点学校。所以Ross受过良好的教育就比较说得过去，他也自然而然为六个人确立了招人待见的“好孩子”身份。因为他是长岛来的嘛。其实长岛也不是人人都那么有钱，可Ross的爸爸Jack Geller开保时捷，他妈则一瞅就知道是阔太太。所以他家在长岛至少是排中上的。同样因为“来自长岛”，Ross的妹妹Monica才很自然地有两套曼哈顿公寓可以继承。这可是很值钱的。至于为什么妹妹Monica有公寓继承而哥哥Ross却没有，原因可能有两点。第一，Ross也许继承了别的，编剧不是一定得交代得那么清楚；第二，多半只有富人家才能做到这种程度的不重男轻女，把财产都留给女性继承人。如果不是交待了来自长岛的家庭背景，一般人家做这种事就不那么能被轻易理解了。然后再说Rachel。Rachel和Monica是初、高中同学，那她家基本也就是住在长岛。 Rachel和Monica的高中时代上面说过，长岛和曼哈顿、布鲁克林、皇后区都不一样。公立学校是就近入学制，是不用经过考试选拔的。即使上的是私立，但因为Rachel老是腻在Monica家里，两人能从小玩到大，那两家一定离得不远。Rachel的爸爸是个医生，这一点第一集就交待了。所以Rachel家里有钱，她那时每天的乐子就是跟一群同样有钱的女友们到处购物，是个花钱小专家。本来Rachel是要嫁给牙医当阔太太的，谁知她逃婚端咖啡去了。第一集的故事就是这么展开的。端了一个月的咖啡，第一个月的工资单下来，Rachel非常非常失望，她不能相信自己一个月忙活地这么累，居然挣的这么少。 第一季时逃婚的Rachel，好美啊在这里，编剧其实写了一个真实的曼哈顿。曼哈顿就是这样的地方，生活成本很高，可是如果你不是干投行什么的，收入也高不到哪儿去。可是，Rachel还是在大家的怂恿下，把她爹给她的信用卡剪了。除了自食其力的勇气，悔婚、剪掉信用卡的更深层原因，仍然是因为Ross那一句“我来自长岛”。一般来讲，富人家的小孩儿更不怕穷，更有勇气从事自己喜欢干的工作，为爱情冒险。他们没有经历过匮乏，他们有人接盘，他们心里更有底。 痛下决心，准备剪卡的Rachel对Rachel来讲，虽然要交Monica的房租，可是Monica毕竟是她的中学同学，是她最要好的朋友。大家知根知底，Rechel就是欠房租，对Monica来说，她还能跑到哪里去？Rachel生病了有医保，她爸就是医生。就算实在付不起房租了，要钱也只是张口的事（她爸还因为她的附属卡突然不消费了吓得上门来找她，以为她出了什么事，惊问“你咋不刷我的卡了？！”）至于Chandler，他是Ross的大学室友，虽然没交待到底是啥大学，但应该也还不错。因为Ross是学霸，是Geller家的骄傲（Monica从小就是肥妹+丑小鸭，可是，她继承了天价公寓！兄妹俩扯平了），而Chandler是Ross的朋友，所以他还能差到哪里去。 Chandler和Ross的…呃…大学时代Chandler虽然不一定来自长岛，但家里也很有钱。还记得他父母在感恩节当天，在他吃撑了火鸡的时候告诉他离婚的消息。而旁边的佣人则不识趣地盯着他问道：“少爷你要不要再吃点儿火鸡啊？” 听闻父母离婚及其诡异理由的小Chandler ，眼神到位这让Chandler恨透了感恩节和火鸡。但也说明Chandler家是有佣人的。Chandler的爸爸据说是个同性恋，开了个同性恋俱乐部。他妈妈是个美女作家，看上去经济状况非常不错。所以美国的有钱人，除了长岛的，还有很多很多。可Chandler虽然家境好，却有不小的原生家庭创伤，跟家里人很不亲近。也正因为如此，他把朋友当家人，每天跟他们混得不亦乐乎。也正因为Ross、Monica、Rachel、Chandler四个人都是有钱人家的小孩儿，这个六人组合才比较稳固——相似的生活背景，父辈强有力的经济基础为他们做了担保。一句“我来自长岛”，反映了编剧的世故。美国是个最不浪漫的国家，钱在这个国家的地位十分清晰，没有钱虽不是寸步难行，但标准是在那明摆着的。一切都必须以足够的钱为基础，包括美德。所以，《老友记》的编剧非但没有胡扯友谊，瞎编浪漫，反倒是非常现实的。编剧甚至让Ross亲口说了两遍“我来自长岛”。第一次是在上面说的第六季第四集，在Ross给纽约大学的学生讲课的时候。第二次则是在第六季第八集。在那一集里，Ross把牙齿狠狠漂白去泡妞儿，但因为牙太白，他捂着嘴不好意思说话，最后不得已，扭扭捏捏地跟人自我介绍说“我是在长岛长大的”。 Ross不露齿地第二次说出自己来自长岛可见，这句“我来自长岛”的台词，对剧情很重要。但编剧没有让年轻人的友谊变成有钱人的同盟，那样太没意思也太不政治正确了。真正的友谊，只要有稳定的经济基础，就是完全可以产生的，也是完全可以不分阶级、阶层、知识、穷富......whatever的。（注意，这六个人里有四个是富人的小孩儿，注意这个微妙的比例。）所以，就出现了Joey和Phoebe。 Joey和Phoebe不像其他四人一样手头宽裕，剧中二人也对此心知肚明Joey是意裔。他的父母一看就没受过良好教育，家里孩子又多，有一大堆姐姐。Joey穷，简直是纽约最吃了上顿没下顿的。他还是个九流演员。而他“艺术家”的身份绝对是现实生活的灾难和制造客观欢乐的小源泉。Joey出现在六个人里带点偶然性。Chandler在挑选室友的时候面试过他，但并没有选他。但是另一个被选中的人阴差阳错地没来，Joey才当上了Chandler的室友。 Joey在剧中是一个有点脱线又重情重义的…逗比Joey之所以能持久地和其他人一起生活下去，付房租，分摊披萨、生日party和水电电话费，主要是因为Chandler是做财务的。他不仅挣钱多，还老明里暗里地贴补Joey。看来和谐社会的出现，有时还是需要富人的小孩儿多做一点儿努力。Phoebe也是个都市穷孩子。她从小被生父生母抛弃，养母又在她小时候自杀，不得不在小小年纪时就自食其力。她不仅是六个人里最知道人生疾苦的，也是六个人中最堪称品德高尚的。她从不贪财，还主动借钱给富人家的孩子Monica创业。 Phoebe人生中的第一辆自行车，Ross送的编剧真是太政治正确了，懂事。Phoebe的祖母去世后，编剧体贴地把房子留给了她。要不然的话，Phoebe做体力劳动打工的收入，即使能够应付曼哈顿的生活费用，也是没法长期维持她和其他五个人的交往的。还是那句话，大城市的友谊和爱情都是昂贵的。Monica继承的两间贵价公寓，给了六个人的曼哈顿友谊一个稳固的屋顶；Ross和Rachel的爱情主线，串起了整个十季的剧集。这么一想，恰恰是Ross、Rachel、Monica这三个长岛孩子的背景，给了这段曼哈顿友谊一个支撑。这支撑就是他们背后的钱，和他们所受的教育。为什么来自长岛的孩子在编剧看来就更逗比，更好玩，拿着开涮更顺手呢？也许因为长岛的孩子大部分是散漫的。长岛的公立学校都离家近，孩子们每天睡到日上三竿。课业压力、通勤压力跟纽约市的孩子比都要小很多。私立学校课业即使重一些，但还是相对舒服的。长岛就是个风景如画的散漫之地，是孩子的天堂。此外，也许在编剧看来，只有长岛，才能支撑起六个人在残酷的“大苹果”里建立的温馨友谊，才不至于让他们友谊的小船在金钱世界中说翻就翻。（我感觉我像个搞长岛房地产的。）最近我看到一篇文章，标题是“《老友记》创造了脑残一代”。我心想“脑残”已经变成夸人的新词儿了吗？因为在我看来，《老友记》显然改变了不止一代的中国人。“多嘲笑自己，少折磨别人”，“与其阶级斗争，不如正常放松”，你看这是多好的教育啊。“放过自己和这个世界吧！”。我觉得像《老友记》那样每天人畜无害傻呵呵，比天天没事儿老想着怎么祸害社会，要好很多。
            )

            data class Author(
                    @SerializedName("user_id")
                    val userId: String, // 0
                    @SerializedName("user_name")
                    val userName: String, // 小豆
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

        data class Weather(
                @SerializedName("city_name")
                val cityName: String, // 地球
                val date: String, // 2017-07-05
                val temperature: String, // -275
                val humidity: String, // 120
                val climate: String, // 对流层
                @SerializedName("wind_direction")
                val windDirection: String, // 一阵妖风
                val hurricane: String, // 36级
                val icons: Icons
        ) {
            data class Icons(
                    val day: String, // weather_icon_unknown
                    val night: String // weather_icon_unknown_night
            )
        }
    }

    class Deserializer : ResponseDeserializable<OneDetail> {
        override fun deserialize(content: String): OneDetail? {
            return Gson().fromJson(content,OneDetail::class.java)
        }
    }
}