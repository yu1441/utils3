package com.yujing.kotlinapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yujing.kotlinapp.R
import kotlinx.android.synthetic.main.main_item.view.*

class MyAdapter<T>(private val mContext: Context, private var list: List<T>?) :
    RecyclerView.Adapter<MyViewHolder>() {

    fun setList(list: List<T>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.main_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val items = list!![position] as String
        holder.itemView.mainItemTextView.text = items
    }
}

class MyViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView)