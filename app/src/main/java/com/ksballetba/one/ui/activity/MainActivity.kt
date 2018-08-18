package com.ksballetba.one.ui.activity

import android.animation.LayoutTransition
import android.annotation.SuppressLint
import android.content.Intent
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
import android.support.v4.view.MenuItemCompat
import android.support.v4.widget.NestedScrollView
import android.util.Log
import android.view.Menu
import com.ksballetba.one.R
import kotlinx.android.synthetic.main.activity_main.*
import android.view.MenuItem
import android.view.View
import android.support.v7.widget.SearchView
import android.view.KeyEvent
import android.widget.ImageView
import com.ksballetba.one.ui.fragment.*
import android.widget.LinearLayout
import org.jetbrains.anko.imageResource


class MainActivity : AppCompatActivity() {

    val fragmentList = ArrayList<Fragment>()
    lateinit var oneFragment:OneFragment
    lateinit var readFragment: ReadFragment
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
        readFragment = ReadFragment()
        musicFragment = MusicFragment()
        movieFragment = MovieFragment()
        fragmentList.add(oneFragment)
        fragmentList.add(readFragment)
        fragmentList.add(musicFragment)
        fragmentList.add(movieFragment)
        main_viewpager.offscreenPageLimit = 4
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

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (event?.keyCode == KeyEvent.KEYCODE_BACK) {
            if (isTaskRoot) {
                moveTaskToBack(false)
                return true
            } else {
                return super.onKeyDown(keyCode, event)

            }
        }
        return super.onKeyDown(keyCode, event)
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

