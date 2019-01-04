package io.merculet.wxbot.hook

import com.gh0u1l5.wechatmagician.spellbook.C
import com.gh0u1l5.wechatmagician.spellbook.WechatGlobal.wxClasses
import com.gh0u1l5.wechatmagician.spellbook.WechatGlobal.wxLazy
import com.gh0u1l5.wechatmagician.spellbook.WechatGlobal.wxLoader
import com.gh0u1l5.wechatmagician.spellbook.WechatGlobal.wxPackageName
import com.gh0u1l5.wechatmagician.spellbook.util.ReflectionUtil.findClassesFromPackage

object Classes {
    // 这个类有一个方法可以直接发送消息
    val ChattingFooterEventImpl: Class<*> by wxLazy("ChattingFooterEventImpl") {
        findClassesFromPackage(wxLoader!!, wxClasses!!, "$wxPackageName.ui.chatting")
                .filterByField("android.media.ToneGenerator")
                .filterByField("android.os.Vibrator")
                .filterByField("com.tencent.mm.pluginsdk.ui.chat.ChatFooter")
                .firstOrNull()
    }

    // 发送消息的封装类，可以 hook 消息目的地
    val NetSceneSendMsg: Class<*> by wxLazy("NetSceneSendMsg") {
        findClassesFromPackage(wxLoader!!, wxClasses!!, "$wxPackageName.modelmulti", 1)
                .filterByField("java.util.List")
                .filterByField("long")
                .filterByField("boolean")
                .filterByField("int")
                .filterByMethod(C.String, C.String, C.Object, C.Int)
                .filterByMethod(C.Int, "getType")
                .firstOrNull()
    }

    // 抢红包
    val LuckyMoneyReceiveUI: Class<*> by wxLazy("LuckyMoneyReceiveUI") {
        findClassesFromPackage(wxLoader!!, wxClasses!!, "$wxPackageName.plugin.luckymoney.ui") //com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyReceiveUI
                .filterByField("android.widget.RelativeLayout.LayoutParams")
                .filterByField("com.tencent.mm.sdk.platformtools.BackwardSupportUtil")
                .filterByField("com.tencent.mm.wallet_core.ui.WalletTextView")
                .firstOrNull()
    }

    // 需要hook这个类的构造方法 m  自动添加好友
    val Find_plugin_sdk_model_m: Class<*> by wxLazy("Find_plugin_sdk_model_m") {
        findClassesFromPackage(wxLoader!!, wxClasses!!, "com.tencent.mm.pluginsdk.model")   //com.tencent.mm.pluginsdk.model.m
                .filterByField("java.util.List")
                .filterByMethod(C.Int, "getType")
                .filterByMethod(C.String)
                .filterByMethod(null, C.String, C.String)
                .firstOrNull()
    }

    val Find_mm_model_au: Class<*> by wxLazy("Find_mm_model_au") {
        findClassesFromPackage(wxLoader!!, wxClasses!!, "com.tencent.mm.model")   //com.tencent.mm.model.au
                .filterByMethod(C.Boolean)
                .filterByMethod(Any::class.java, "getNotification")
                .filterByMethod(Any::class.java, "getSysCmdMsgExtension")
                .filterByMethod(null, "hold")
                .filterByMethod(null, "unhold")
                .firstOrNull()
    }

    // 需要hook这个类的构造方法 m  自动添加好友
    val Find_mm_ah_p: Class<*> by wxLazy("AddFriendHooker") {
        findClassesFromPackage(wxLoader!!, wxClasses!!, "com.tencent.mm.ah")
                .filterByField("java.util.List")
                .filterByMethod(C.Int, "getType")
                .filterByMethod(C.String)
                .filterByMethod(null, C.String, C.String)
                .firstOrNull()
    }
}