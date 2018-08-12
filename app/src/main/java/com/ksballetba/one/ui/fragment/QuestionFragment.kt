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
import com.ksballetba.one.entity.QuestionListItem
import com.ksballetba.one.tools.network.NetworkManager
import com.ksballetba.one.ui.adapter.QuestionAdapter
import kotlinx.android.synthetic.main.fragment_question.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.onUiThread
import org.jetbrains.anko.support.v4.uiThread

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class QuestionFragment : Fragment() {

    var mQuestionItemList=ArrayList<QuestionListItem>()
    lateinit var mQuestionAdapter:QuestionAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        val myLayoutManager = LinearLayoutManager(activity)
        myLayoutManager.orientation = LinearLayout.VERTICAL
        question_recyclerview.layoutManager = myLayoutManager
        mQuestionAdapter = QuestionAdapter(mQuestionItemList){

        }
        question_recyclerview.itemAnimator = DefaultItemAnimator()
        question_recyclerview.adapter = mQuestionAdapter
        question_swipe.setOnRefreshListener {
            refreshList()
        }
        if(mQuestionItemList.size == 0){
            refreshList()
        }
    }

    private fun refreshList(){
        question_swipe.isRefreshing = true
        doAsync {
            NetworkManager.getReadList { readListStr, error ->
                if(error==null){
                    onUiThread {
                        mQuestionAdapter.update(NetworkManager.getQuestionList(readListStr))
                        question_swipe.isRefreshing = false
                    }
                } else{
                    Log.d("debug","boom")
                }

            }
        }
    }


}
