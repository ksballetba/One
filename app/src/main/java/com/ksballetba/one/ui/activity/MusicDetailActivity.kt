package com.ksballetba.one.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.ksballetba.one.R
import com.ksballetba.one.tools.image.ImageManager
import com.ksballetba.one.tools.network.NetworkManager
import com.ksballetba.one.ui.widget.AppBarStateChangeListener
import kotlinx.android.synthetic.main.activity_music_detail.*
import kotlinx.android.synthetic.main.layout_bottom_card.*
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import kotlin.concurrent.thread

class MusicDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_detail)
        init()
    }
    
    private fun init(){
        setSupportActionBar(music_toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.title = ""
        val musicId = intent.getStringExtra("music_id")
        showMusicDetail(musicId)
        reading_type.text = "音乐"
        music_appbarlayout.addOnOffsetChangedListener(AppBarStateChangeListener{appBarLayout, state ->
            when(state){
                AppBarStateChangeListener.State.COLLAPSED->{
                    actionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)
                    music_info.visibility = View.GONE
                    reading_type.visibility = View.VISIBLE

                }
                AppBarStateChangeListener.State.EXPANDED->{
                    actionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
                    reading_type.visibility = View.GONE
                    music_info.visibility = View.VISIBLE
                }
                AppBarStateChangeListener.State.IDLE->{
                    music_info.visibility = View.VISIBLE
                }
            }
        })
    }
    
    private fun showMusicDetail(id:String){
        doAsync {

            NetworkManager.getMusicDetail(id){musicDetail, error ->

                thread {
                    val blur = ImageManager(this@MusicDetailActivity)
                    val bgBitmap = Glide.with(applicationContext).asBitmap().load(musicDetail!!.data.cover).submit(500,500).get()
                    uiThread {
                        Glide.with(this@MusicDetailActivity).load(musicDetail!!.data.cover).into(music_cover)
                        Glide.with(this@MusicDetailActivity).load(blur.gaussianBlur(25,bgBitmap)).into(music_coverbg)
                        music_info.text = "${musicDetail.data.title}·${musicDetail.data.album}|${musicDetail.data.author.userName}"
                        music_title.text = musicDetail.data.storyTitle
                        music_author.text = "文/${musicDetail.data.storyAuthor.userName}"
                        music_content.settings.defaultTextEncodingName ="utf-8"
                        music_content.backgroundColor = 0
                        music_content.background.alpha = 0
                        music_content.loadDataWithBaseURL(null,musicDetail.data.story,"text/html", "UTF-8", null)
                        essay_favcount.text = musicDetail.data.praisenum.toString()
                        essay_commentcount.text = musicDetail.data.commentnum.toString()
                    }
                }
//


            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home->{
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
