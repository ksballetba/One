package com.ksballetba.one.ui.activity

import android.annotation.SuppressLint
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RestrictTo
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenu
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.widget.NestedScrollView
import android.util.Log
import com.ksballetba.one.R
import com.ksballetba.one.ui.fragment.EssayFragment
import com.ksballetba.one.ui.fragment.MovieFragment
import com.ksballetba.one.ui.fragment.MusicFragment
import com.ksballetba.one.ui.fragment.OneFragment
import kotlinx.android.synthetic.main.activity_main.*
import android.view.MenuItem


class MainActivity : AppCompatActivity() {

    val fragmentList = ArrayList<Fragment>()
    lateinit var oneFragment:OneFragment
    lateinit var essayFragment:EssayFragment
    lateinit var musicFragment:MusicFragment
    lateinit var movieFragment:MovieFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

    }

    private fun init(){
        setSupportActionBar(main_toolbar)
        BottomNavigationViewHelper.disableShiftMode(main_bottomnav)
        oneFragment = OneFragment()
        essayFragment = EssayFragment()
        musicFragment = MusicFragment()
        movieFragment = MovieFragment()
        fragmentList.add(oneFragment)
        fragmentList.add(essayFragment)
        fragmentList.add(musicFragment)
        fragmentList.add(movieFragment)
        main_viewpager.adapter = KotlinPagerAdapter(fragmentList,supportFragmentManager)
        main_bottomnav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }



    private val mOnNavigationItemSelectedListener = object : BottomNavigationView.OnNavigationItemSelectedListener {

        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.menu_one->{
                    item.isChecked = true
                    main_viewpager.currentItem = 0
                }
                R.id.menu_essay->{
                    item.isChecked = true
                    main_viewpager.currentItem = 1
                }
                R.id.menu_music->{
                    item.isChecked = true
                    main_viewpager.currentItem = 2
                }
                R.id.menu_movie->{
                    item.isChecked = true
                    main_viewpager.currentItem = 3
                }
            }
            return false
        }
    }

}


@SuppressLint("RestrictedApi")
class BottomNavigationViewHelper{
    companion object {
        fun disableShiftMode(navigationView:BottomNavigationView){
            try{
                val menuView = navigationView.getChildAt(0) as BottomNavigationMenuView
                val shiftingMode = menuView::class.java.getDeclaredField("mShiftingMode")
                shiftingMode.isAccessible = true
                shiftingMode.setBoolean(menuView,false)
                shiftingMode.isAccessible = false

                for (i in 0 until menuView.childCount){
                    val itemView = menuView.getChildAt(i) as BottomNavigationItemView
                    itemView.setShiftingMode(false)
                    itemView.setChecked(itemView.itemData.isChecked)
                }
            } catch (e:NoSuchFieldException){
                e.printStackTrace()
            }catch (e:IllegalAccessException){
                e.printStackTrace()
            }

        }
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

