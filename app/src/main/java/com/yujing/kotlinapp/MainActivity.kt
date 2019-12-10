package com.yujing.kotlinapp

import android.view.KeyEvent
import com.yujing.adapter.YSetRecyclerView
import com.yujing.kotlinapp.activity.BaseActivity
import com.yujing.kotlinapp.activity.page01.Activity0101
import com.yujing.kotlinapp.activity.page02.Activity0201
import com.yujing.kotlinapp.activity.page03.Activity0301
import com.yujing.kotlinapp.activity.page04.Activity0401
import com.yujing.kotlinapp.activity.page05.Activity0501
import com.yujing.kotlinapp.activity.page06.Activity0601
import com.yujing.kotlinapp.activity.page07.Activity0701
import com.yujing.kotlinapp.activity.page08.Activity0801
import com.yujing.kotlinapp.activity.page09.Activity0901
import com.yujing.kotlinapp.activity.page10.Activity1001
import com.yujing.kotlinapp.activity.page11.Activity1101
import com.yujing.kotlinapp.activity.page12.Activity1201
import com.yujing.kotlinapp.activity.page13.Activity1301
import com.yujing.kotlinapp.activity.page14.Activity1401
import com.yujing.kotlinapp.activity.page15.Activity1501
import com.yujing.kotlinapp.activity.page16.Activity1601
import com.yujing.kotlinapp.activity.page17.Activity1701
import com.yujing.kotlinapp.activity.page18.Activity1801
import com.yujing.kotlinapp.activity.page19.Activity1901
import com.yujing.kotlinapp.activity.page20.Activity2001
import com.yujing.kotlinapp.adapter.MainActivityAdapter
import com.yujing.utils.YEventCount
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private val yEventCount: YEventCount = YEventCount(2000, 2)
    override fun setContentView(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        YSetRecyclerView.init(this, mainRecyclerView)
        yEventCount.setEventFailListener { Toasty.error(this, "再按一次退出").show() }
        yEventCount.setEventSuccessListener { finish() }
    }

    override fun initData() {
        val list = listOf(
            "1:viewPager",
            "2:屏幕旋转保存数据",
            "3:Toasty测试",
            "4:网络测试",
            "5:Android-SpinKit过渡动画",
            "6:日期选择",
            "7:YSAVE,网络请求返回对象",
            "8:TTS测试",
            "9:",
            "10:",
            "11:",
            "12:",
            "13:",
            "14:",
            "15:",
            "16:",
            "17:",
            "18:",
            "19:",
            "20:"
        )
        val listActivity = listOf(
            Activity0101::class.java,
            Activity0201::class.java,
            Activity0301::class.java,
            Activity0401::class.java,
            Activity0501::class.java,
            Activity0601::class.java,
            Activity0701::class.java,
            Activity0801::class.java,
            Activity0901::class.java,
            Activity1001::class.java,
            Activity1101::class.java,
            Activity1201::class.java,
            Activity1301::class.java,
            Activity1401::class.java,
            Activity1501::class.java,
            Activity1601::class.java,
            Activity1701::class.java,
            Activity1801::class.java,
            Activity1901::class.java,
            Activity2001::class.java
        )

        val adapter: MainActivityAdapter<String> = MainActivityAdapter(this, list)
        mainRecyclerView.adapter = adapter
        adapter.setOnItemClickListener { _, position ->
            if (listActivity.size > position) start(
                listActivity[position]
            )
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN && KeyEvent.KEYCODE_BACK == keyCode) {
            yEventCount.event()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
