package com.yujing.kotlinapp.activity.page02

import android.os.Bundle
import com.yujing.kotlinapp.R
import com.yujing.kotlinapp.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_0201.*

/**
 * Created by yujing on 2017/10/30 0030.
 * 旋转布局修改测试，和旋转控件赋值测试
 */
class Activity0201 : BaseActivity() {
    private val keyText: String = "keyText"
    private val keyEdit1: String = "keyEdit1"
    private val keyEdit2: String = "keyEdit2"
    private var editValue: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            editValue = savedInstanceState.getInt(keyText)
            editText1.setText(savedInstanceState.getString(keyEdit1))
            editText2.setText(savedInstanceState.getString(keyEdit2))
        }
        button.setOnClickListener { editValue++; text.text = "+1后的值:$editValue" }
        text.text = "测试数据的值:$editValue"
    }

    override fun setContentView(): Int {
        return R.layout.activity_0201
    }

    override fun initView() {}

    override fun initData() {}


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(keyText, editValue)
        outState.putString(keyEdit1, editText1.text.toString())
        outState.putString(keyEdit2, editText2.text.toString())
        super.onSaveInstanceState(outState)
    }

}