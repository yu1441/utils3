package com.yujing.kotlinapp.activity.page06


import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.yujing.kotlinapp.R
import com.yujing.kotlinapp.activity.BaseActivity
import com.yujing.utils.YDateDialog
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_0601.*
import java.text.SimpleDateFormat
import java.util.*

class Activity0601 : BaseActivity() {
    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    override fun setContentView(): Int {
        return R.layout.activity_0601
    }

    override fun initView() {
        button1.setOnClickListener {
            val selectedDate = Calendar.getInstance()
            val startDate = Calendar.getInstance()
            val endDate = Calendar.getInstance()
            startDate.set(2019, 0, 1)
            endDate.set(2030, 11, 31)

            //时间选择器
            val pvTime = TimePickerBuilder(this) { date, _ ->
                Toasty.success(this, simpleDateFormat.format(date)).show()
            }
                .setType(booleanArrayOf(true, true, true, false, false, false))// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setTitleSize(18)//标题文字大小
                .setTitleText("请选择日期")//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setTitleColor(-0xd53e14)//标题文字颜色
                .setSubmitColor(-0xfa8201)//确定按钮文字颜色
                .setCancelColor(-0xfa8201)//取消按钮文字颜色
                .setTitleBgColor(-0xf0f10)//标题背景颜色 Night mode
                .setBgColor(-0x1f000001)//滚轮背景颜色 Night mode
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(true)//是否显示为对话框样式
                .build()
            pvTime.show()
        }
        button2.setOnClickListener {
            val pvTime = TimePickerBuilder(this) { date, _ ->
                Toasty.success(this, simpleDateFormat.format(date)).show()
            }
                .setType(booleanArrayOf(true, true, true, true, true, true))// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(true)//是否显示为对话框样式
                .build()
            pvTime.show()
        }
        button3.setOnClickListener {
            val yDateDialog = YDateDialog(this)
            yDateDialog.isShowTime=false
            yDateDialog.show { format, _, _, _, _, _, _, _ ->
                Toasty.success(
                    this,
                    format
                ).show()
            }
        }
        button4.setOnClickListener { }
        button5.setOnClickListener { }
        button6.setOnClickListener { }
        button7.setOnClickListener { }
        button8.setOnClickListener { }
    }

    override fun initData() {

    }

}