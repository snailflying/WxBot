package io.merculet.wxbot.hook

import com.gh0u1l5.wechatmagician.spellbook.base.Hooker
import com.gh0u1l5.wechatmagician.spellbook.base.HookerProvider
import com.gh0u1l5.wechatmagician.spellbook.util.LogUtil
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import io.merculet.wxbot.config.Config

object SendMsgHooker : HookerProvider {

    override fun provideStaticHookers(): List<Hooker>? {
        return listOf(chattingFooterEventImplHook(), netSceneSendMsgHook())
    }


    private fun netSceneSendMsgHook(): Hooker {
        LogUtil.log("SendMsgHooker netSceneSendMsgHook ${Classes.NetSceneSendMsg}")

        return Hooker {
            XposedBridge.hookAllConstructors(Classes.NetSceneSendMsg, object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam?) {
                    param?.args?.apply {
                        val content = this[1] as String
                        LogUtil.log("SendMsgHooker netSceneSendMsgHook content = $content")

                        val splitIndex = content.indexOf(Config.WX_MSG_SPLIT)
                        if (splitIndex > 0) {//如果存在这个分隔符，代表是我们 hook 的
                            // 拆分出我们拼凑的 wxId 和 实际要发送的内容
                            val wxId = content.substring(0, splitIndex)
                            val contentReal = content.substring(splitIndex + Config.WX_MSG_SPLIT.length, content.length)
                            // 设置到 args 里
                            LogUtil.log("SendMsgHooker netSceneSendMsgHook wxId = $wxId,content:$contentReal")

                            this[0] = wxId
                            this[1] = contentReal
                        }
                    }
                }
            })
//        val clz = XposedHelpers.findClass("com.tencent.mm.g.c.cg", WechatGlobal.wxLoader)
//        XposedHelpers.findAndHookMethod(clz, "ed", String::class.java, object : XC_MethodHook() {
//            override fun beforeHookedMethod(param: MethodHookParam?) {
//                log("set field_talker start")
//                LogUtil.logStackTraces()
//                log("set field_talker end")
//            }
//        })
        }
    }

    // 这个类可以发送消息，要获取到它的实例，hook 构造方法，将它的实例保存到 Objects.ChattingFooterEventImpl
    private fun chattingFooterEventImplHook(): Hooker {
        LogUtil.log("SendMsgHooker chattingFooterEventImplHook ${Classes.ChattingFooterEventImpl}")
        return Hooker {
            XposedBridge.hookAllConstructors(Classes.ChattingFooterEventImpl, object : XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam?) {
                    LogUtil.log("SendMsgHooker ChattingFooterEventImpl = ${param?.thisObject}")
                    Objects.ChattingFooterEventImpl = param?.thisObject
                }
            })
        }
    }

    // hook 这个类的构造方法，参数0是微信 id，也就是收件人 id，参数1是消息内容
//    private val netSceneSendMsgHook = Hooker {
//        XposedBridge.hookAllConstructors(Classes.NetSceneSendMsg, object : XC_MethodHook() {
//            override fun beforeHookedMethod(param: MethodHookParam?) {
//                param?.args?.apply {
//                    val content = this[1] as String
//                    LogUtil.log("SendMsgHooker content = $content")
//
//                    val splitIndex = content.indexOf(WX_MSG_SPLIT)
//                    if (splitIndex > 0) {//如果存在这个分隔符，代表是我们 hook 的
//                        // 拆分出我们拼凑的 wxId 和 实际要发送的内容
//                        val wxId = content.substring(0, splitIndex)
//                        val contentReal = content.substring(splitIndex + WX_MSG_SPLIT.length, content.length)
//                        // 设置到 args 里
//                        LogUtil.log("SendMsgHooker wxId = $wxId,content:$contentReal")
//
//                        this[0] = wxId
//                        this[1] = contentReal
//                    }
//                }
//            }
//        })
//    }

//
//    private val chattingFooterEventImplHook = Hooker {
//        XposedBridge.hookAllConstructors(Classes.ChattingFooterEventImpl, object : XC_MethodHook() {
//            override fun afterHookedMethod(param: MethodHookParam?) {
//                LogUtil.log("SendMsgHooker ChattingFooterEventImpl = ${param?.thisObject}")
//                Objects.ChattingFooterEventImpl = param?.thisObject
//            }
//        })
//    }
}