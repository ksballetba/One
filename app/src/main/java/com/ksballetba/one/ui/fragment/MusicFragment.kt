package com.ksballetba.one.ui.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ksballetba.one.R
import com.ksballetba.one.entity.MusicDetail
import com.ksballetba.one.tools.network.NetworkManager
import com.ksballetba.one.ui.activity.MusicDetailActivity
import com.ksballetba.one.ui.adapter.MusicAdapter
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_music.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class MusicFragment : Fragment() {

    var mMusicList  = mutableListOf<MusicDetail>()
    lateinit var mMusicAdapter:MusicAdapter

    lateinit var musicSwipe:SmartRefreshLayout


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_music, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        musicSwipe = view.findViewById(R.id.music_swipe)
        init()
        initRefreshLayout()
    }

    private fun init(){
        val myLayoutManager = GridLayoutManager(activity,2)
        myLayoutManager.orientation = GridLayoutManager.VERTICAL
        music_recyclerview.layoutManager = myLayoutManager
        mMusicAdapter = MusicAdapter(mMusicList){idx->
            val intent = Intent(activity,MusicDetailActivity::class.java)
            intent.putExtra("music_id",mMusicList[idx].data.id)
            startActivity(intent)
        }
        music_recyclerview.itemAnimator = DefaultItemAnimator()
        music_recyclerview.adapter = mMusicAdapter
        if(mMusicList.size == 0){
            refreshList()
        }
    }

    private fun initRefreshLayout(){
        musicSwipe.setEnableRefresh(true)
        musicSwipe.setEnableLoadMore(true)
        musicSwipe.setEnableAutoLoadMore(true)
        musicSwipe.setEnableOverScrollBounce(true)//是否启用越界回弹
        musicSwipe.setEnableScrollContentWhenLoaded(true)//是否在加载完成时滚动列表显示新的内容
        musicSwipe.setEnableHeaderTranslationContent(true)//是否下拉Header的时候向下平移列表或者内容
        musicSwipe.setEnableFooterTranslationContent(true)//是否上拉Footer的时候向上平移列表或者内容
        musicSwipe.setEnableLoadMoreWhenContentNotFull(true)
        musicSwipe.setOnRefreshListener {
            if(mMusicList.size==0){
                refreshList()
            } else{
                musicSwipe.finishRefresh(2000)
            }
        }
        musicSwipe.setOnLoadMoreListener {
            loadMore()
        }
    }

    private fun refreshList(){
        if(activity!=null){
            musicSwipe.autoRefresh()
            NetworkManager.getMusicIdListObservable("0").concatMap{

                NetworkManager.getMusicDetailObservable(it)
            }.toList()!!.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ list->
                        mMusicList = list
                        mMusicAdapter.update(mMusicList)
                        musicSwipe.finishRefresh()
            })
        }
    }

    private fun loadMore(){
        NetworkManager.getMusicIdListObservable((mMusicList[mMusicList.size-1].data.id).toString()).concatMap{
            NetworkManager.getMusicDetailObservable(it)
        }.toList()!!.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list->
                    mMusicList.addAll(list)
                    mMusicAdapter.add(list)
                    musicSwipe.finishLoadMore()
                })
    }

}
