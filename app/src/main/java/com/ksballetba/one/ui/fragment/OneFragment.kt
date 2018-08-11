package com.ksballetba.one.ui.fragment


import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.ksballetba.one.R
import com.ksballetba.one.entity.OneDetail
import com.ksballetba.one.tools.network.NetworkManager
import com.ksballetba.one.ui.adapter.OneAdapter
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

    var mOneList = ArrayList<OneDetail>()

    lateinit var mOneAdapter:OneAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        val myLayoutManager = LinearLayoutManager(activity)
        myLayoutManager.orientation = LinearLayout.HORIZONTAL
        one_recyclerview.layoutManager = myLayoutManager
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(one_recyclerview)
        mOneAdapter = OneAdapter(mOneList)
        one_recyclerview.itemAnimator = DefaultItemAnimator()
        one_recyclerview.adapter = mOneAdapter
        one_swipe.setOnRefreshListener {
            refreshList()
        }
        if(mOneList.size == 0){
            refreshList()
        }
    }

    private fun refreshList(){
        one_swipe.isRefreshing=true
        NetworkManager.getOneIdListObservable().concatMap{
             NetworkManager.getOneDetailObservable(it)
        }.toList()!!.subscribeOn(Schedulers.io()).subscribe({list->
            mOneAdapter.update(list)
            one_swipe.isRefreshing = false
        })
    }

}
