package com.yujing.kotlinapp.activity.page01

import android.view.KeyEvent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.yujing.kotlinapp.R
import com.yujing.kotlinapp.activity.BaseActivity
import com.yujing.kotlinapp.fragment.Fragment1
import com.yujing.kotlinapp.fragment.Fragment2
import com.yujing.kotlinapp.fragment.Fragment3
import com.yujing.kotlinapp.fragment.Fragment4
import kotlinx.android.synthetic.main.activity_0101.*


class Activity0101 : BaseActivity() {
    private val fragments = ArrayList<Fragment>()
    override fun setContentView(): Int {
        return R.layout.activity_0101
    }

    override fun initView() {
        fragments.add(Fragment1())
        fragments.add(Fragment2())
        fragments.add(Fragment3())
        fragments.add(Fragment4())

        val mAdapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getCount(): Int {
                return fragments.size
            }

            override fun getItem(position: Int): Fragment {
                return fragments[position]
            }
        }
        viewPager.offscreenPageLimit = mAdapter.count//缓存Count()个
        viewPager.adapter = mAdapter
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(state: Int) {
                bottomNavigationView.menu.getItem(state).isChecked = true/*设置对应的item为选中状态*/
            }

            override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}

            override fun onPageScrollStateChanged(arg0: Int) {}
        })
    }

    override fun initData() {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            item.isChecked = true/*设置对应的item为选中状态*/
            val i =
                if (item.itemId == R.id.index1) 0 else if (item.itemId == R.id.index2) 1 else if (item.itemId == R.id.index3) 2 else if (item.itemId == R.id.index4) 3 else -1
            viewPager.currentItem = i
            
             false
        }
    }

    private var touchTime: Long = 0
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN && KeyEvent.KEYCODE_BACK == keyCode) {
            if (System.currentTimeMillis() - touchTime >= 2000) {
                touchTime = System.currentTimeMillis()
                show("再按一次退出")
            } else
                finish()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}

