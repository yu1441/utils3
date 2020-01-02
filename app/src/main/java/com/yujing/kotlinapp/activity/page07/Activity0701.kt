package com.yujing.kotlinapp.activity.page07


import com.yujing.kotlinapp.R
import com.yujing.kotlinapp.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_0701.*

class Activity0701 : BaseActivity() {

    override fun setContentView(): Int {
        return R.layout.activity_0701
    }

    override fun initView() {
       val presenter= Presenter07<String>()
        button1.setOnClickListener { presenter.getData()}
        button2.setOnClickListener { presenter.getType1()}
        button3.setOnClickListener { presenter.getType2()}
        button4.setOnClickListener { presenter.getType3()}
        button5.setOnClickListener { presenter.getYSave1()}
        button6.setOnClickListener { presenter.getYSave2()}
        button7.setOnClickListener { presenter.getTest1()}
        button8.setOnClickListener { presenter.getTest2(this)}
    }


    override fun initData() {

    }

}