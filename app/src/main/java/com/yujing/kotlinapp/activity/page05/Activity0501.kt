package com.yujing.kotlinapp.activity.page05


import android.graphics.Color
import android.widget.ProgressBar
import com.github.ybq.android.spinkit.SpriteFactory
import com.github.ybq.android.spinkit.Style
import com.yujing.kotlinapp.R
import com.yujing.kotlinapp.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_0501.*

class Activity0501 : BaseActivity() {
    override fun setContentView(): Int {
        return R.layout.activity_0501
    }

    override fun initView() {
        val progressBar = spinKitView as ProgressBar

        button1.setOnClickListener {
            spinKitView.setColor(Color.GREEN)
            progressBar.indeterminateDrawable = SpriteFactory.create(Style.FADING_CIRCLE)
        }
        button2.setOnClickListener {
            spinKitView.setColor(Color.BLUE)
            progressBar.indeterminateDrawable = SpriteFactory.create(Style.MULTIPLE_PULSE)
        }
        button3.setOnClickListener {
            spinKitView.setColor(Color.YELLOW)
            progressBar.indeterminateDrawable = SpriteFactory.create(Style.THREE_BOUNCE)
        }
        button4.setOnClickListener {
            spinKitView.setColor(Color.RED)
            progressBar.indeterminateDrawable = SpriteFactory.create(Style.FOLDING_CUBE)
        }

        button5.setOnClickListener {
            YProgressBar.show(this, editText1.text.toString(), editText2.text.toString(), true)
        }
        button6.setOnClickListener {
            YKitBar.show(this, editText1.text.toString(), editText2.text.toString(), true)
        }

        button7.setOnClickListener {
            YKitBar.show(
                this,
                editText1.text.toString(),
                editText2.text.toString(),
                Color.RED,
                Style.WAVE,
                true
            )
        }
        button8.setOnClickListener {
            YKitBar.show(
                this,
                editText1.text.toString(),
                editText2.text.toString(),
                Color.GREEN,
                Style.FADING_CIRCLE,
                true
            )
        }
    }

    override fun initData() {

    }

}