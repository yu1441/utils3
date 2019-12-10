package com.yujing.kotlinapp.fragment


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment


abstract class BaseFragment : Fragment() {
    private var toast: Toast? = null
    protected var rootView: View? = null// 缓存Fragment view
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(setContentView(), container, false)
        init()
        initData()
        return rootView
    }

    abstract fun setContentView(): Int
    abstract fun init()
    abstract fun initData()


    /**
     * 跳转
     */
    fun start(ActivityClass: Class<*>) {
        val intent = Intent(activity, ActivityClass)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        this.startActivity(intent)
    }


            /**
     * 弹出toast
     */
            @SuppressLint("ShowToast")
    fun show(text: String) {
        if (toast == null) {
            toast = Toast.makeText(activity, text, Toast.LENGTH_SHORT)
        } else {
            toast!!.setText(text)
            toast!!.duration = Toast.LENGTH_SHORT
        }
        toast!!.show()
    }
}
