package io.merculet.wxbot.hook

import com.gh0u1l5.wechatmagician.spellbook.C
import com.gh0u1l5.wechatmagician.spellbook.WechatGlobal.wxLazy
import com.gh0u1l5.wechatmagician.spellbook.util.ReflectionUtil.findMethodsByExactParameters
import de.robv.android.xposed.XposedHelpers
import java.lang.reflect.Method

object Methods {
    // 这个方法可以直接发送消息
    val ChattingFooterEventImpl_SendMsg: Method by wxLazy("ChattingFooterEventImpl_SendMsg") {
        findMethodsByExactParameters(Classes.ChattingFooterEventImpl, C.Boolean, C.String).firstOrNull()
    }

    val LuckyMoneyReceiveUI_onResume: Method by wxLazy("LuckyMoneyReceiveUI_onResume") {
        findMethodsByExactParameters(Classes.LuckyMoneyReceiveUI, null).firstOrNull()
    }

    val RequestCaller_p: Method by wxLazy("RequestCaller_p") {
        findMethodsByExactParameters(XposedHelpers.callStaticMethod(Classes.Find_mm_model_au, "Dk") as Class<*>, C.Int).firstOrNull()
    }
}