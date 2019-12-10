package com.yujing.kotlinapp.activity.page08


import com.yujing.kotlinapp.R
import com.yujing.kotlinapp.activity.BaseActivity
import com.yujing.utils.YTts
import kotlinx.android.synthetic.main.activity_0801.*

class Activity0801 : BaseActivity() {
     var yTts: YTts? = null
    override fun setContentView(): Int {
        return R.layout.activity_0801
    }

    override fun initView() {
        button1.setOnClickListener { 阻断播放() }
        button2.setOnClickListener { 队列播放()}
        button3.setOnClickListener { }
        button4.setOnClickListener { }
        button5.setOnClickListener { }
        button6.setOnClickListener { }
        button7.setOnClickListener { }
        button8.setOnClickListener { }
        yTts=YTts(this)
    }

    private fun 队列播放() {
        yTts?.speakQueue("你好abcdefg12345")
    }

    private fun 阻断播放() {
        yTts?.speak("你好abcdefg12345")
    }

    override fun initData() {

    }

    override fun onDestroy() {
        yTts?.onDestroy()
        super.onDestroy()
    }
}