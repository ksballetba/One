package com.ksballetba.one.tools.network

import android.util.Log
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Method
import com.ksballetba.one.entity.OneDetail
import com.ksballetba.one.entity.OneidList
import io.reactivex.Observable

class NetworkManager {

    companion object {
        fun host():String{
            return "http://v3.wufazhuce.com:8000/api"
        }
        val oneIdlistUrl = host()+"/onelist/idlist"
        val oneDetailUrl = host()+"/onelist" //id/0
        val readingIdlistUrl = host()+"/channel/reading/more/0" //id
        val essayDetailUrl = host()+"/essay"//id
        val serialDetailUrl = host()+"/serialcontent"//id
        val questionDetailUrl = host()+"/question"//id
        val musicIdlistUrl = host()+"/channel/music/more/0"
        val movieIdlistUrl = host()+"/channel/movie/more/0"
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

    }
}