package com.yujing.kotlinapp.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yujing.utils.YToast


abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setContentView())
        initView()
        initData()
    }

    abstract fun setContentView(): Int
    abstract fun initView()
    abstract fun initData()

    /**
     * 跳转
     */
    fun start(ActivityClass: Class<*>) {
        val intent = Intent(this, ActivityClass)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        this.startActivity(intent)
    }

    /**
     * 弹出toast
     */
    @SuppressLint("ShowToast")
    fun show(text: String) {
        YToast.show(this, text)
    }
}
