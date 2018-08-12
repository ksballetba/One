package com.ksballetba.one.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.ksballetba.one.R
import com.ksballetba.one.entity.EssayListItem
import com.ksballetba.one.tools.network.NetworkManager
import com.ksballetba.one.ui.adapter.EssayAdapter
import kotlinx.android.synthetic.main.fragment_essay.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class EssayFragment : Fragment() {

    var mEssayItemList=ArrayList<EssayListItem>()

    lateinit var mEssayAdapter:EssayAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_essay, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        val myLayoutManager = LinearLayoutManager(activity)
        myLayoutManager.orientation = LinearLayout.VERTICAL
        essay_recyclerview.layoutManager = myLayoutManager
        mEssayAdapter = EssayAdapter(mEssayItemList){

        }
        essay_recyclerview.itemAnimator = DefaultItemAnimator()
        essay_recyclerview.adapter = mEssayAdapter
        essay_swipe.setOnRefreshListener {
            refreshList()
        }
        if(mEssayItemList.size == 0){
            refreshList()
        }
    }

    private fun refreshList(){
        essay_swipe.isRefreshing = true
      doAsync {
          NetworkManager.getReadList { readListStr,error->
              if(error==null){
                  uiThread {
                      mEssayAdapter.update(NetworkManager.getEssayList(readListStr))
                      essay_swipe.isRefreshing = false
                  }
              }
          }
      }
    }
}
