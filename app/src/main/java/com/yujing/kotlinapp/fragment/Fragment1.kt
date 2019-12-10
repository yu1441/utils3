package com.yujing.kotlinapp.fragment

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.yujing.kotlinapp.R
import java.util.*

class Fragment1 : BaseFragment() {
    private var timer = Timer()
    private val adBitmaps = ArrayList<Bitmap>()
    lateinit var adViewPager: ViewPager//延迟初始化
    lateinit var llPoints: LinearLayout

    override fun setContentView(): Int {
        return R.layout.fragment_fragment_index1
    }

    override fun init() {
        adViewPager = rootView!!.findViewById(R.id.adViewPager)
        llPoints = rootView!!.findViewById(R.id.llPoints)
        //添加4张图片
        adBitmaps.add(BitmapFactory.decodeResource(activity!!.resources, R.mipmap.ad1))
        adBitmaps.add(BitmapFactory.decodeResource(activity!!.resources, R.mipmap.ad1))
        adBitmaps.add(BitmapFactory.decodeResource(activity!!.resources, R.mipmap.ad1))
        adBitmaps.add(BitmapFactory.decodeResource(activity!!.resources, R.mipmap.ad1))
    }

    override fun initData() {
        adsHome()
    }

    /**
     * 首页广告
     */
    private fun adsHome() {
        //添加view显示广告
        val ads = ArrayList<ImageView>()
        val viewGroupLayoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        for (bitmap in adBitmaps) {
            val imageView = ImageView(context)
            imageView.layoutParams = viewGroupLayoutParams
            imageView.scaleType = ImageView.ScaleType.FIT_XY
            imageView.setImageBitmap(bitmap)
            ads.add(imageView)
        }
        //点的样式
        val pints = ArrayList<ImageView>()
        val pointStyle = intArrayOf(R.mipmap.point_gray, R.mipmap.point_white)
        val linearLayoutParamsPoint = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        for (i in 0 until adBitmaps.size) {
            val imageViewPoint = ImageView(context)
            imageViewPoint.setPadding(10, 10, 10, 10)//4DP
            imageViewPoint.layoutParams = linearLayoutParamsPoint
            imageViewPoint.scaleType = ImageView.ScaleType.FIT_XY
            imageViewPoint.setImageDrawable(ContextCompat.getDrawable(context!!, pointStyle[0]))
            pints.add(imageViewPoint)
            llPoints.addView(imageViewPoint)
        }

        adViewPager.adapter = object : PagerAdapter() {
            override fun isViewFromObject(arg0: View, arg1: Any): Boolean {
                return arg0 === arg1
            }

            override fun getCount(): Int {
                return Integer.MAX_VALUE
            }

            override fun destroyItem(viewGroup: ViewGroup, position: Int, `object`: Any) {}

            override fun instantiateItem(viewGroup: ViewGroup, position: Int): Any {
                val view = ads[position % ads.size]
                viewGroup.removeView(view)
                viewGroup.addView(view, 0)
                return view
            }
        }

        adViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(arg0: Int) {
                //动态设置小圆点
                for (i in pints.indices) {
                    pints[i].setImageDrawable(ContextCompat.getDrawable(context!!, pointStyle[0]))
                    if (i == adViewPager.currentItem % ads.size)
                        pints[i].setImageDrawable(
                            ContextCompat.getDrawable(
                                context!!,
                                pointStyle[1]
                            )
                        )
                }
            }

            override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}

            override fun onPageScrollStateChanged(arg0: Int) {}
        })
        //打开APP后设置小圆点
        for (i in pints.indices) {
            pints[i].setImageDrawable(ContextCompat.getDrawable(context!!, pointStyle[0]))
            if (i == adViewPager.currentItem % ads.size)
                pints[i].setImageDrawable(ContextCompat.getDrawable(context!!, pointStyle[1]))
        }
        timer.schedule(object : TimerTask() {
            override fun run() {
                activity?.runOnUiThread {
                    adViewPager.currentItem = adViewPager.currentItem + 1//设置图片为当前显示图片加1
                }
            }
        }, 2000, 2000)
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }
}
