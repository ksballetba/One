package com.ksballetba.one.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.ksballetba.one.R
import com.ksballetba.one.R.id.serial_recyclerview
import com.ksballetba.one.entity.SerialListItem
import com.ksballetba.one.tools.network.NetworkManager
import com.ksballetba.one.ui.adapter.SerialAdapter
import kotlinx.android.synthetic.main.fragment_serial.*
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
class SerialFragment : Fragment() {

    var mSerialItemList=ArrayList<SerialListItem>()

    lateinit var mSerialAdapter:SerialAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_serial, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }


    private fun init(){
        val myLayoutManager = LinearLayoutManager(activity)
        myLayoutManager.orientation = LinearLayout.VERTICAL
        serial_recyclerview.layoutManager = myLayoutManager
        mSerialAdapter = SerialAdapter(mSerialItemList){

        }
        serial_recyclerview.itemAnimator = DefaultItemAnimator()
        serial_recyclerview.adapter = mSerialAdapter
        serial_swipe.setOnRefreshListener {
            refreshList()
        }
        if(mSerialItemList.size == 0){
            refreshList()
        }
    }

    private fun refreshList(){
        serial_swipe.isRefreshing = true
        doAsync {
            NetworkManager.getReadList { readListStr, error->
                if(error==null){
                    onUiThread {
                        mSerialAdapter.update(NetworkManager.getSerialList(readListStr))
                        serial_swipe.isRefreshing = false
                    }
                }
            }
        }
    }

}
