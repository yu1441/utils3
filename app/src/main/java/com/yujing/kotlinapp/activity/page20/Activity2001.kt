package com.yujing.kotlinapp.activity.page20


import com.yujing.kotlinapp.R
import com.yujing.kotlinapp.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_2001.*

class Activity2001 : BaseActivity() {

    override fun setContentView(): Int {
        return R.layout.activity_2001
    }

    override fun initView() {
        button1.setOnClickListener { }
        button2.setOnClickListener { }
        button3.setOnClickListener { }
        button4.setOnClickListener { }
        button5.setOnClickListener { }
        button6.setOnClickListener { }
        button7.setOnClickListener { }
        button8.setOnClickListener { }
    }

    override fun initData() {

    }

}