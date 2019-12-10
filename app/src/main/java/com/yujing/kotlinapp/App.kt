package com.yujing.kotlinapp

import android.app.Application
import android.content.Context
import android.util.Log
import com.yujing.ycrash.YCrash

class App : Application() {
    //标准单列
//    companion object {
//        val instance: App by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {App()}
//    }
    //单列
    companion object {
        private var instance: App? = null
            get() {
                if (field == null) field = App()
                return field
            }

        @Synchronized
        fun get(): App {
            return instance!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        YCrash.getInstance().appName = "kotlinAPP"
        YCrash.getInstance().setCrashInfoListener { appInfo -> Log.e("异常信息", appInfo.崩溃信息) }
        YCrash.getInstance().init(this)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }
}