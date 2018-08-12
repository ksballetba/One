package com.ksballetba.one.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v4.app.FragmentManager

import com.ksballetba.one.R
import kotlinx.android.synthetic.main.fragment_read.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ReadFragment : Fragment() {

    val fragmentList = ArrayList<Fragment>()
    lateinit var essayFragment: EssayFragment
    lateinit var serialFragment:SerialFragment
    lateinit var questionFragment: QuestionFragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_read, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        essayFragment = EssayFragment()
        serialFragment = SerialFragment()
        questionFragment = QuestionFragment()
        fragmentList.add(essayFragment)
        fragmentList.add(serialFragment)
        fragmentList.add(questionFragment)
        read_viewpager.adapter = KotlinPagerAdapter(fragmentList,childFragmentManager)
        read_viewpager.offscreenPageLimit = 3
        read_tablayout.setupWithViewPager(read_viewpager)
        read_tablayout.getTabAt(0)?.text = "阅读"
        read_tablayout.getTabAt(1)?.text = "连载"
        read_tablayout.getTabAt(2)?.text = "问答"
    }
}

class KotlinPagerAdapter(var mList : List<Fragment>, fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): android.support.v4.app.Fragment {
        return mList[position]
    }

    override fun getCount(): Int {
        return mList.size
    }

}
