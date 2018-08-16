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

import com.ksballetba.one.R
import com.ksballetba.one.entity.MovieListItem
import com.ksballetba.one.entity.MoviePosterDetail
import com.ksballetba.one.tools.network.NetworkManager
import com.ksballetba.one.ui.activity.MovieDetailActivity
import com.ksballetba.one.ui.adapter.MovieAdapter
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_movie.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class MovieFragment : Fragment() {

    var mMovieList = mutableListOf<MoviePosterDetail>()

    lateinit var mMovieAdapter: MovieAdapter

    lateinit var movieSwipe:SmartRefreshLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieSwipe = view.findViewById(R.id.movie_swipe)
        initRefreshLayout()
        init()
    }

    private fun init(){
        val myLayoutManager = LinearLayoutManager(activity)
        myLayoutManager.orientation = LinearLayoutManager.VERTICAL
        movie_recyclerview.layoutManager = myLayoutManager
        mMovieAdapter = MovieAdapter(mMovieList){idx ->
            val intent = Intent(activity,MovieDetailActivity::class.java)
            intent.putExtra("movie_id",mMovieList[idx].data.id)
            startActivity(intent)
        }
        movie_recyclerview.itemAnimator = DefaultItemAnimator()
        movie_recyclerview.adapter = mMovieAdapter
        if(mMovieList.size == 0){
            refreshList()
        }
    }

    private fun initRefreshLayout(){
        movieSwipe.setEnableRefresh(true)
        movieSwipe.setEnableLoadMore(true)
        movieSwipe.setEnableAutoLoadMore(true)
        movieSwipe.setEnableOverScrollBounce(true)//是否启用越界回弹
        movieSwipe.setEnableScrollContentWhenLoaded(true)//是否在加载完成时滚动列表显示新的内容
        movieSwipe.setEnableHeaderTranslationContent(true)//是否下拉Header的时候向下平移列表或者内容
        movieSwipe.setEnableFooterTranslationContent(true)//是否上拉Footer的时候向上平移列表或者内容
        movieSwipe.setEnableLoadMoreWhenContentNotFull(true)
        movieSwipe.setOnRefreshListener {
            if(mMovieList.size==0){
                refreshList()
            } else{
                movieSwipe.finishRefresh(2000)
            }
        }
        movieSwipe.setOnLoadMoreListener {
            loadMore()
        }
    }

    private fun refreshList(){
        if(activity!=null){
            movieSwipe.autoRefresh()
            NetworkManager.getMovieIdListObservable("0").concatMap{
                NetworkManager.getMoviePosterDetailObservable(it)
            }.toList()!!.subscribeOn(Schedulers.newThread()).
                    observeOn(AndroidSchedulers.mainThread()).subscribe({ list->
                mMovieList = list
                mMovieAdapter.update(mMovieList)
                movieSwipe.finishRefresh()
            })
        }
    }

    private fun loadMore(){
        NetworkManager.getMovieIdListObservable(mMovieList[mMovieList.size-1].data.id).concatMap{
            NetworkManager.getMoviePosterDetailObservable(it)
        }.toList()!!.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list->
                    mMovieList.addAll(list)
                    mMovieAdapter.add(list)
                    movieSwipe.finishLoadMore()
                })
    }

}
