package com.yujing.kotlinapp.activity.page03


import android.graphics.Color
import com.yujing.kotlinapp.R
import com.yujing.kotlinapp.activity.BaseActivity
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_0301.*

class Activity0301 : BaseActivity() {

    override fun setContentView(): Int {
        return R.layout.activity_0301
    }

    override fun initView() {
        button1.setOnClickListener { Toasty.normal(this, editText1.text).show() }
        button2.setOnClickListener { Toasty.info(this, editText1.text).show() }
        button3.setOnClickListener { Toasty.success(this, editText1.text).show() }
        button4.setOnClickListener { Toasty.error(this, editText1.text).show() }
        button5.setOnClickListener { Toasty.custom(this,editText1.text,getDrawable(R.mipmap.activity_mian_1_2),Toasty.LENGTH_SHORT,true).show() }
        button6.setOnClickListener { Toasty.custom(this,editText1.text,getDrawable(R.mipmap.activity_mian_3_2),Toasty.LENGTH_SHORT,false).show() }
        button7.setOnClickListener { Toasty.custom(this,editText1.text,getDrawable(R.mipmap.activity_mian_2_2), Color.RED, Color.BLUE,Toasty.LENGTH_SHORT,true,false).show() }
        button8.setOnClickListener { Toasty.custom(this,editText1.text,getDrawable(R.mipmap.activity_mian_4_2), Color.parseColor("#22FF5555"),Color.GREEN,Toasty.LENGTH_SHORT,true,true).show() }
    }

    override fun initData() {

    }

}