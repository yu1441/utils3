package com.yujing.kotlinapp.activity.page05


import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.ColorInt
import com.github.ybq.android.spinkit.SpinKitView
import com.github.ybq.android.spinkit.SpriteFactory
import com.github.ybq.android.spinkit.Style

/**
 * 半透明等待对话框，单例模式
 *
 * @author 余静 2019年8月29日10:38:57
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
class YKitBar : Dialog {
    private var activity: Activity? = null
    private var mProgressBar: ProgressBar? = null
    private var message1: String? = null
    private var message2: String? = null
    private var textView1: TextView? = null
    private var textView2: TextView? = null
    private var isCancelable: Boolean? = true
    var color: Int = Color.parseColor("#FF9FD160")
    var style: Style? = Style.CHASING_DOTS

    fun effect(@ColorInt color: Int, style: Style?) {
        this.color = color
        this.style = style
    }

    private fun getView(): LinearLayout {
        val linearLayout = LinearLayout(context)
        linearLayout.removeAllViews()
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.setPadding(
            dip2px(context, 10f), dip2px(context, 15f), dip2px(context, 10f), dip2px(
                context, 10f
            )
        )
        linearLayout.minimumHeight = dip2px(context, 90f)
        linearLayout.minimumWidth = dip2px(context, 90f)

        val imageViewLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        imageViewLayoutParams.gravity = Gravity.CENTER
        // mProgressBar = ProgressBar(context, null, android.R.attr.progressBarStyle)
        val spinKitView = SpinKitView(context)
        spinKitView.setColor(color)
        mProgressBar = spinKitView
        mProgressBar!!.indeterminateDrawable = SpriteFactory.create(style)
        mProgressBar!!.layoutParams = imageViewLayoutParams
        val tvParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        tvParams.setMargins(0, 0, 0, 0)
        tvParams.gravity = Gravity.CENTER
        val tv = TextView(context)
        tv.layoutParams = tvParams
        tv.textSize = 12f
        tv.setTextColor(Color.parseColor("#FFFFFF"))
        tv.text = message1

        val tvParams2 = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        tvParams2.setMargins(0, dip2px(context, 2f), 0, 0)
        tvParams2.gravity = Gravity.CENTER
        val tv2 = TextView(context)
        tv2.layoutParams = tvParams2
        tv2.textSize = 10f
        tv2.setTextColor(Color.parseColor("#AAFFFFFF"))
        tv2.text = message2

        linearLayout.addView(mProgressBar)
        linearLayout.addView(tv)
        linearLayout.addView(tv2)
        return linearLayout
    }

    // 构造函数，主题android.R.style.Theme_Holo_Light_Dialog_NoActionBar,Theme_DeviceDefault_Dialog_NoActionBar,Theme_Material_Dialog_NoActionBar
    private constructor(
        activity: Activity,
        text: String?,
        text2: String?,
        isCancelable: Boolean
    ) : super(
        activity,
        android.R.style.Theme_DeviceDefault_Dialog_NoActionBar
    ) {
        this.message1 = text
        this.message2 = text2
        this.isCancelable = isCancelable
        this.activity = activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val linearLayout = getView()
        setContentView(linearLayout)// 设置布局view
        val window = window
        window?.setGravity(Gravity.CENTER)// 设置Gravity居中
        //设置 window的Background为圆角
        val gradientDrawable = GradientDrawable()
        val strokeWidth = 1 // 1dp 边框宽度
        val roundRadius = 5 // 6dp 圆角半径
        val strokeColor = Color.parseColor("#203A89FF")//边框颜色
        val fillColor = Color.parseColor("#60000000")//内部填充颜色
        gradientDrawable.setColor(fillColor)
        gradientDrawable.cornerRadius = dip2px(context, roundRadius.toFloat()).toFloat()
        gradientDrawable.setStroke(dip2px(context, strokeWidth.toFloat()), strokeColor)
        if (window != null) {
            window.setBackgroundDrawable(gradientDrawable)
            val lp = window.attributes
            lp.alpha = 0.9f// 透明度
            lp.dimAmount = 0f// 模糊度
            //lp.width=dip2px(getContext(), 90);
            window.attributes = lp
        }
        isCancelable?.let { setCancelable(it) }// 是否允许按返回键
        //找到mProgressBar
        mProgressBar = linearLayout.getChildAt(0) as ProgressBar
        //找到textView1
        textView1 = linearLayout.getChildAt(1) as TextView
        //找到textView2
        textView2 = linearLayout.getChildAt(2) as TextView
        setMessage1(message1)
        setMessage2(message2)
        setCanceledOnTouchOutside(false)// 触摸屏幕其他区域不关闭对话框

    }

    fun setMessage1(message: String?) {
        this.message1 = message
        if (textView1 != null) {
            textView1!!.text = this.message1
            if (message1 == null || message1!!.isEmpty()) {
                textView1!!.visibility = View.GONE// 没有值就隐藏textView
            } else {
                textView1!!.visibility = View.VISIBLE
            }
        }
    }

    fun setMessage2(message: String?) {
        this.message2 = message
        if (textView2 != null) {
            textView2!!.text = this.message2
            if (message2 == null || message2!!.isEmpty()) {
                textView2!!.visibility = View.GONE
            } else {
                textView2!!.visibility = View.VISIBLE
            }
        }
    }

    fun getMessage1(): String? {
        return message1
    }

    fun getMessage2(): String? {
        return message2
    }

    companion object {
        /**
         * 获取Dialog
         * @return YShow
         */
        @SuppressLint("StaticFieldLeak")
        var dialog: YKitBar? = null
            private set

        @Synchronized
        fun show(activity: Activity) {
            show(activity, null, null, true)
        }

        @Synchronized
        fun show(activity: Activity, message: String) {
            show(activity, message, null, true)
        }

        @Synchronized
        fun show(activity: Activity, message: String, isCancelable: Boolean) {
            show(activity, message, null, isCancelable)
        }

        @Synchronized
        fun show(activity: Activity?, message1: String?, message2: String?, isCancelable: Boolean) {
            finish()
            if (activity == null || activity.isFinishing)
                return
            dialog = YKitBar(activity, message1, message2, isCancelable)
            dialog!!.show()
        }

        @Synchronized
        fun show(
            activity: Activity?,
            message1: String?,
            message2: String?,
            color: Int?,
            style: Style?,
            isCancelable: Boolean
        ) {
            finish()
            if (activity == null || activity.isFinishing)
                return
            dialog = YKitBar(activity, message1, message2, isCancelable)
            if (color != null) {
                dialog!!.color = color
            }
            dialog!!.style = style
            dialog!!.show()
        }

        @Synchronized
        fun showUpdate(activity: Activity) {
            showUpdate(activity, null, null, true)
        }

        @Synchronized
        fun showUpdate(activity: Activity, message: String) {
            showUpdate(activity, message, null, true)
        }

        @Synchronized
        fun showUpdate(activity: Activity, message: String, isCancelable: Boolean) {
            showUpdate(activity, message, null, isCancelable)
        }

        @Synchronized
        fun showUpdate(
            activity: Activity,
            message1: String?,
            message2: String?,
            isCancelable: Boolean
        ) {
            if (isShow) {
                setMessage(message1)
                setMessageOther(message2)
            } else {
                show(activity, message1, message2, isCancelable)
            }
        }

        //设置文本
        @Synchronized
        fun setMessage(message: String?) {
            dialog?.setMessage1(message)
        }

        @Synchronized
        fun setMessageOther(message: String?) {
            dialog?.setMessage2(message)
        }

        @Synchronized
        fun setCancel(isCancelable: Boolean) {
            dialog?.setCancelable(isCancelable)// 是否允许按返回键
        }

        /**
         * 是否正在显示
         * @return 是否正在显示
         */
        val isShow: Boolean
            @Synchronized get() = if (dialog != null) {
                dialog!!.isShowing
            } else false

        /**
         * 关闭对话框
         */
        fun finish() {
            dialog?.dismiss()
        }

        /**
         * dp转px
         * @param context context
         * @param dpValue dp
         * @return px
         */
        private fun dip2px(context: Context, dpValue: Float): Int {
            val scale = context.resources.displayMetrics.density
            return (dpValue * scale + 0.5f).toInt()
        }
    }
}
