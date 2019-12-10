package com.yujing.kotlinapp.adapter

import android.content.Context
import android.view.View
import android.widget.TextView
import com.yujing.adapter.YBaseYRecyclerViewAdapter
import com.yujing.kotlinapp.R

class MainActivityAdapter<T>(context: Context, list: List<T>) : YBaseYRecyclerViewAdapter<T>(context, list) {
    override fun setLayout(): Int {
        return R.layout.main_item
    }
    override fun setViewHolder(itemView: View?): BaseViewHolder? {
        return  MainViewHolder(itemView)
    }
}

class MainViewHolder(view: View?) : YBaseYRecyclerViewAdapter.BaseViewHolder(view) {
    private var textView: TextView ?= null
    override fun findView(convertView: View?) {
        textView=convertView?.findViewById(R.id.mainItemTextView)
    }
    override fun setData(position: Int, obj: Any?, adapterList: MutableList<Any?>?, adapter: YBaseYRecyclerViewAdapter<*>?) {
        val value:String = obj as String
        textView!!.text=value
    }
}