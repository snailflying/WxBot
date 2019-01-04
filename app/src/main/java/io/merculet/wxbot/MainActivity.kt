package io.merculet.wxbot

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.wanzi.wechatrecord.util.ShellCommand
import io.merculet.core.base.BaseActivity
import io.merculet.core.config.Config
import io.merculet.core.ext.routerWithAnim
import io.merculet.wxbot.domain.TuringReq
import io.merculet.wxbot.fragment.PrefFragment
import io.merculet.wxbot.service.ServerService
import io.merculet.wxbot.util.OkHttpUtils

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        if (ShellCommand.checkRoot(packageCodePath)) {
//            ShellCommand.shellCommand("chmod -R 777 ${Config.WX_ROOT_PATH}")
//        }

        //start service
        startService(Intent(this, ServerService::class.java))

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
