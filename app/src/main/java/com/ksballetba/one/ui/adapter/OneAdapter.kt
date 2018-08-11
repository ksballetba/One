package com.ksballetba.one.ui.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.transition.Visibility
import android.view.LayoutInflater
import com.ksballetba.one.entity.OneDetail
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.ksballetba.one.R
import kotlinx.android.synthetic.main.layout_one_discovery.view.*

class OneAdapter(val mItems:MutableList<OneDetail>):RecyclerView.Adapter<OneAdapter.ViewHolder>(){
    internal var mContext: Context? = null
    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        var oneDiscoveryPhoto = view.findViewById<ImageView>(R.id.one_discovery_photo)
        var oneDiscoveryPhotographer = view.findViewById<TextView>(R.id.one_discovery_photographer)
        var oneDiscoveryForward = view.findViewById<TextView>(R.id.one_discovery_forward)
        var oneDiscoveryForwardTitle = view.findViewById<TextView>(R.id.one_discovery_forwardtitle)
        var oneDiscoveryFavCount= view.findViewById<TextView>(R.id.one_discovery_favcount)
        var oneStoryTitle = view.findViewById<TextView>(R.id.one_story_title)
        var oneStoryAuthor = view.findViewById<TextView>(R.id.one_story_author)
        var oneStoryPhoto = view.findViewById<ImageView>(R.id.one_story_photo)
        var oneStoryDigest = view.findViewById<TextView>(R.id.one_story_digest)
        var oneStoryDate = view.findViewById<TextView>(R.id.one_story_date)
        var oneStoryFavCount = view.findViewById<TextView>(R.id.one_story_favcount)
        var oneSerialTitle = view.findViewById<TextView>(R.id.one_serial_title)
        var oneSerial = view.findViewById<CardView>(R.id.one_serial)
        var oneSerialAuthor = view.findViewById<TextView>(R.id.one_serial_author)
        var oneSerialPhoto = view.findViewById<ImageView>(R.id.one_serial_photo)
        var oneSerialDigest = view.findViewById<TextView>(R.id.one_serial_digest)
        var oneSerialDate = view.findViewById<TextView>(R.id.one_serial_date)
        var oneSerialFavCount = view.findViewById<TextView>(R.id.one_serial_favcount)
        var oneEssayTitle = view.findViewById<TextView>(R.id.one_essay_title)
        var oneEssayAuthor = view.findViewById<TextView>(R.id.one_essay_author)
        var oneEssayPhoto = view.findViewById<ImageView>(R.id.one_essay_photo)
        var oneEssayDigest = view.findViewById<TextView>(R.id.one_essay_digest)
        var oneEssayDate = view.findViewById<TextView>(R.id.one_essay_date)
        var oneEssayFavCount = view.findViewById<TextView>(R.id.one_essay_favcount)
        var oneEssay = view.findViewById<CardView>(R.id.one_essay)
        var oneQuestionTitle = view.findViewById<TextView>(R.id.one_question_title)
        var oneQuestionAuthor = view.findViewById<TextView>(R.id.one_question_author)
        var oneQuestionPhoto = view.findViewById<ImageView>(R.id.one_question_photo)
        var oneQuestionDigest = view.findViewById<TextView>(R.id.one_question_digest)
        var oneQuestionDate = view.findViewById<TextView>(R.id.one_question_date)
        var oneQuestionFavCount = view.findViewById<TextView>(R.id.one_question_favcount)
        var oneQuestion = view.findViewById<CardView>(R.id.one_question)
        var oneMusicTitle = view.findViewById<TextView>(R.id.one_music_title)
        var oneMusicAuthor = view.findViewById<TextView>(R.id.one_music_author)
        var oneMusicPhoto = view.findViewById<ImageView>(R.id.one_music_photo)
        var oneMusicDigest = view.findViewById<TextView>(R.id.one_music_digest)
        var oneMusicDate = view.findViewById<TextView>(R.id.one_music_date)
        var oneMusicFavCount = view.findViewById<TextView>(R.id.one_music_favcount)
        var oneMusic = view.findViewById<CardView>(R.id.one_music)
        var oneMovieTitle = view.findViewById<TextView>(R.id.one_movie_title)
        var oneMovieAuthor = view.findViewById<TextView>(R.id.one_movie_author)
        var oneMoviePhoto = view.findViewById<ImageView>(R.id.one_movie_photo)
        var oneMovieDigest = view.findViewById<TextView>(R.id.one_movie_digest)
        var oneMovieDate = view.findViewById<TextView>(R.id.one_movie_date)
        var oneMovieFavCount = view.findViewById<TextView>(R.id.one_movie_favcount)
        var oneMovie = view.findViewById<CardView>(R.id.one_movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if(mContext==null){
            mContext = parent.context
        }
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_one_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        fun bind(model:OneDetail){
            Glide.with(mContext!!).load(model.data.contentList[0].imgUrl).into(holder.oneDiscoveryPhoto)
            holder.oneDiscoveryPhotographer.text = "${model.data.contentList[0].title}|${model.data.contentList[0].picInfo}"
            holder.oneDiscoveryForward.text = model.data.contentList[0].forward
            holder.oneDiscoveryForwardTitle.text = model.data.contentList[0].wordsInfo
            holder.oneDiscoveryFavCount.text = model.data.contentList[0].likeCount.toString()
            holder.oneStoryTitle.text = model.data.contentList[1].title
            holder.oneStoryAuthor.text = "文/${model.data.contentList[1].author.userName}"
            Glide.with(mContext!!).load(model.data.contentList[1].imgUrl).into(holder.oneStoryPhoto)
            holder.oneStoryDigest.text = model.data.contentList[1].forward
            holder.oneStoryDate.text = model.data.contentList[1].postDate.substring(5,10)
            holder.oneStoryFavCount.text = model.data.contentList[1].likeCount.toString()
            for (i in 2 until model.data.contentList.size){
                when(model.data.contentList[i].category.toInt()){
                    1->{
                        holder.oneEssayTitle.text = model.data.contentList[i].title
                        holder.oneEssayAuthor.text = "文/${model.data.contentList[i].author.userName}"
                        Glide.with(mContext!!).load(model.data.contentList[i].imgUrl).into(holder.oneEssayPhoto)
                        holder.oneEssayDigest.text = model.data.contentList[i].forward
                        holder.oneEssayDate.text = model.data.contentList[i].postDate.substring(5,10)
                        holder.oneEssayFavCount.text = model.data.contentList[i].likeCount.toString()
                    }
                    2->{
                        holder.oneSerialTitle.text = model.data.contentList[i].title
                        holder.oneSerialAuthor.text = "文/${model.data.contentList[i].author.userName}"
                        Glide.with(mContext!!).load(model.data.contentList[i].imgUrl).into(holder.oneSerialPhoto)
                        holder.oneSerialDigest.text = model.data.contentList[i].forward
                        holder.oneSerialDate.text = model.data.contentList[i].postDate.substring(5,10)
                        holder.oneSerialFavCount.text = model.data.contentList[i].likeCount.toString()
                    }
                    3->{
                        holder.oneQuestionTitle.text = model.data.contentList[i].title
                        holder.oneQuestionAuthor.text = "文/${model.data.contentList[i].author.userName}"
                        Glide.with(mContext!!).load(model.data.contentList[i].imgUrl).into(holder.oneQuestionPhoto)
                        holder.oneQuestionDigest.text = model.data.contentList[i].forward
                        holder.oneQuestionDate.text = model.data.contentList[i].postDate.substring(5,10)
                        holder.oneQuestionFavCount.text = model.data.contentList[i].likeCount.toString()
                    }
                    4->{
                        holder.oneMusicTitle.text = model.data.contentList[i].title
                        holder.oneMusicAuthor.text = "文/${model.data.contentList[i].author.userName}"
                        Glide.with(mContext!!).load(model.data.contentList[i].imgUrl).into(holder.oneMusicPhoto)
                        holder.oneMusicDigest.text = model.data.contentList[i].forward
                        holder.oneMusicDate.text = model.data.contentList[i].postDate.substring(5,10)
                        holder.oneMusicFavCount.text = model.data.contentList[i].likeCount.toString()
                    }
                    5->{
                        holder.oneMovieTitle.text = model.data.contentList[i].title
                        holder.oneMovieAuthor.text = "文/${model.data.contentList[i].author.userName}"
                        Glide.with(mContext!!).load(model.data.contentList[i].imgUrl).into(holder.oneMoviePhoto)
                        holder.oneMovieDigest.text = model.data.contentList[i].forward
                        holder.oneMovieDate.text = model.data.contentList[i].postDate.substring(5,10)
                        holder.oneMovieFavCount.text = model.data.contentList[i].likeCount.toString()
                    }
                }
            }
            if(holder.oneEssayTitle.text==""){
                holder.oneEssay.visibility = View.GONE
            }
            if(holder.oneSerialTitle.text==""){
                holder.oneEssay.visibility = View.GONE
            }
            if(holder.oneQuestionTitle.text==""){
                holder.oneEssay.visibility = View.GONE
            }
            if(holder.oneMusicTitle.text==""){
                holder.oneEssay.visibility = View.GONE
            }
            if(holder.oneMovieTitle.text==""){
                holder.oneEssay.visibility = View.GONE
            }
        }
        val item = mItems[position]
        bind(item)
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    fun update(newData:List<OneDetail>){
        mItems.clear()
        mItems.addAll(newData)
        notifyDataSetChanged()
    }
}