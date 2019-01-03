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
        return listOf(openRedpacket(), chattingFooterEventImplHook())
    }

    private fun openRedpacket(): Hooker {
        LogUtil.log("TestHooker openRedpacket ~")
        return Hooker {
            // hook红包界面初始化“开”按钮的方法，在该方法完成后自动点击开按钮领取红包
            XposedBridge.hookMethod(Methods.LuckyMoneyReceiveUI_onResume, object : XC_MethodHook() {

                override fun beforeHookedMethod(param: MethodHookParam?) {
                    LogUtil.log("TestHooker LuckyMoneyReceiveUI_onResume = ${param?.thisObject}")
                }

                override fun afterHookedMethod(param: MethodHookParam?) {
                    LogUtil.log("TestHooker LuckyMoneyReceiveUI_onResume = ${param?.thisObject}")
                }
            })
        }
    }

    /**
     * 微信运行时包名和打包之后不一样,所以导致findAndHookMethod找不到对应的class
     */
    private fun chattingFooterEventImplHook(): Hooker {
        LogUtil.log("TestHooker chattingFooterEventImplHook ~")
        return Hooker {
            XposedHelpers.findAndHookMethod("com.tencent.mm.ui.chatting.ChattingSendDataToDeviceUI", WechatGlobal.wxLoader, "getView", C.Int, C.View, C.ViewGroup,
                    object : XC_MethodHook() {
                        override fun beforeHookedMethod(param: XC_MethodHook.MethodHookParam?) {
                            log("开始劫持了~")
                            log("参数1 = " + (param?.args?.get(0) ?: ""))
                        }

                        override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam?) {
                            log("劫持结束了~")
                            log("参数1 = " + (param?.args?.get(0) ?: ""))
                        }
                    })
        }
    }

}