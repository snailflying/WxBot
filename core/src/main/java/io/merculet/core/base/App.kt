package io.merculet.core.base

import android.support.multidex.MultiDexApplication
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import org.litepal.LitePal
import kotlin.properties.Delegates


class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        LitePal.initialize(this)
    }

    companion object {

        //方式1.通过标准代理实现late init
        var instance: App by Delegates.notNull()
            private set

        init {
            //设置全局的Header,Footer构建器
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout -> ClassicsHeader(context).setEnableLastTime(false).setTextSizeTitle(14F).setSpinnerStyle(SpinnerStyle.Translate) }
            SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
                layout.setEnableOverScrollDrag(true)    //开启越界拖动（仿苹果效果)
                layout.setEnableAutoLoadMore(true)  //启用列表惯性滑动到底部时自动加载更多
                layout.setEnableFooterFollowWhenNoMoreData(true)  //在全部加载结束之后Footer跟随内容
                ClassicsFooter(context).setDrawableSize(20f).setTextSizeTitle(14F)
            }
        }
    }
}