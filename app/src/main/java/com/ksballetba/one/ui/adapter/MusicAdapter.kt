package com.ksballetba.one.ui.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.ksballetba.one.R
import com.ksballetba.one.entity.MusicDetail
import kotlinx.android.synthetic.main.layout_music_item.*

class MusicAdapter(val mItems:MutableList<MusicDetail>,internal val didSelectedAtPos:(idx:Int)->Unit):RecyclerView.Adapter<MusicAdapter.ViewHolder>() {
    internal var mContext:Context? = null

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        var musicItem = view.findViewById<LinearLayout>(R.id.music_item)
        var musicItemCardView = view.findViewById<CardView>(R.id.music_item_cardview)
        var musicItemImage = view.findViewById<ImageView>(R.id.music_item_image)
        var musicItemAuthor = view.findViewById<TextView>(R.id.music_item_auhtor)
        var musicItemTitle = view.findViewById<TextView>(R.id.music_item_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if(mContext==null){
            mContext = parent.context
        }
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_music_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        fun bind(model:MusicDetail){
            Glide.with(mContext!!).load(model.data.cover).into(holder.musicItemImage)
            holder.musicItemAuthor.text = "${model.data.author.userName}  "
            holder.musicItemTitle.text = model.data.storySummary
            with(holder.musicItemCardView){
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

    fun update(newData:MutableList<MusicDetail>){
        mItems.clear()
        mItems.addAll(newData)
        notifyDataSetChanged()
    }

    fun add(addData:List<MusicDetail>){
        mItems.addAll(addData)
        notifyDataSetChanged()
    }
}