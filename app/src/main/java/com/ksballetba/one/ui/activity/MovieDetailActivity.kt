package com.ksballetba.one.ui.activity

import android.content.Context
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.ksballetba.one.R
import com.ksballetba.one.tools.network.NetworkManager
import com.ksballetba.one.ui.widget.AppBarStateChangeListener
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.layout_bottom_card.*
import org.jetbrains.anko.backgroundColor

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        init()
    }
    
    private fun init(){
        setSupportActionBar(movie_toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.title = ""
        val movieId = intent.getStringExtra("movie_id")
        showMovieDetail(movieId)
        showBanner(movieId)
        reading_type.text = "影视"
        movie_appbarlayout.addOnOffsetChangedListener(AppBarStateChangeListener{appBarLayout, state ->
            when(state){
                AppBarStateChangeListener.State.COLLAPSED->{
                    actionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)
                    movie_name.visibility = View.GONE
                    reading_type.visibility = View.VISIBLE

                }
                AppBarStateChangeListener.State.EXPANDED->{
                    actionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
                    reading_type.visibility = View.GONE
                    movie_name.visibility = View.VISIBLE
                }
                AppBarStateChangeListener.State.IDLE->{
                    movie_name.visibility = View.VISIBLE
                }
            }
        })
    }


    
    private fun showMovieDetail(id:String){
        NetworkManager.getMovieDetail(id){movieDetail, error ->  
            movie_title.text = movieDetail!!.data.data[0].title
            movie_author.text = "文/${movieDetail.data.data[0].authorList[0].userName}"
            movie_content.settings.defaultTextEncodingName ="utf-8"
            movie_content.backgroundColor = 0
            movie_content.background.alpha = 0
            movie_content.loadDataWithBaseURL(null,movieDetail.data.data[0].content,"text/html", "UTF-8", null)
            essay_favcount.text = movieDetail.data.data[0].praisenum.toString()
        }
    }

    private fun showBanner(id:String){
        NetworkManager.getMoviePosterDetail(id){moviePosterDetail, error ->
            essay_commentcount.text = moviePosterDetail!!.data.commentnum.toString()
            val bannerList = ArrayList<String>()
            for(i in moviePosterDetail.data.photo){
                bannerList.add(i)
            }
            bannerList.add(moviePosterDetail.data.detailcover)
            movie_info.text = moviePosterDetail.data.info
            movie_summary.text = moviePosterDetail.data.officialstory
            movie_name.text = moviePosterDetail.data.title
            movie_banner.setImages(bannerList).setImageLoader(GlideImageLoader()).start()
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

class GlideImageLoader: ImageLoader(){
    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        Glide.with(context!!).load(path).into(imageView!!)
    }
}
