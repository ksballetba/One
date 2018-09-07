package com.ksballetba.one.ui.activity

import android.Manifest
import android.animation.LayoutTransition
import android.annotation.SuppressLint
import android.content.Intent
import android.location.LocationListener
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
import com.ksballetba.one.ui.fragment.*import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.ksballetba.one.tools.network.NetworkManager
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.toast
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions

import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions

class MainActivity : AppCompatActivity() {

    companion object {
        const val PERMISSISON_CODE = 100
    }

    val fragmentList = ArrayList<Fragment>()
    lateinit var oneFragment:OneFragment
    lateinit var readFragment: ReadFragment
    lateinit var musicFragment:MusicFragment
    lateinit var movieFragment:MovieFragment
    lateinit var oneLocation:TextView
    lateinit var mLocationClient:AMapLocationClient
    lateinit var mLocationOption:AMapLocationClientOption


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent.flags and Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT != 0) {
            finish()
            return
        }
        setContentView(R.layout.activity_main)
        oneLocation = findViewById(R.id.one_location)
        mLocationClient = AMapLocationClient(applicationContext)
        val mLocationListener = AMapLocationListener{aMapLocation ->
            if(aMapLocation!=null){
                if(aMapLocation.errorCode ==0){
                    NetworkManager.getWeatherNow(aMapLocation.city.toString().substring(0,2)){weatherNow, error ->
                        if(error==null){
                            oneLocation.text = "${aMapLocation.city.toString().substring(0,2)}·${weatherNow!!.results[0].now?.text} ${weatherNow!!.results[0].now?.temperature}℃"
                        }
                    }
                } else{
                    Log.d("debug",aMapLocation.errorInfo)
                }
                mLocationClient.stopLocation()
            }
        }
        mLocationClient.setLocationListener(mLocationListener)
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
        requestPermissions()
        main_viewpager.offscreenPageLimit = 4
        main_viewpager.adapter = KotlinPagerAdapter(fragmentList,supportFragmentManager)
        main_bottomnav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun initLocation(){
        mLocationOption = AMapLocationClientOption()
        mLocationOption.locationPurpose = AMapLocationClientOption.AMapLocationPurpose.SignIn
        mLocationOption.locationMode=AMapLocationClientOption.AMapLocationMode.Battery_Saving
        mLocationOption.isOnceLocation = true
        mLocationOption.isOnceLocationLatest = true
        mLocationOption.isNeedAddress = true
        mLocationOption.httpTimeOut = 20000
        if (null!=mLocationClient){
            mLocationClient.setLocationOption(mLocationOption)
            mLocationClient.stopLocation()
            mLocationClient.startLocation()
        }
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

    @AfterPermissionGranted(PERMISSISON_CODE)
    private fun requestPermissions(){
        if (EasyPermissions.hasPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_PHONE_STATE,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION)){
            initLocation()
        } else{
            EasyPermissions.requestPermissions(this,"需要获取权限" ,
                    PERMISSISON_CODE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_PHONE_STATE,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
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



