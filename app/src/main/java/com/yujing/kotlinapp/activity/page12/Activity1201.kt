package com.yujing.kotlinapp.activity.page12


import com.yujing.kotlinapp.R
import com.yujing.kotlinapp.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_1201.*

class Activity1201 : BaseActivity() {

    override fun setContentView(): Int {
        return R.layout.activity_1201
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