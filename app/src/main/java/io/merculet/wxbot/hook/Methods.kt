package io.merculet.wxbot.hook

import com.gh0u1l5.wechatmagician.spellbook.C
import com.gh0u1l5.wechatmagician.spellbook.WechatGlobal
import com.gh0u1l5.wechatmagician.spellbook.WechatGlobal.wxLazy
import com.gh0u1l5.wechatmagician.spellbook.util.ReflectionUtil.findMethodsByExactParameters
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.XposedHelpers.findClass
import java.lang.reflect.Method

object Methods {
    // 这个方法可以直接发送消息
    val ChattingFooterEventImpl_SendMsg: Method by wxLazy("ChattingFooterEventImpl_SendMsg") {
        findMethodsByExactParameters(Classes.ChattingFooterEventImpl, C.Boolean, C.String).firstOrNull()
    }

    val LuckyMoneyReceiveUI_onResume: Method by wxLazy("LuckyMoneyReceiveUI_onResume") {
        findMethodsByExactParameters(Classes.LuckyMoneyReceiveUI, null).firstOrNull()
    }

    //测试群组添加好友方法
    val AddFriend_em: Method by wxLazy("AddFriend_em") {
        findMethodsByExactParameters(findClass("com.tencent.mm.ui.contact.SelectContactUI", WechatGlobal.wxLoader), C.Boolean, C.List).firstOrNull()
    }

    val RequestCaller_p: Method by wxLazy("RequestCaller_p") {
        findMethodsByExactParameters(XposedHelpers.callStaticMethod(Classes.Find_mm_model_au, "Dk") as Class<*>, C.Int).firstOrNull()
    }
}