package com.ksballetba.one.ui.fragment


import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.widget.NestedScrollView
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir

import com.ksballetba.one.R
import com.ksballetba.one.entity.MovieDetail
import com.ksballetba.one.entity.OneDetail
import com.ksballetba.one.tools.network.NetworkManager
import com.ksballetba.one.ui.activity.EssayDetailActivity
import com.ksballetba.one.ui.activity.MovieDetailActivity
import com.ksballetba.one.ui.activity.MusicDetailActivity
import com.ksballetba.one.ui.adapter.OneAdapter
import com.ksballetba.one.ui.callback.TimeoutCallback
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.functions.Action
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_one.*
import kotlinx.android.synthetic.main.layout_one_item.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.uiThread
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class OneFragment : Fragment() {

    var mOneList = mutableListOf<OneDetail>()

    lateinit var mOneAdapter:OneAdapter

    lateinit var oneSwipe:SmartRefreshLayout


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        oneSwipe = view.findViewById(R.id.one_swipe)
        init()
    }

    private fun init(){
        val myLayoutManager = LinearLayoutManager(activity)
        myLayoutManager.orientation = LinearLayout.HORIZONTAL
        one_recyclerview.layoutManager = myLayoutManager
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(one_recyclerview)
        mOneAdapter = OneAdapter(mOneList){idx, viewName ->
            when(viewName){
                "storyClick"->{
                    val intent = Intent(activity,EssayDetailActivity::class.java)
                    intent.putExtra("essay_id",mOneList[idx].data.contentList[1].itemId)
                    intent.putExtra("serial_id","null")
                    intent.putExtra("question_id","null")
                    startActivity(intent)
                }
                "essayClick"->{
                    val intent = Intent(activity,EssayDetailActivity::class.java)
                    for(i in 2 until mOneList[idx].data.contentList.size){
                        if(mOneList[idx].data.contentList[i].category.toInt()==1){
                            intent.putExtra("essay_id",mOneList[idx].data.contentList[i].itemId)
                            intent.putExtra("serial_id","null")
                            intent.putExtra("question_id","null")
                            startActivity(intent)
                        }
                    }
                }
                "serialClick"->{
                    val intent = Intent(activity,EssayDetailActivity::class.java)
                    for(i in mOneList[idx].data.contentList){
                        if(i.category.toInt()==2){
                            intent.putExtra("essay_id","null")
                            intent.putExtra("serial_id",i.itemId)
                            intent.putExtra("question_id","null")
                            startActivity(intent)
                        }
                    }
                }
                "questionClick"->{
                    val intent = Intent(activity,EssayDetailActivity::class.java)
                    for(i in mOneList[idx].data.contentList){
                        if(i.category.toInt()==3){
                            intent.putExtra("essay_id","null")
                            intent.putExtra("serial_id","null")
                            intent.putExtra("question_id",i.itemId)
                            startActivity(intent)
                        }
                    }
                }
                "musicClick"->{
                    val intent = Intent(activity,MusicDetailActivity::class.java)
                    for(i in mOneList[idx].data.contentList){
                        if(i.category.toInt()==4){
                            intent.putExtra("music_id",i.itemId)
                            startActivity(intent)
                        }
                    }
                }
                "movieClick"->{
                    val intent = Intent(activity,MovieDetailActivity::class.java)
                    for(i in mOneList[idx].data.contentList){
                        if(i.category.toInt()==5){
                            intent.putExtra("movie_id",i.itemId)
                            startActivity(intent)
                        }
                    }
                }
            }
        }
        one_recyclerview.itemAnimator = DefaultItemAnimator()
        one_recyclerview.adapter = mOneAdapter
        oneSwipe.setOnRefreshListener {
            if(mOneList.size==0){
                refreshList()
            }else{
                oneSwipe.finishRefresh(2000)
            }
        }
        if(mOneList.size == 0){
            refreshList()
        }
    }

    private fun refreshList(){
        oneSwipe.autoRefresh()
        NetworkManager.getOneIdListObservable().concatMap{
             NetworkManager.getOneDetailObservable(it)
        }.toList()!!.subscribeOn(Schedulers.io()).subscribe({list->
            mOneList = list
            mOneAdapter.update(mOneList)
            oneSwipe.finishRefresh()
        })
    }

}
