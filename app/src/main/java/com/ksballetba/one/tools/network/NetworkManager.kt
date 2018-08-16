package com.ksballetba.one.tools.network

import android.util.Log
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import com.ksballetba.one.entity.*
import io.reactivex.Observable
import org.json.JSONObject
import org.jsoup.safety.Whitelist
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.util.Collections.replaceAll



class NetworkManager {

    companion object {
        fun host():String{
            return "http://v3.wufazhuce.com:8000/api"
        }
        val oneIdlistUrl = host()+"/onelist/idlist"
        val oneDetailUrl = host()+"/onelist" //id/0
        val readinglistUrl = host()+"/reading/index" //page
        val essayDetailUrl = host()+"/essay"//id
        val serialDetailUrl = host()+"/serialcontent"//id
        val questionDetailUrl = host()+"/question"//id
        val musicIdlistUrl = host()+"/music/idlist" //page
        val movieItemlistUrl = host()+"/movie/list" //page
        val musicDetailUrl = host()+"/music/detail" //id
        val movieDetailUrl = host()+"/movie" //id/stroy/1/0
        val moviePosterUrl = host()+"/movie/detail" //id
        val keySearchUrl = host()+"/search" //channel/keyword
        /*channel包括6种类型：hp（图文） reading（阅读） music（音乐）
        movie（影视） radio（电台） author（作者/音乐人）
         */
        val timeSearchUrl = host() //type/bymonth/month
        /*
        {type} : 只能是以下五种类型：hp : 图文  reading : 阅读 serialcontent : 连载 question : 问答 music : 音乐
        {month} : 月份格式如下： 2017-07 2017-07-01 2017-07-01 000:00:00
         */

        fun getOneidListAll(complete: (res: List<String>?, error: FuelError?) ->Unit){
            FuelManager.instance.request(Method.GET, oneIdlistUrl)
                    .responseObject(OneidList.Deserializer()){request, response, result ->
                        when (result) {
                            is com.github.kittinunf.result.Result.Failure -> {
                                complete(null, result.error)
                            }
                            is com.github.kittinunf.result.Result.Success -> {
                                val (res, err) = result
                                val list = res?.data?.toList()
                                complete(list,null)
                            }
                        }
                    }
        }

        fun getMusicIdList(page:String,complete: (res: List<String>?, error: FuelError?) -> Unit){
            FuelManager.instance.request(Method.GET, musicIdlistUrl+"/$page")
                    .responseObject(OneidList.Deserializer()){request, response, result ->
                        when (result) {
                            is com.github.kittinunf.result.Result.Failure -> {
                                complete(null, result.error)
                            }
                            is com.github.kittinunf.result.Result.Success -> {
                                val (res, err) = result
                                val list = res?.data?.toList()
                                complete(list,null)
                            }
                        }
                    }
        }

        fun getMovieIdList(page:String,complete: (res: List<String>?, error: FuelError?) -> Unit){
            FuelManager.instance.request(Method.GET, movieItemlistUrl+"/$page")
                    .responseObject(MovieListItem.Deserializer()){request, response, result ->
                        when(result){
                            is Result.Failure->{
                                complete(null,result.error)
                            }
                            is Result.Success->{
                                val(res,err) = result
                                val list = mutableListOf<String>()
                                for (i in 0 until res?.data?.size!!){
                                    list.add(res.data[i].id)
                                }
                                complete(list,null)
                            }
                        }
                    }
        }

        fun getMoviePosterDetail(id:String,complete: (moviePosterDetail: MoviePosterDetail?, error: FuelError?) -> Unit){
            FuelManager.instance.request(Method.GET, moviePosterUrl+"/$id")
                    .responseObject(MoviePosterDetail.Deserializer()){request, response, result ->
                        when(result){
                            is Result.Failure->{
                                complete(null,result.error)
                            }
                            is Result.Success->{
                                val(data,err) = result
                                complete(data,null)
                            }
                        }
                    }
        }


        fun getMovieDetail(id:String,complete:(movieDetail: MovieDetail?, error: FuelError?) -> Unit){
            FuelManager.instance.request(Method.GET, movieDetailUrl+"/$id/story/1/0")
                    .responseObject(MovieDetail.Deserializer()){request, response, result ->
                        when(result){
                            is Result.Failure->{
                                complete(null,result.error)
                            }
                            is Result.Success->{
                                val(data,err) = result
                                complete(data,null)
                            }
                        }
                    }
        }


        fun getMusicDetail(id:String,complete: (musicDetail: MusicDetail?, error: FuelError?) -> Unit){
            FuelManager.instance.request(Method.GET, musicDetailUrl+"/$id")
                    .responseObject(MusicDetail.Deserializer()){request, response, result ->
                        when(result){
                            is Result.Failure->{
                                complete(null,result.error)
                            }
                            is Result.Success->{
                                val(data,err) = result
                                complete(data!!,null)
                            }
                        }
                    }
        }


        fun getOneDetail(id:String,complete: (oneDetail: OneDetail?, error: FuelError?) -> Unit){
            FuelManager.instance.request(Method.GET, oneDetailUrl+"/$id/0")
                    .responseObject(OneDetail.Deserializer()){request, response, result ->
                        when (result) {
                            is com.github.kittinunf.result.Result.Failure -> {
                                complete(null, result.error)
                            }
                            is com.github.kittinunf.result.Result.Success -> {
                                val (data, err) = result
                                complete(data!!, null)
                            }
                        }
                    }
        }

        fun getEssayDetail(id:String,complete: (essayDetail: EssayDetail?, error: FuelError?) -> Unit){
            FuelManager.instance.request(Method.GET, essayDetailUrl+"/$id")
                    .responseObject(EssayDetail.Deserializer()){request, response, result ->
                        when(result){
                            is Result.Failure->{
                                complete(null,result.error)
                            }
                            is Result.Success->{
                                val(data,err) = result
                                complete(data!!,null)
                            }
                        }
                    }
        }

        fun getSerialDetail(id:String,complete: (serialDetail: SerialDetail?, error: FuelError?) -> Unit){
            FuelManager.instance.request(Method.GET, serialDetailUrl+"/$id")
                    .responseObject(SerialDetail.Deserializer()){request, response, result ->
                        when(result){
                            is Result.Failure->{
                                complete(null,result.error)
                            }
                            is Result.Success->{
                                val(data,err) = result
                                complete(data!!,null)
                            }
                        }
                    }
        }

        fun getQuestionDetail(id:String,complete: (question: QuestionDetail?, error: FuelError?) -> Unit){
            FuelManager.instance.request(Method.GET, questionDetailUrl+"/$id")
                    .responseObject(QuestionDetail.Deserializer()){request, response, result ->
                        when(result){
                            is Result.Failure->{
                                complete(null,result.error)
                            }
                            is Result.Success->{
                                val(data,err) = result
                                complete(data!!,null)
                            }
                        }
                    }
        }


        fun getReadList(page:String,complete: (readListStr: String?, error: FuelError?) -> Unit){
            FuelManager.instance.request(Method.GET, readinglistUrl+"/$page")
                    .responseString{request, response, result ->
                        when(result){
                            is Result.Failure->{
                                complete(null,result.error)
                            }
                            is Result.Success->{
                                val(data,err) = result
                                complete(data,null)
                            }
                        }
                    }
        }



        fun getEssayList(response:String?):MutableList<EssayListItem>{
            val all = JSONObject(response)
            val dataContent = all.getJSONArray("data")
            val resList = mutableListOf<EssayListItem>()
            for(i in 0 until dataContent.length()){
                val dayList = dataContent.getJSONObject(i).getJSONArray("items")
                for(i in 0 until dayList.length()){
                    if(dayList.getJSONObject(i).getInt("type")==1){
                        val resItemStr =  dayList.getJSONObject(i).getJSONObject("content")
                        val resItem = Gson().fromJson(resItemStr.toString(),EssayListItem::class.java)
                        resList.add(resItem)
                    }
                }

            }
            return resList
        }

        fun getSerialList(response:String?):MutableList<SerialListItem>{
            val all = JSONObject(response)
            val dataContent = all.getJSONArray("data")
            val resList = mutableListOf<SerialListItem>()
            for(i in 0 until dataContent.length()){
                val dayList = dataContent.getJSONObject(i).getJSONArray("items")
                for(i in 0 until dayList.length()){
                    if(dayList.getJSONObject(i).getInt("type")==2){
                        val resItemStr =  dayList.getJSONObject(i).getJSONObject("content")
                        val resItem = Gson().fromJson(resItemStr.toString(),SerialListItem::class.java)
                        resList.add(resItem)
                    }
                }

            }
            return resList
        }

        fun getQuestionList(response:String?):MutableList<QuestionListItem>{
            val all = JSONObject(response)
            val dataContent = all.getJSONArray("data")
            val resList = mutableListOf<QuestionListItem>()
            for(i in 0 until dataContent.length()){
                val dayList = dataContent.getJSONObject(i).getJSONArray("items")
                for(i in 0 until dayList.length()){
                    if(dayList.getJSONObject(i).getInt("type")==3){
                        val resItemStr =  dayList.getJSONObject(i).getJSONObject("content")
                        val resItem = Gson().fromJson(resItemStr.toString(),QuestionListItem::class.java)
                        resList.add(resItem)
                    }
                }

            }
            return resList
        }



        fun getOneIdListObservable():Observable<String>{
           var observable = Observable.create<String>{
               NetworkManager.getOneidListAll{res, error ->
                   if(error==null){
                       for(i in 0 until res!!.size){
                           it.onNext(res[i])
                       }
                       it.onComplete()
                   } else{
                       Log.d("debug","boom")
                   }
               }
           }
            return observable
        }

        fun getMusicIdListObservable(page: String):Observable<String>{
            var observable = Observable.create<String>{
                NetworkManager.getMusicIdList(page){res, error ->
                    if(error==null){
                        for(i in 0 until res!!.size){
                            it.onNext(res[i])
                        }
                        it.onComplete()
                    } else{
                        Log.d("debug","boom")
                    }
                }
            }
            return observable
        }

        fun getMovieIdListObservable(page: String):Observable<String>{
            var observable = Observable.create<String>{
                NetworkManager.getMovieIdList(page) { res, error ->
                    if(error==null){
                        for(i in 0 until res!!.size){
                            it.onNext(res[i])
                        }
                        it.onComplete()
                    } else{
                        Log.d("debug","boom")
                    }
                }
            }
            return observable
        }




        fun getOneDetailObservable(oneId:String):Observable<OneDetail>{
            var observable = Observable.create<OneDetail> {
                NetworkManager.getOneDetail(oneId){oneDetail, error ->
                    if (error==null){
                        it.onNext(oneDetail!!)
                        it.onComplete()
                    } else{
                        Log.d("debug","boom")
                    }

                }
            }
            return observable
        }

        fun getMusicDetailObservable(musicId:String):Observable<MusicDetail>{
            var observable = Observable.create<MusicDetail> {
                NetworkManager.getMusicDetail(musicId){musicDetail, error ->
                    if(error==null){
                        it.onNext(musicDetail!!)
                        it.onComplete()
                    } else{
                        Log.d("debug","boom")
                    }
                }
            }
            return observable
        }

        fun getMoviePosterDetailObservable(movieId:String):Observable<MoviePosterDetail>{
            var observable = Observable.create<MoviePosterDetail> {
                NetworkManager.getMoviePosterDetail(movieId){moviePosterDetail, error ->
                    if(error==null){
                        it.onNext(moviePosterDetail!!)
                        it.onComplete()
                    } else{
                        Log.d("debug","boom")
                    }
                }
            }
            return observable
        }

        fun getMovieDetailObservable(movieId:String):Observable<MovieDetail>{
            var observable = Observable.create<MovieDetail> {
                NetworkManager.getMovieDetail(movieId){movieDetail, error ->
                    if(error==null){
                        it.onNext(movieDetail!!)
                        it.onComplete()
                    } else{
                        Log.d("debug","boom")
                    }
                }
            }
            return observable
        }

    }
}