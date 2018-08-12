package com.ksballetba.one.ui.fragment


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
import com.ksballetba.one.ui.adapter.MusicAdapter
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_music.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class MusicFragment : Fragment() {

    var mMusicList  = ArrayList<MusicDetail>()
    lateinit var mMusicAdapter:MusicAdapter

    lateinit var musicSwipe:SwipeRefreshLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_music, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        musicSwipe = view.findViewById(R.id.music_swipe)
        init()
    }

    private fun init(){
        val myLayoutManager = GridLayoutManager(activity,2)
        myLayoutManager.orientation = GridLayoutManager.VERTICAL
        music_recyclerview.layoutManager = myLayoutManager
        mMusicAdapter = MusicAdapter(mMusicList){

        }
        music_recyclerview.itemAnimator = DefaultItemAnimator()
        music_recyclerview.adapter = mMusicAdapter
        musicSwipe.setOnRefreshListener {
            refreshList()
        }
        if(mMusicList.size == 0){
            refreshList()
        }
    }

    private fun refreshList(){
        musicSwipe.isRefreshing=true
        NetworkManager.getMusicIdListObservable().concatMap{
            NetworkManager.getMusicDetailObservable(it)
        }.toList()!!.subscribeOn(Schedulers.io()).subscribe({ list->
            mMusicAdapter.update(list)
            musicSwipe.isRefreshing = false
        })

    }
}
