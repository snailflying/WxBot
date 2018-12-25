package io.merculet.core.base

import android.app.Application
import org.litepal.LitePal
import kotlin.properties.Delegates


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        LitePal.initialize(this)
    }

    companion object {

        //方式1.通过标准代理实现late init
        var instance: App by Delegates.notNull()
            private set

    }
}