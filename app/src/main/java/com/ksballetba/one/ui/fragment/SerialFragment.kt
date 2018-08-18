package com.ksballetba.one.ui.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.ksballetba.one.R
import com.ksballetba.one.entity.SerialListItem
import com.ksballetba.one.tools.network.NetworkManager
import com.ksballetba.one.ui.activity.EssayDetailActivity
import com.ksballetba.one.ui.adapter.SerialAdapter
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import kotlinx.android.synthetic.main.fragment_serial.*
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
class SerialFragment : Fragment() {

    var mSerialItemList= mutableListOf<SerialListItem>()

    lateinit var mSerialAdapter:SerialAdapter

    lateinit var serialSwipe:SmartRefreshLayout

    companion object {
        var currentPage = 0
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_serial, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        serialSwipe = view.findViewById(R.id.serial_swipe)
        initRefreshLayout()
        init()
    }


    private fun init(){
        val myLayoutManager = LinearLayoutManager(activity)
        myLayoutManager.orientation = LinearLayout.VERTICAL
        serial_recyclerview.layoutManager = myLayoutManager
        mSerialAdapter = SerialAdapter(mSerialItemList){idx->
            val intent = Intent(activity, EssayDetailActivity::class.java)
            intent.putExtra("essay_id","null")
            intent.putExtra("serial_id",mSerialItemList[idx].id)
            intent.putExtra("question_id","null")
            startActivity(intent)
        }
        serial_recyclerview.itemAnimator = DefaultItemAnimator()
        serial_recyclerview.adapter = mSerialAdapter
        if(mSerialItemList.size == 0){
            refreshList()
        }

    }

    private fun initRefreshLayout(){
        serialSwipe.setEnableRefresh(true)
        serialSwipe.setEnableLoadMore(true)
        serialSwipe.setEnableAutoLoadMore(true)
        serialSwipe.setEnableOverScrollBounce(true)//是否启用越界回弹
        serialSwipe.setEnableScrollContentWhenLoaded(true)//是否在加载完成时滚动列表显示新的内容
        serialSwipe.setEnableHeaderTranslationContent(true)//是否下拉Header的时候向下平移列表或者内容
        serialSwipe.setEnableFooterTranslationContent(true)//是否上拉Footer的时候向上平移列表或者内容
        serialSwipe.setEnableLoadMoreWhenContentNotFull(true)
        serialSwipe.setOnRefreshListener {
            if(mSerialItemList.size==0){
                refreshList()
            } else{
                serialSwipe.finishRefresh(2000)
            }
        }
        serialSwipe.setOnLoadMoreListener {
            loadMore()
        }
    }

    private fun refreshList(){
        serialSwipe.autoRefresh()
        doAsync {
            NetworkManager.getReadList("0") { readListStr, error->
                if(error==null){
                    uiThread {
                        mSerialItemList = NetworkManager.getSerialList(readListStr)
                        mSerialAdapter.update(mSerialItemList)
                        if (mSerialItemList.size<9){
                            loadMore()
                        }
                        serialSwipe.finishRefresh()
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
                        mSerialItemList.addAll(NetworkManager.getSerialList(readListStr))
                        mSerialAdapter.add(NetworkManager.getSerialList(readListStr))
                        serialSwipe.finishLoadMore()
                    }
                }
            }
        }
    }

}
