package io.merculet.wxbot.base

import android.app.Application
import kotlin.properties.Delegates

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {

        //方式1.通过标准代理实现late init
        var instance: App by Delegates.notNull()
            private set

    }
}