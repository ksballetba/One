package com.ksballetba.one.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.ksballetba.one.R
import com.ksballetba.one.entity.MoviePosterDetail

class MovieAdapter(val mItems:MutableList<MoviePosterDetail>,internal val didSelectedAtPos:(idx:Int)->Unit):RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    internal var mContext:Context? = null

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        var movieItem = view.findViewById<FrameLayout>(R.id.movie_item)
        var movieItemImage = view.findViewById<ImageView>(R.id.movie_item_image)
        var movieItemTitle = view.findViewById<TextView>(R.id.movie_item_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if(mContext==null){
            mContext = parent.context
        }
        val view=LayoutInflater.from(mContext).inflate(R.layout.layout_movie_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        fun bind(model:MoviePosterDetail){
            Glide.with(mContext!!).load(model.data.detailcover).into(holder.movieItemImage)
            holder.movieItemTitle.text = model.data.title
            with(holder.movieItem){
                setOnClickListener {
                    didSelectedAtPos(position)
                }
            }
        }

        val item = mItems[position]
        bind(item)

    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    fun update(newData:MutableList<MoviePosterDetail>){
        mItems.clear()
        mItems.addAll(newData)
        notifyDataSetChanged()
    }

    fun add(addData:List<MoviePosterDetail>){
        mItems.addAll(addData)
        notifyDataSetChanged()
    }
}