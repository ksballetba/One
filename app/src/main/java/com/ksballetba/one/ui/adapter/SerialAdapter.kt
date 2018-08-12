package com.ksballetba.one.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.ksballetba.one.R
import com.ksballetba.one.entity.EssayListItem
import com.ksballetba.one.entity.OneDetail
import com.ksballetba.one.entity.SerialListItem
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.layout_read_ltem.view.*

class SerialAdapter(val mItems:MutableList<SerialListItem>,internal val didSelectedAtPos:(idx:Int)->Unit): RecyclerView.Adapter<SerialAdapter.ViewHolder>() {
    internal var mContext:Context? = null

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        var readItem = view.findViewById<RelativeLayout>(R.id.read_item)
        var readItemAvatar = view.findViewById<CircleImageView>(R.id.read_item_avatar)
        var readItemTitle = view.findViewById<TextView>(R.id.read_item_title)
        var readItemAuthor = view.findViewById<TextView>(R.id.read_item_author)
        var readItemGuide = view.findViewById<TextView>(R.id.read_item_guide)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if(mContext==null){
            mContext = parent.context
        }
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_read_ltem,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        fun bind(model: SerialListItem) {
            Glide.with(mContext!!).load(model.author.webUrl).into(holder.readItemAvatar)
            holder.readItemTitle.text = model.title
            holder.readItemAuthor.text = model.author.userName
            holder.readItemGuide.text = model.excerpt
            with(holder.readItem){
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

    fun update(newData:List<SerialListItem>){
        mItems.clear()
        mItems.addAll(newData)
        notifyDataSetChanged()
    }

}
