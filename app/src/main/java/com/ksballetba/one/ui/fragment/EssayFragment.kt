package com.ksballetba.one.ui.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
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
import com.ksballetba.one.ui.activity.EssayDetailActivity
import com.ksballetba.one.ui.adapter.EssayAdapter
import com.scwang.smartrefresh.layout.SmartRefreshLayout
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

    var mEssayItemList= mutableListOf<EssayListItem>()

    lateinit var mEssayAdapter:EssayAdapter

    lateinit var essaySwipe:SmartRefreshLayout

    companion object {
        var currentPage = 0
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_essay, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        essaySwipe = view.findViewById(R.id.essay_swipe)
        initRefreshLayout()
        init()
    }

    private fun init(){
        val myLayoutManager = LinearLayoutManager(activity)
        myLayoutManager.orientation = LinearLayout.VERTICAL
        essay_recyclerview.layoutManager = myLayoutManager
        mEssayAdapter = EssayAdapter(mEssayItemList){idx->
            val intent = Intent(activity,EssayDetailActivity::class.java)
            intent.putExtra("essay_id",mEssayItemList[idx].contentId)
            intent.putExtra("serial_id","null")
            intent.putExtra("question_id","null")
            startActivity(intent)
        }
        essay_recyclerview.itemAnimator = DefaultItemAnimator()
        essay_recyclerview.adapter = mEssayAdapter
        if(mEssayItemList.size == 0){
            refreshList()
        }

    }

    private fun initRefreshLayout(){
        essaySwipe.setEnableRefresh(true)
        essaySwipe.setEnableLoadMore(true)
        essaySwipe.setEnableAutoLoadMore(true)
        essaySwipe.setEnableOverScrollBounce(true)//是否启用越界回弹
        essaySwipe.setEnableScrollContentWhenLoaded(true)//是否在加载完成时滚动列表显示新的内容
        essaySwipe.setEnableHeaderTranslationContent(true)//是否下拉Header的时候向下平移列表或者内容
        essaySwipe.setEnableFooterTranslationContent(true)//是否上拉Footer的时候向上平移列表或者内容
        essaySwipe.setEnableLoadMoreWhenContentNotFull(true)
        essaySwipe.setOnRefreshListener {
            if(mEssayItemList.size==0){
                refreshList()
            } else{
                essaySwipe.finishRefresh(2000)
            }
        }
        essaySwipe.setOnLoadMoreListener {
            loadMore()
        }
    }


    private fun refreshList(){
        essaySwipe.autoRefresh()
      doAsync {
          NetworkManager.getReadList(currentPage.toString()) { readListStr, error->
              if(error==null){
                  uiThread {
                      mEssayItemList = NetworkManager.getEssayList(readListStr)
                      mEssayAdapter.update(mEssayItemList)
                      essaySwipe.finishRefresh()
                      if(mEssayItemList.size<9){
                          loadMore()
                      }
                  }
              }
          }
      }
    }

    private fun loadMore(){
        doAsync {
            NetworkManager.getReadList((++currentPage).toString()) { readListStr, error->
                if(error==null){
                    uiThread {
                        mEssayItemList.addAll(NetworkManager.getEssayList(readListStr))
                        mEssayAdapter.add(NetworkManager.getEssayList(readListStr))
                        essaySwipe.finishLoadMore()
                    }
                }
            }
        }
    }
}
