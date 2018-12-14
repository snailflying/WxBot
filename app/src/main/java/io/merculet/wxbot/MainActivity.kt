package io.merculet.wxbot

import android.os.Bundle
import android.util.Log
import com.gh0u1l5.wechatmagician.spellbook.util.LogUtil
import io.merculet.wxbot.base.BaseActivity
import io.merculet.wxbot.config.Config
import io.merculet.wxbot.domain.ReplyReq
import io.merculet.wxbot.domain.ReplyRes
import io.merculet.wxbot.domain.TuringReq
import io.merculet.wxbot.fragment.PrefFragment
import io.merculet.wxbot.handler.AbsHandler
import io.merculet.wxbot.handler.TextHandler
import io.merculet.wxbot.handler.TuringHandler
import io.merculet.wxbot.handler.WebHandler
import io.merculet.wxbot.util.OkHttpUtils
import io.merculet.wxbot.util.routerWithAnim

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gotoFragment()

    }

    private fun turingTest() {

        val request = TuringReq("你好")

        OkHttpUtils.instance.postTuring(request) { response ->


            Log.e("aaron1", "response:${response}")

        }

    }

    private fun gotoFragment() {
        val fragment = routerWithAnim(Config.ROUTER_FRAGMENT_SETTINGS)
                .with(Config.FRAGMENT_SETTINGS_ARG_PREF_RES, R.xml.pref_settings)
                .with(Config.FRAGMENT_SETTINGS_ARG_PREF_NAME, Config.PREFERENCE_NAME_SETTINGS)
                .getFragment(this) as PrefFragment

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
    }

}
