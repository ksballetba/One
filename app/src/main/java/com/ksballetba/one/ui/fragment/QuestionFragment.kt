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
import com.ksballetba.one.entity.QuestionListItem
import com.ksballetba.one.tools.network.NetworkManager
import com.ksballetba.one.ui.activity.EssayDetailActivity
import com.ksballetba.one.ui.adapter.QuestionAdapter
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import kotlinx.android.synthetic.main.fragment_question.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.uiThread
import org.jetbrains.anko.uiThread

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class QuestionFragment : Fragment() {

    var mQuestionItemList= mutableListOf<QuestionListItem>()

    lateinit var mQuestionAdapter:QuestionAdapter

    lateinit var questionSwipe:SmartRefreshLayout

    companion object {
        var currentPage = 0
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        questionSwipe = view.findViewById(R.id.question_swipe)
        initRefreshLayout()
        init()
    }

    private fun init(){
        val myLayoutManager = LinearLayoutManager(activity)
        myLayoutManager.orientation = LinearLayout.VERTICAL
        question_recyclerview.layoutManager = myLayoutManager
        mQuestionAdapter = QuestionAdapter(mQuestionItemList){idx->
            val intent = Intent(activity, EssayDetailActivity::class.java)
            intent.putExtra("essay_id","null")
            intent.putExtra("serial_id","null")
            intent.putExtra("question_id",mQuestionItemList[idx].questionId)
            startActivity(intent)
        }
        question_recyclerview.itemAnimator = DefaultItemAnimator()
        question_recyclerview.adapter = mQuestionAdapter
        if(mQuestionItemList.size == 0){
            refreshList()
        }

    }

    private fun initRefreshLayout(){
        questionSwipe.setEnableRefresh(true)
        questionSwipe.setEnableLoadMore(true)
        questionSwipe.setEnableAutoLoadMore(true)
        questionSwipe.setEnableOverScrollBounce(true)//是否启用越界回弹
        questionSwipe.setEnableScrollContentWhenLoaded(true)//是否在加载完成时滚动列表显示新的内容
        questionSwipe.setEnableHeaderTranslationContent(true)//是否下拉Header的时候向下平移列表或者内容
        questionSwipe.setEnableFooterTranslationContent(true)//是否上拉Footer的时候向上平移列表或者内容
        questionSwipe.setEnableLoadMoreWhenContentNotFull(true)
        questionSwipe.setOnRefreshListener {
            if(mQuestionItemList.size==0){
                refreshList()
            } else{
                questionSwipe.finishRefresh(2000)
            }
        }
        questionSwipe.setOnLoadMoreListener {
            loadMore()
        }
    }

    private fun refreshList(){
        questionSwipe.autoRefresh()
        doAsync {
            NetworkManager.getReadList("0") { readListStr, error ->
                if(error==null){
                    uiThread {
                        mQuestionItemList = NetworkManager.getQuestionList(readListStr)
                        mQuestionAdapter.update(mQuestionItemList)
                        if (mQuestionItemList.size<9){
                            loadMore()
                        }
                        questionSwipe.finishRefresh()
                    }
                } else{
                    Log.d("debug","boom")
                }

            }
        }
    }

    private fun loadMore(){
        doAsync {
            NetworkManager.getReadList((++currentPage).toString()) { readListStr, error->
                if(error==null){
                    uiThread {
                        mQuestionItemList.addAll(NetworkManager.getQuestionList(readListStr))
                        mQuestionAdapter.add(NetworkManager.getQuestionList(readListStr))
                        questionSwipe.finishLoadMore()
                    }
                }
            }
        }
    }


}
