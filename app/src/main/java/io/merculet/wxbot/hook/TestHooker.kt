package io.merculet.wxbot.hook

import com.gh0u1l5.wechatmagician.spellbook.C
import com.gh0u1l5.wechatmagician.spellbook.WechatGlobal
import com.gh0u1l5.wechatmagician.spellbook.base.Hooker
import com.gh0u1l5.wechatmagician.spellbook.base.HookerProvider
import com.gh0u1l5.wechatmagician.spellbook.util.LogUtil
import com.gh0u1l5.wechatmagician.spellbook.util.LogUtil.log
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers

/**
 * @Author Aaron
 * @Date 2018/12/12
 * @Email aaron@magicwindow.cn
 * @Description
 */


object TestHooker : HookerProvider {

    override fun provideStaticHookers(): List<Hooker>? {
        return listOf(chattingFooterEventImplHook())
    }


    private fun test() {

    }

    private fun test(abc: String) {

    }

    private fun chattingFooterEventImplHook(): Hooker {
        LogUtil.log("SendMsgHooker chattingFooterEventImplHook ${Classes.ChattingFooterEventImpl}")
        return Hooker {

            Class.forName("")
            val clz = XposedHelpers.findClass("com.tencent.mm.ui.transmit.SendAppMessageWrapperUI", WechatGlobal.wxLoader)
            XposedHelpers.findAndHookMethod(clz, "a",
                    Class.forName("com.tencent.mm.ui.transmit.SendAppMessageWrapperUI"),
                    Class.forName("com.tencent.mm.opensdk.modelmsg.WXMediaMessage"),
                    C.String,
                    C.Int,
                    object : XC_MethodHook() {
                        override fun beforeHookedMethod(param: MethodHookParam?) {
                            log("set field_talker start")
                            LogUtil.logStackTraces()
                            log("set field_talker end")
                        }
                    })
        }
    }

}